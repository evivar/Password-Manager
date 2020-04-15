package com.ernesto.passwordmanager.domain.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ernesto.passwordmanager.domain.entity.Person;

import java.util.List;

@Dao
public interface PersonDAO {

    @Insert
    void createPerson(Person person);

    @Query("SELECT * FROM person_table ORDER BY id ASC")
    LiveData<List<Person>> readAllPerson();

    @Update
    void updatePerson(Person person);

    @Query("UPDATE person_table SET nPassword = nPassword + 1 WHERE id = :personId")
    void updatePasswordCount(int personId);

    @Delete
    void deletePerson(Person person);

}
