package com.ernesto.passwordmanager.domain.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ernesto.passwordmanager.data.repository.PasswordRepository;
import com.ernesto.passwordmanager.domain.entity.Password;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class PasswordViewModel extends AndroidViewModel {

    private PasswordRepository repository;

    private LiveData<List<Password>> allPassword;

    public PasswordViewModel(@NonNull Application application) {
        super(application);
        repository = new PasswordRepository(application);
        allPassword = repository.readAllPassword();
    }

    public void createPassword(Password sassword) {
        repository.createPassword(sassword);
    }

    public LiveData<List<Password>> readAllPassword() {
        return allPassword;
    }

    public LiveData<List<Password>> readAllPersonPassword(int personId) throws ExecutionException, InterruptedException {
        return repository.readAllPersonPassword(personId);
    }

    public void updatePassword(Password password) {
        repository.updatePassword(password);
    }

    public void updatePasswordCount(int personId) {
        repository.updatePasswordCount(personId);
    }

    public void updatePasswordByUserAndApp(String newUser, String newPassword, String oldUser, String app) {
        repository.updatePasswordByUserAndApp(newUser, newPassword, oldUser, app);
    }

    public void deletePassword(Password sassword) {
        repository.deletePassword(sassword);
    }

    public void deletePasswordById(int id) {
        repository.deletePasswordById(id);
    }

    public void deletePasswordByUserAndPassword(String user, String password) {
        repository.deletePasswordByUserAndPassword(user, password);
    }

}
