package com.miu.lab2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    private String content;

    @ManyToOne
    private Post post;
}
