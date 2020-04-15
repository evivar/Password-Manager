package com.ernesto.passwordmanager.presentation.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ernesto.passwordmanager.R;
import com.ernesto.passwordmanager.domain.entity.Password;
import com.ernesto.passwordmanager.domain.viewModel.PasswordViewModel;
import com.ernesto.passwordmanager.presentation.adapter.PasswordAdapter;
import com.ernesto.passwordmanager.presentation.dialog.AddEditPasswordDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ViewPasswordActivity extends AppCompatActivity implements AddEditPasswordDialog.AddEditPasswordDialogListener {

    public static final String EXTRA_ID = "com.ernesto.passwordmanager.presentation.ui.EXTRA_ID";

    public static final String EXTRA_NAME = "com.ernesto.passwordmanager.presentation.ui.EXTRA_NAME";

    public static final String EXTRA_N_PASSWORD = "com.ernesto.passwordmanager.presentation.ui.EXTRA_N_PASSWORD";

    public static final String EXTRA_IMAGE_ID = "com.ernesto.passwordmanager.presentation.ui.EXTRA_IMAGE_ID";

    public static final int ADD_PASSWORD_REQUEST = 1;

    public static final int EDIT_PASSWORD_REQUEST = 2;

    private TextView textViewName;

    private TextView textViewNPassword;

    private ImageView imageViewUser;

    private int personId;

    private PasswordViewModel viewModel;

    private PasswordAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_password);

        textViewName = findViewById(R.id.nameLbl_ViewPassword);
        textViewNPassword = findViewById(R.id.nPasswordLbl_ViewPassword);
        imageViewUser = findViewById(R.id.personImg_ViewPassword);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_NAME) && intent.hasExtra(EXTRA_N_PASSWORD)) {
            String name = intent.getStringExtra(EXTRA_NAME);
            textViewName.setText(name);
        }
        personId = intent.getIntExtra(EXTRA_ID, -1);
        int imgId = intent.getIntExtra(EXTRA_IMAGE_ID, -1);
        if(imgId != -1) {
            imageViewUser.setImageDrawable(ContextCompat.getDrawable(ViewPasswordActivity.this, imgId));
        }

        FloatingActionButton buttonAddPassword = findViewById(R.id.addPasswordBtn_ViewPassword);
        buttonAddPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddEditPasswordDialog(false, null);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.PasswordRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new PasswordAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(PasswordViewModel.class);
        try {
            viewModel.readAllPersonPassword(personId).observe(this, new Observer<List<Password>>() {
                @Override
                public void onChanged(List<Password> passwords) {
                    adapter.setPasswords(passwords);
                    if (passwords.size() == 1) {
                        textViewNPassword.setText(passwords.size() + " Contraseña");
                    } else {
                        textViewNPassword.setText(passwords.size() + " Contraseñas");
                    }
                }
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Password password = adapter.getPasswordAt(viewHolder.getAdapterPosition());
                viewModel.deletePasswordByUserAndPassword(password.getUser(), password.getPassword());
                Toast.makeText(ViewPasswordActivity.this, "Contraseña eliminada", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new PasswordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Password password) {
                openAddEditPasswordDialog(true, password);
            }
        });
    }

    public void openAddEditPasswordDialog(boolean edit, Password password) {
        AddEditPasswordDialog dialog = new AddEditPasswordDialog(edit, password);
        dialog.show(getSupportFragmentManager(), "add edit password dialog");
    }

    @Override
    public void addPassword(String app, String user, String pass) {
        Password password = new Password(personId, app, user, pass, 1);
        System.out.println(password.toString());
        viewModel.createPassword(password);
    }

    @Override
    public void editPassword(String app, String user, String pass, int passwordId) {
        Password password = new Password(personId, app, user, pass, -1);
        viewModel.updatePassword(password);
    }

    @Override
    public void editPasswordV2(Password oldPassword, String user, String pass) {
        System.out.println("AUIIIII");
        String oldUser = oldPassword.getUser();
        String app = oldPassword.getApplication();
        viewModel.updatePasswordByUserAndApp(user, pass, oldUser, app);
    }
}
