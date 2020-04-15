package com.ernesto.passwordmanager.presentation.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ernesto.passwordmanager.R;
import com.ernesto.passwordmanager.domain.entity.Password;
import com.ernesto.passwordmanager.domain.entity.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordAdapter extends RecyclerView.Adapter<PasswordAdapter.PasswordHolder> {

    private List<Password> passwords = new ArrayList<>();

    private Resources resources;

    private OnItemClickListener listener;

    private Context context;


    @NonNull
    @Override
    public PasswordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.password_item, parent, false);
        context = parent.getContext();
        return new PasswordHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordHolder holder, int position) {
        Password currentPassword = passwords.get(position);
        holder.textViewUser.setText(currentPassword.getUser());
        holder.textViewName.setText(currentPassword.getApplication());
        holder.textViewPassword.setText(currentPassword.getPassword());
        String imgName = ("ic_" + currentPassword.getApplication().trim().replaceAll("\\s+", "").toLowerCase()).trim().toLowerCase();
        int imgId = holder.itemView.getContext().getResources().getIdentifier(
                imgName,
                "drawable",
                context.getPackageName());
        holder.imageViewApp.setImageDrawable(ContextCompat.getDrawable(holder.mContext, imgId));
        System.out.println("onBindViewHolder: " + currentPassword);
    }

    @Override
    public int getItemCount() {
        return passwords.size();
    }

    public void setPasswords(List<Password> passwords) {
        this.passwords = passwords;
        notifyDataSetChanged();
    }

    public Password getPasswordAt(int position) {
        return passwords.get(position);
    }


    class PasswordHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewApp;

        private TextView textViewName;

        private TextView textViewUser;

        private TextView textViewPassword;

        private ImageButton buttonShowPassword;


        private boolean isShowingPassword;

        private Context mContext;

        public PasswordHolder(@NonNull View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            imageViewApp = itemView.findViewById(R.id.appImg_Password);
            textViewName = itemView.findViewById(R.id.NameTxt_Password);
            textViewUser = itemView.findViewById(R.id.userTxt_Password);
            textViewPassword = itemView.findViewById(R.id.passwordTxt_Password);
            buttonShowPassword = itemView.findViewById(R.id.viewBtn_Password);
            isShowingPassword = false;
            buttonShowPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isShowingPassword) {
                        isShowingPassword = true;
                        textViewPassword.setTransformationMethod(null);
                    } else {
                        isShowingPassword = false;
                        textViewPassword.setTransformationMethod(new PasswordTransformationMethod());
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(listener != null && pos != RecyclerView.NO_POSITION){
                        listener.onItemClick(passwords.get(pos));
                    }
                }
            });
            // TODO: Añadir los click listeners de los botones de ver contraseña y editar
        }
    }

    public interface OnItemClickListener{
        void onItemClick(Password password);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
}
