package com.nhi.bookstore.model;

import lombok.Data;

@Data
public class UserDTO {
    int id;
    String userName;
    String firstName;
    String lastName;
    String email;
}
