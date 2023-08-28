package com.example.blog.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@Entity
@NoArgsConstructor
@Data
@Setter
@Getter
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Title should be not empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String title;

    @NotNull(message = "Body should be not empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    @JsonIgnore
    private User user;
}
