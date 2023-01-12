package com.market.backend.util;

import com.market.backend.dto.PersonDTO;
import com.market.backend.dto.PersonDTOResponse;

import java.util.List;

public class PeopleGoodResponse {

    private List<PersonDTOResponse> persons;

    public PeopleGoodResponse(List<PersonDTOResponse> persons) {
        this.persons = persons;
    }

    public List<PersonDTOResponse> getPersons() {
        return persons;
    }

    public void setPersons(List<PersonDTOResponse> persons) {
        this.persons = persons;
    }
}
