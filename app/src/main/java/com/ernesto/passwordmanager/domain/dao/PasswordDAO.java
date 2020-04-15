package com.ernesto.passwordmanager.domain.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ernesto.passwordmanager.domain.entity.Password;

import java.util.List;

@Dao
public interface PasswordDAO {

    @Insert
    void createPassword(Password password);

    @Query("SELECT * FROM password_table ORDER BY application ASC")
    LiveData<List<Password>> readAllPassword();

    @Query("SELECT * FROM password_table JOIN person_table ON password_table.person_id = person_table.id WHERE person_id = :personId ORDER BY application ASC")
    LiveData<List<Password>> readAllPersonPassword(int personId);

    @Update
    void updatePassword(Password password);

    @Query("UPDATE person_table SET nPassword = nPassword + 1 WHERE id = :personId")
    void updatePasswordCount(int personId);

    @Query("UPDATE password_table SET user = :newUser, password = :newPassword WHERE user = :oldUser AND application = :app")
    void updatePasswordByUserAndApp(String newUser, String newPassword, String oldUser, String app);

    @Delete
    void deletePassword(Password password);

    @Query("DELETE FROM password_table WHERE id = :passwordId")
    void deletePasswordById(int passwordId);

    @Query("DELETE FROM password_table WHERE user = :user AND password = :password")
    void deletePasswordByUserAndPassword(String user, String password);

}
