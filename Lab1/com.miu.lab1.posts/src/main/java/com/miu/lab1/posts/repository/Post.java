package com.miu.lab1.posts.repository;

import lombok.*;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private int id;
    private String title;
    private String content;
    private String author;
    private Date createdDate;
}
