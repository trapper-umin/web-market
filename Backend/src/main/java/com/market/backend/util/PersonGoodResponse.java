package com.market.backend.util;

import com.market.backend.dto.PersonDTOResponse;
import com.market.backend.models.Person;

public class PersonGoodResponse {

    private PersonDTOResponse person;

    public PersonGoodResponse(PersonDTOResponse person) {
        this.person = person;
    }

    public PersonDTOResponse getPerson() {
        return person;
    }

    public void setPerson(PersonDTOResponse person) {
        this.person = person;
    }
}
