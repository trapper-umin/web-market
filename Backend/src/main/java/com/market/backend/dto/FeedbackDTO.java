package com.market.backend.dto;

import jakarta.validation.constraints.*;

public class FeedbackDTO {

    @NotEmpty(message = "Author name should be not empty")
    @Size(min=2,max = 50,message = "Author name should be between 2 and 50")
    private String author;

    @NotNull(message = "Mark should be not null")
    @Min(value = 0,message = "Mark should be greater than 0")
    @Max(value = 100,message = "Mark should be less than 100")
    private int mark;

    @NotEmpty(message = "Message should be not empty")
    @Size(max = 1000,message = "Max message size is 1000 characters")
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
