package com.radis.example.models;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class User implements Serializable {

    private String userId;
    private String name;
    private String phone;
    private String email;


}
