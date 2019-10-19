package com.stackroute.userregistration.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

@Document(collection = "movieColl")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    String name;
    String email;
    String username;

//   String password;
    Date dateOfBirth;
    String newsPreferences;


}
