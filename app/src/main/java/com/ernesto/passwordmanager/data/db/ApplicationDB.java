package com.ernesto.passwordmanager.data.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.ernesto.passwordmanager.domain.dao.PasswordDAO;
import com.ernesto.passwordmanager.domain.dao.PersonDAO;
import com.ernesto.passwordmanager.domain.entity.Password;
import com.ernesto.passwordmanager.domain.entity.Person;

@Database(entities = {Person.class, Password.class}, version = 10)
public abstract class ApplicationDB extends RoomDatabase {

    private static ApplicationDB instance;

    public abstract PersonDAO personDAO();

    public abstract PasswordDAO passwordDAO();

    public static synchronized ApplicationDB getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ApplicationDB.class, "application_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAT(instance).execute();
        }
    };

    private static class PopulateDBAT extends AsyncTask<Void, Void, Void>{

        private PersonDAO personDAO;

        private PasswordDAO passwordDAO;

        public PopulateDBAT(ApplicationDB db) {
            this.personDAO = db.personDAO();
            this.passwordDAO = db.passwordDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

}
