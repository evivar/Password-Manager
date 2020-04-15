package com.ernesto.passwordmanager.presentation.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import com.ernesto.passwordmanager.domain.entity.Person;
import com.ernesto.passwordmanager.presentation.adapter.PeopleAdapter;
import com.ernesto.passwordmanager.presentation.item.PeopleItem;

import java.util.ArrayList;

public class AddEditPersonDialog extends AppCompatDialogFragment {

    private Spinner spinnerPeople;

    private EditText editTextName;

    private Button saveBtn;

    private Button cancelBtn;

    private AddEditPersonDialogListener listener;

    private ArrayList<PeopleItem> peopleList;

    private PeopleAdapter adapter;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_edit_person, null);

        builder.setView(view);

        initList();

        spinnerPeople = view.findViewById(R.id.peopleSpinner_AddPersonDialog);
        adapter = new PeopleAdapter(getContext(), peopleList);
        spinnerPeople.setAdapter(adapter);

        editTextName = view.findViewById(R.id.nameTxt_AddPersonDialog);

        saveBtn = view.findViewById(R.id.AddPersonBtn_AddPersonDialog);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                if(name.trim().isEmpty()){
                    Toast.makeText(getActivity(), "Introduzca un nombre", Toast.LENGTH_SHORT).show();
                }
                else{
                    String img = getImage();
                    listener.addEditPerson(-1, name, img);
                    dismiss();
                }
            }
        });

        cancelBtn = view.findViewById(R.id.CancelBtn_AddPersonDialog);
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return builder.create();
    }

    private void initList() {
        peopleList = new ArrayList<>();
        peopleList.add(new PeopleItem(R.drawable.ic_chico));
        peopleList.add(new PeopleItem(R.drawable.ic_chica));
        peopleList.add(new PeopleItem(R.drawable.ic_adulta));
        peopleList.add(new PeopleItem(R.drawable.ic_adulto));
        peopleList.add(new PeopleItem(R.drawable.ic_abuela));
        peopleList.add(new PeopleItem(R.drawable.ic_abuelo));
    }

    public String getImage(){
        String image = "";
        switch ((int) spinnerPeople.getSelectedItemId()){
            case 0: image = "ic_chico"; break;
            case 1: image = "ic_chica"; break;
            case 2: image = "ic_adulta"; break;
            case 3: image = "ic_adulto"; break;
            case 4: image = "ic_abuela"; break;
            case 5: image = "ic_abuelo"; break;
            default: image = "nada"; break;
        }
        return image;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddEditPersonDialogListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "Implementar el listener del dialogo");
        }
    }

    public interface AddEditPersonDialogListener{

        void addEditPerson(int imgId, String name, String img);

    }

}
