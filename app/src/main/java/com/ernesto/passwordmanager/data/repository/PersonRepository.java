package com.ernesto.passwordmanager.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.ernesto.passwordmanager.data.db.ApplicationDB;
import com.ernesto.passwordmanager.domain.dao.PersonDAO;
import com.ernesto.passwordmanager.domain.entity.Person;

import java.util.List;

public class PersonRepository {

    private PersonDAO personDAO;

    private LiveData<List<Person>> allPerson;

    public PersonRepository(Application application){
        ApplicationDB database = ApplicationDB.getInstance(application);
        personDAO = database.personDAO();
        allPerson = personDAO.readAllPerson();
    }

    public void createPerson(Person person){
        new CreatePersonAT(personDAO).execute(person);
    }

    public LiveData<List<Person>> readAllPerson(){
        return allPerson;
    }

    public void updatePerson(Person person){
        new UpdatePersonAT(personDAO).execute(person);
    }

    public void deletePerson(Person person){
        new DeletePersonAT(personDAO).execute(person);
    }

    private static class CreatePersonAT extends AsyncTask<Person, Void, Void>{

        private PersonDAO personDAO;

        public CreatePersonAT(PersonDAO personDAO) {
            this.personDAO = personDAO;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDAO.createPerson(people[0]);
            return null;
        }
    }

    private static class UpdatePersonAT extends AsyncTask<Person, Void, Void>{

        private PersonDAO personDAO;

        public UpdatePersonAT(PersonDAO personDAO) {
            this.personDAO = personDAO;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDAO.updatePerson(people[0]);
            return null;
        }
    }

    private static class DeletePersonAT extends AsyncTask<Person, Void, Void>{

        private PersonDAO personDAO;

        public DeletePersonAT(PersonDAO personDAO) {
            this.personDAO = personDAO;
        }

        @Override
        protected Void doInBackground(Person... people) {
            personDAO.deletePerson(people[0]);
            return null;
        }
    }



}
