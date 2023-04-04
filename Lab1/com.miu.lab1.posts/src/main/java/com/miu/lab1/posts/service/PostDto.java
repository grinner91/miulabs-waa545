package com.miu.lab1.posts.service;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class PostDto {
    private long id;
    private String title;
    private String content;
    private String author;
}
