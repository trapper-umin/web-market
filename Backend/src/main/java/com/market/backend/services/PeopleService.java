package com.market.backend.services;

import com.market.backend.models.Person;
import com.market.backend.repositories.PeopleRepository;
import com.market.backend.util.Exception.Person.PersonNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository){
        this.peopleRepository=peopleRepository;
    }

    public List<Person> findAll(){
        return peopleRepository.findAll();
    }

    public Person findById(int id){
        return peopleRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    public Optional<Person> findByName(String name){
        return peopleRepository.findByName(name);
    }

    @Transactional
    public void create(Person person){
        person.setCreatedAt(LocalDateTime.now());
        person.setUpdatedAt(LocalDateTime.now());
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id,Person person){
        Optional<Person> personOld=peopleRepository.findById(id);
        if(personOld.isEmpty()){
            throw new PersonNotFoundException();
        }
        person.setId(id);
        person.setCreatedAt(personOld.get().getCreatedAt());
        person.setUpdatedAt(LocalDateTime.now());
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }
}
