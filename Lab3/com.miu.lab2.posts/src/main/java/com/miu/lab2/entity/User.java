package com.miu.lab2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private String email;
    private String password;
    private String firstname;
    private String lastname;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Role> roles;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JsonBackReference
    List<Post> posts;
}
