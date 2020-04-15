package com.ernesto.passwordmanager.presentation.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ernesto.passwordmanager.R;
import com.ernesto.passwordmanager.domain.entity.Person;
import com.ernesto.passwordmanager.domain.viewModel.PersonViewModel;
import com.ernesto.passwordmanager.presentation.adapter.PersonAdapter;
import com.ernesto.passwordmanager.presentation.dialog.AddEditPersonDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AddEditPersonDialog.AddEditPersonDialogListener {

    public static final int ADD_PERSON_REQUEST = 1;

    public static final int VIEW_PASSWORD_REQUEST = 2;

    private PersonViewModel personViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_main);

        FloatingActionButton buttonAddPerson = findViewById(R.id.addPersonBtn_AllPerson);
        buttonAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(MainActivity.this, AddEditPersonActivity.class);
                startActivityForResult(intent, ADD_PERSON_REQUEST);*/
                openAddEditPersonDialog();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.PeopleRV);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        final PersonAdapter adapter = new PersonAdapter();
        recyclerView.setAdapter(adapter);

        personViewModel = ViewModelProviders.of(this).get(PersonViewModel.class);
        personViewModel.readAllPerson().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> people) {
                for (Person p : people) {
                    System.out.println(p.toString());
                }
                adapter.setPeople(people);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                personViewModel.deletePerson(adapter.getPersonAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Persona eliminada", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new PersonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Person person) {
                Intent intent = new Intent(MainActivity.this, ViewPasswordActivity.class);
                intent.putExtra(ViewPasswordActivity.EXTRA_ID, person.getId());
                intent.putExtra(ViewPasswordActivity.EXTRA_NAME, person.getName());
                intent.putExtra(ViewPasswordActivity.EXTRA_N_PASSWORD, person.getNPassword());
                int imgId = MainActivity.this.getResources().getIdentifier(
                        person.getImage(),
                        "drawable",
                        MainActivity.this.getPackageName()
                );
                intent.putExtra(ViewPasswordActivity.EXTRA_IMAGE_ID, imgId);
                startActivityForResult(intent, VIEW_PASSWORD_REQUEST);
            }
        });

    }

    public void openAddEditPersonDialog() {
        AddEditPersonDialog dialog = new AddEditPersonDialog();
        dialog.show(getSupportFragmentManager(), "add edit person dialog");
    }

    @Override
    public void addEditPerson(int imgId, String name, String img) {
        Person person = new Person(name, 0, imgId, img);
        personViewModel.createPerson(person);
    }
}
