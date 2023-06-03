package com.example.viewbindingapp3_003.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.viewbindingapp3_003.model.Person;

public class PersonViewModel extends ViewModel {
    public MutableLiveData<Person> personLiveData;
    private Person person;

    public PersonViewModel() {
        personLiveData = new MutableLiveData<>();
        person = new Person("Man", "Tomasz");
    }

    public void setupData(){
        personLiveData.setValue(person);
    }

    public MutableLiveData<Person> getObservedPerson() {
        return personLiveData;
    }

    public void changePerson(String sex, String name) {
        person.setSex(sex);
        person.setName(name);
        personLiveData.setValue(person);
    }

    public Person getPerson() {
        return person;
    }
}
