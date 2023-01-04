package com.market.backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FeedbackDTO {

    @NotEmpty(message = "Author name should be not empty")
    @Size(min=2,max = 50,message = "Author name should be between 2 and 50")
    private String author;

    @NotNull(message = "Mark should be not null")
    //@Size(min = 1,max = 100,message = "Mark should be between 0 and 100")
    private int mark;

    @NotEmpty(message = "Message should be not empty")
    @Max(value = 1000,message = "Max message size is 1000 characters")
    private String message;

    public FeedbackDTO(){}

    public FeedbackDTO(String author, int mark, String message) {
        this.author = author;
        this.mark = mark;
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
