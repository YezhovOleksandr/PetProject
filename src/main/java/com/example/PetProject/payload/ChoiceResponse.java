package com.example.PetProject.payload;

import lombok.Data;

@Data
public class ChoiceResponse {
    private long Id;
    private String text;
    private long voteCount;

}
