package com.ernesto.passwordmanager.presentation.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.ernesto.passwordmanager.R;
import com.ernesto.passwordmanager.domain.entity.Password;
import com.ernesto.passwordmanager.presentation.adapter.ApplicationAdapter;
import com.ernesto.passwordmanager.presentation.item.ApplicationItem;

import java.util.ArrayList;

public class AddEditPasswordDialog extends AppCompatDialogFragment {

    private Spinner spinnerApp;

    private EditText editTextUser;

    private EditText editTextPassword;

    private Button saveBtn;

    private Button cancelBtn;

    private AddEditPasswordDialogListener listener;

    private ArrayList<ApplicationItem> applicationList;

    private ApplicationAdapter adapter;

    private boolean isEditing;

    private Password password;

    private int passwordId;

    public AddEditPasswordDialog(boolean edit, Password password) {
        if (edit) {
            this.isEditing = true;
            this.password = password;
            passwordId = password.getId();
            System.out.println(password);
        } else {
            this.isEditing = false;
            this.password = null;
            this.passwordId = -1;
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_edit_password, null);

        builder.setView(view);

        initList();

        spinnerApp = view.findViewById(R.id.applicationSpinner_AddPasswordDialog);
        adapter = new ApplicationAdapter(getContext(), applicationList);
        spinnerApp.setAdapter(adapter);

        editTextUser = view.findViewById(R.id.userTxt_AddPasswordDialog);
        editTextPassword = view.findViewById(R.id.passwordTxt_AddPasswordDialog);

        if (isEditing) {
            editTextUser.setText(password.getUser());
            editTextPassword.setText(password.getPassword());
            spinnerApp.setVisibility(View.GONE);
        } else {
            spinnerApp.setVisibility(View.VISIBLE);
        }

        saveBtn = view.findViewById(R.id.AddBtn_AddPasswordDialog);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editTextUser.getText().toString();
                String pass = editTextPassword.getText().toString();
                ApplicationItem appItem = applicationList.get(spinnerApp.getSelectedItemPosition());
                String app = appItem.getApplicationName();
                if (user.trim().isEmpty() || pass.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Rellene el usuario y contraseña", Toast.LENGTH_SHORT).show();
                } else {
                    if (isEditing) {
                        //listener.editPassword(password.getApplication(), user, pass, password.getId());
                        listener.editPasswordV2(password, user, pass);
                    } else {
                        listener.addPassword(app, user, pass);
                    }
                    dismiss();
                }
            }
        });

        cancelBtn = view.findViewById(R.id.CancelBtn_AddPasswordDialog);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return builder.create();
    }

    private void initList() {
        applicationList = new ArrayList<>();
        applicationList.add(new ApplicationItem(R.drawable.ic_amazon, "Amazon"));
        applicationList.add(new ApplicationItem(R.drawable.ic_apple, "ID de Apple"));
        applicationList.add(new ApplicationItem(R.drawable.ic_applepay, "Apple Pay"));
        applicationList.add(new ApplicationItem(R.drawable.ic_aws, "Amazon Web Services"));
        applicationList.add(new ApplicationItem(R.drawable.ic_crunchyroll, "Crunchyroll"));
        applicationList.add(new ApplicationItem(R.drawable.ic_discord, "Discord"));
        applicationList.add(new ApplicationItem(R.drawable.ic_disneyplus, "Disney +"));
        applicationList.add(new ApplicationItem(R.drawable.ic_dropbox, "Dropbox"));
        applicationList.add(new ApplicationItem(R.drawable.ic_facebook, "Facebook"));
        applicationList.add(new ApplicationItem(R.drawable.ic_facetime, "FaceTime"));
        applicationList.add(new ApplicationItem(R.drawable.ic_fitbit, "FitBit"));
        applicationList.add(new ApplicationItem(R.drawable.ic_flickr, "Flickr"));
        applicationList.add(new ApplicationItem(R.drawable.ic_github, "GitHub"));
        applicationList.add(new ApplicationItem(R.drawable.ic_gmail, "Gmail"));
        applicationList.add(new ApplicationItem(R.drawable.ic_gog, "GOG"));
        applicationList.add(new ApplicationItem(R.drawable.ic_googlewallet, "Google Wallet"));
        applicationList.add(new ApplicationItem(R.drawable.ic_hangouts, "Hangouts"));
        applicationList.add(new ApplicationItem(R.drawable.ic_heroku, "Heroku"));
        applicationList.add(new ApplicationItem(R.drawable.ic_instagram, "Instagram"));
        applicationList.add(new ApplicationItem(R.drawable.ic_linkedin, "LinkedIn"));
        applicationList.add(new ApplicationItem(R.drawable.ic_moodle, "Moodle"));
        applicationList.add(new ApplicationItem(R.drawable.ic_netflix, "Netflix"));
        applicationList.add(new ApplicationItem(R.drawable.ic_onedrive, "OneDrive"));
        applicationList.add(new ApplicationItem(R.drawable.ic_origin, "Origin"));
        applicationList.add(new ApplicationItem(R.drawable.ic_outlook, "Outlook"));
        applicationList.add(new ApplicationItem(R.drawable.ic_paypal, "PayPal"));
        applicationList.add(new ApplicationItem(R.drawable.ic_pin, "PIN"));
        applicationList.add(new ApplicationItem(R.drawable.ic_pinterest, "Pinterest"));
        applicationList.add(new ApplicationItem(R.drawable.ic_playstation, "PlayStation"));
        applicationList.add(new ApplicationItem(R.drawable.ic_reddit, "Reddit"));
        applicationList.add(new ApplicationItem(R.drawable.ic_rockstar, "Rockstar"));
        applicationList.add(new ApplicationItem(R.drawable.ic_sap, "SAP"));
        applicationList.add(new ApplicationItem(R.drawable.ic_skype, "Skype"));
        applicationList.add(new ApplicationItem(R.drawable.ic_snapchat, "Snapchat"));
        applicationList.add(new ApplicationItem(R.drawable.ic_spotify, "Spotify"));
        applicationList.add(new ApplicationItem(R.drawable.ic_stackoverflow, "Stack Overflow"));
        applicationList.add(new ApplicationItem(R.drawable.ic_steam, "Steam"));
        applicationList.add(new ApplicationItem(R.drawable.ic_telegram, "Telegram"));
        applicationList.add(new ApplicationItem(R.drawable.ic_twitter, "Twitter"));
        applicationList.add(new ApplicationItem(R.drawable.ic_visa, "VISA"));
        applicationList.add(new ApplicationItem(R.drawable.ic_wifi, "Wi-Fi"));
        applicationList.add(new ApplicationItem(R.drawable.ic_wordpress, "Wordpress"));
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddEditPasswordDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Implementar el listener del dialogo");
        }

    }

    public interface AddEditPasswordDialogListener {
        void addPassword(String app, String user, String pass);

        void editPassword(String app, String user, String pass, int passwordId);

        void editPasswordV2(Password oldPassword, String user, String pass);
    }
}
