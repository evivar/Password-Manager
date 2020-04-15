package com.ernesto.passwordmanager.domain.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ernesto.passwordmanager.data.repository.PersonRepository;
import com.ernesto.passwordmanager.domain.entity.Person;

import java.util.List;

public class PersonViewModel extends AndroidViewModel {

    private PersonRepository repository;

    private LiveData<List<Person>> allPerson;

    public PersonViewModel(@NonNull Application application) {
        super(application);
        repository = new PersonRepository(application);
        allPerson = repository.readAllPerson();
    }

    public void createPerson(Person person){
        repository.createPerson(person);
    }

    public LiveData<List<Person>> readAllPerson(){
        return allPerson;
    }

    public void updatePerson(Person person){
        repository.updatePerson(person);
    }

    public void deletePerson(Person person){
        repository.deletePerson(person);
    }
}
