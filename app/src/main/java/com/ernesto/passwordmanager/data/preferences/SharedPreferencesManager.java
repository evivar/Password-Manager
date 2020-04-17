package com.ernesto.passwordmanager.data.preferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharedPreferencesManager {

    private static final String SHARED_PREFFERENCES_NAME = "my_shared_prefferences";

    private static SharedPreferencesManager instance;

    private Context context;

    private SharedPreferencesManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPreferencesManager(context);
        }
        return instance;
    }

    public void registerUser(String email, String phone, String password){
        SharedPreferences sharedPreferencesManager = context.getSharedPreferences(SHARED_PREFFERENCES_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferencesManager.edit();
        editor.putString("email", email);
        editor.putString("phone", phone);
        editor.putString("password", password);

        editor.apply();
    }

    public String getUserEmail(){
        SharedPreferences sharedPreferencesManager = context.getSharedPreferences(SHARED_PREFFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferencesManager.getString("email", null);
    }

    public String getUserPassword(){
        SharedPreferences sharedPreferencesManager = context.getSharedPreferences(SHARED_PREFFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferencesManager.getString("password", null);
    }

    public void sendPassword(){
        String to = this.getUserEmail();
        String subject = "Password Manager - Contraseña olvidada";
        String msg = "Hola, \n Tu contraseña para poder iniciar sesión en Password Manager es: \n" + this.getUserPassword() + "\n Password Manager Team.";

        Intent sendMail = new Intent(Intent.ACTION_SEND);
        sendMail.putExtra(Intent.EXTRA_EMAIL, to);
        sendMail.putExtra(Intent.EXTRA_SUBJECT, subject);
        sendMail.putExtra(Intent.EXTRA_TEXT, msg);
    }

}
