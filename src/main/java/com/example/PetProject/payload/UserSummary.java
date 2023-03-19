package com.example.PetProject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserSummary {
    private Long id;
    private String username;
    private String name;
}
