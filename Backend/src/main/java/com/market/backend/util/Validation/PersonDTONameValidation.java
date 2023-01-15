package com.market.backend.util.Validation;

import com.market.backend.dto.PersonDTO;
import com.market.backend.services.PeopleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonDTONameValidation implements Validator {

    private final PeopleService peopleService;

    public PersonDTONameValidation(PeopleService peopleService){
        this.peopleService=peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(PersonDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonDTO personDTO=(PersonDTO) target;
        if(peopleService.findByName(personDTO.getUsername()).isPresent()){
            errors.rejectValue("name","","User with this name already exist");
        }
    }
}
