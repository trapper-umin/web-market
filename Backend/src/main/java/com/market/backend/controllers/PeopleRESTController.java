package com.market.backend.controllers;

import com.market.backend.dto.PersonDTO;
import com.market.backend.dto.PersonDTOResponse;
import com.market.backend.models.Person;
import com.market.backend.services.PeopleService;
import com.market.backend.util.Exception.Person.PersonExceptionHandler;
import com.market.backend.util.Exception.Person.PersonNotCreatedException;
import com.market.backend.util.Exception.Person.PersonNotUpdatedException;
import com.market.backend.util.GoodResponse;
import com.market.backend.util.PeopleGoodResponse;
import com.market.backend.util.PersonGoodResponse;
import com.market.backend.util.Validation.PersonDTONameValidation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PersonExceptionHandler
@RequestMapping("/market/api/people")
public class PeopleRESTController {

    private final PeopleService peopleService;
    private final ModelMapper modelMapper;
    private final PersonDTONameValidation personDTONameValidation;

    public PeopleRESTController(PeopleService peopleService, ModelMapper modelMapper,
                                PersonDTONameValidation personDTONameValidation){
        this.peopleService=peopleService;
        this.modelMapper = modelMapper;
        this.personDTONameValidation=personDTONameValidation;
    }

    @GetMapping
    public ResponseEntity<PeopleGoodResponse> showAll(){
        List<PersonDTOResponse> people=peopleService.findAll().stream().map(this::convertToPersonDTOResponse)
                .toList();
        PeopleGoodResponse response=new PeopleGoodResponse(people);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonGoodResponse> showById(@PathVariable("id")int id){
        PersonGoodResponse response=new PersonGoodResponse(
                convertToPersonDTOResponse(peopleService.findById(id))
        );
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<GoodResponse> create(@RequestBody @Valid PersonDTO personDTO,
                                               BindingResult bindingResult){
        personDTONameValidation.validate(personDTO,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder message =new StringBuilder();
            List<FieldError> errors=bindingResult.getFieldErrors();
            for(FieldError error : errors){
                message.append(error.getField()).append("-").append(error.getDefaultMessage()).append(";");
            }
            throw new PersonNotCreatedException(message.toString());
        }
        peopleService.create(convertToPerson(personDTO));
        return new ResponseEntity<>(new GoodResponse("User was created"),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<GoodResponse> update(@PathVariable("id") int id,@RequestBody @Valid PersonDTO personDTO,
                                               BindingResult bindingResult){
        personDTONameValidation.validate(personDTO,bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder message=new StringBuilder();
            List<FieldError> errors=bindingResult.getFieldErrors();
            for(FieldError error : errors){
                message.append(error.getField()).append("-").append(error.getDefaultMessage()).append(";");
            }
            throw new PersonNotUpdatedException(message.toString());
        }
        peopleService.update(id,convertToPerson(personDTO));
        return new ResponseEntity<>(new GoodResponse("User was updated"),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GoodResponse> delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return new ResponseEntity<>(new GoodResponse("User was delete"),HttpStatus.OK);
    }

    private PersonDTOResponse convertToPersonDTOResponse(Person person){
        return modelMapper.map(person, PersonDTOResponse.class);
    }

    private Person convertToPerson(PersonDTO personDTO){
        return modelMapper.map(personDTO,Person.class);
    }
}
