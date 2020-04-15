package com.ernesto.passwordmanager.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ernesto.passwordmanager.data.db.ApplicationDB;
import com.ernesto.passwordmanager.domain.dao.PasswordDAO;
import com.ernesto.passwordmanager.domain.dao.PersonDAO;
import com.ernesto.passwordmanager.domain.entity.Password;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PasswordRepository {

    private PasswordDAO passwordDAO;

    private LiveData<List<Password>> allPassword;

    public PasswordRepository(Application application) {
        ApplicationDB database = ApplicationDB.getInstance(application);
        passwordDAO = database.passwordDAO();
        allPassword = passwordDAO.readAllPassword();
    }

    public void createPassword(Password password) {
        new PasswordRepository.CreatePasswordAT(passwordDAO).execute(password);
    }

    public LiveData<List<Password>> readAllPassword() {
        return allPassword;
    }

    public LiveData<List<Password>> readAllPersonPassword(int personId) throws ExecutionException, InterruptedException {
        return new ReadAllPersonPasswordAT(passwordDAO).execute(personId).get();
    }

    public void updatePassword(Password password) {
        new PasswordRepository.UpdatePasswordAT(passwordDAO).execute(password);
    }

    public void updatePasswordCount(int personId){
        new PasswordRepository.UpdatePasswordCountAT(passwordDAO).execute(personId);
    }

    public void updatePasswordByUserAndApp(String newUser, String newPassword, String oldUser, String app){
        new PasswordRepository.UpdatePassworByUserAndAppdAT(passwordDAO).execute(newUser, newPassword, oldUser, app);
    }

    public void deletePassword(Password password) {
        new PasswordRepository.DeletePasswordAT(passwordDAO).execute(password);
    }

    public void deletePasswordById(int passwordId) {
        new PasswordRepository.DeletePasswordByIdAT(passwordDAO).execute(passwordId);
    }

    public void deletePasswordByUserAndPassword(String user, String password){
        new PasswordRepository.DeletePasswordByUserAndPasswordAT(passwordDAO).execute(user, password);
    }

    private static class CreatePasswordAT extends AsyncTask<Password, Void, Void> {

        private PasswordDAO passwordDAO;

        public CreatePasswordAT(PasswordDAO passwordDAO) {
            this.passwordDAO = passwordDAO;
        }

        @Override
        protected Void doInBackground(Password... people) {
            passwordDAO.createPassword(people[0]);
            return null;
        }
    }

    private static class ReadAllPersonPasswordAT extends AsyncTask<Integer, Void, LiveData<List<Password>>> {

        private PasswordDAO passwordDAO;

        public ReadAllPersonPasswordAT(PasswordDAO passwordDAO) {
            this.passwordDAO = passwordDAO;
        }

        @Override
        protected LiveData<List<Password>> doInBackground(Integer... ids) {
            return passwordDAO.readAllPersonPassword(ids[0]);
        }
    }

    private static class UpdatePasswordAT extends AsyncTask<Password, Void, Void> {

        private PasswordDAO passwordDAO;

        public UpdatePasswordAT(PasswordDAO passwordDAO) {
            this.passwordDAO = passwordDAO;
        }

        @Override
        protected Void doInBackground(Password... people) {
            passwordDAO.updatePassword(people[0]);
            return null;
        }
    }

    private static class UpdatePasswordCountAT extends AsyncTask<Integer, Void, Void>{

        private PasswordDAO passwordDAO;

        public UpdatePasswordCountAT(PasswordDAO passwordDAO) {
            this.passwordDAO = passwordDAO;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            passwordDAO.updatePasswordCount(integers[0]);
            return null;
        }
    }

    private static class UpdatePassworByUserAndAppdAT extends AsyncTask<String, Void, Void> {

        private PasswordDAO passwordDAO;

        public UpdatePassworByUserAndAppdAT(PasswordDAO passwordDAO) {
            this.passwordDAO = passwordDAO;
        }

        @Override
        protected Void doInBackground(String... strings) {
            passwordDAO.updatePasswordByUserAndApp(strings[0], strings[1], strings[2], strings[3]);
            return null;
        }
    }

    private static class DeletePasswordAT extends AsyncTask<Password, Void, Void> {

        private PasswordDAO passwordDAO;

        public DeletePasswordAT(PasswordDAO passwordDAO) {
            this.passwordDAO = passwordDAO;
        }

        @Override
        protected Void doInBackground(Password... people) {
            passwordDAO.deletePassword(people[0]);
            return null;
        }
    }

    private static class DeletePasswordByIdAT extends AsyncTask<Integer, Void, Void> {

        private PasswordDAO passwordDAO;

        public DeletePasswordByIdAT(PasswordDAO passwordDAO) {
            this.passwordDAO = passwordDAO;
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            passwordDAO.deletePasswordById(integers[0]);
            return null;
        }
    }

    private static class DeletePasswordByUserAndPasswordAT extends AsyncTask<String, Void, Void> {

        private PasswordDAO passwordDAO;

        public DeletePasswordByUserAndPasswordAT(PasswordDAO passwordDAO) {
            this.passwordDAO = passwordDAO;
        }

        @Override
        protected Void doInBackground(String... strings) {
            passwordDAO.deletePasswordByUserAndPassword(strings[0], strings[1]);
            return null;
        }
    }


}
