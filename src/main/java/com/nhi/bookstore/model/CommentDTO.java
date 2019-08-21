package com.nhi.bookstore.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CommentDTO {
    @NotBlank
    String message;

    @NotBlank
    String dayCreate;

    @NotBlank
    String dayUpdate;

    @NotBlank
    String user;
}
