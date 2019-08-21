package com.nhi.bookstore.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonAlias("title")
    @NonNull
    @NotBlank(message = "Name is mandatory")
    private String title;

    @JsonAlias("author")
    @Column(nullable = false)
    @NonNull
    @NotEmpty
    private String author;

    @JsonAlias("author")
    @Column(nullable = false)
    private String description;

    @JsonAlias("created_at")
    @Column(nullable = false)
    private String dayCreate;

    @JsonAlias("updated_at")
    @Column(nullable = false)
    private String dayUpdate;

    @JsonAlias("enable")
    @Column(nullable = false)
    @NonNull
    private int enable;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "book")
    List<Comment> comments;
}
