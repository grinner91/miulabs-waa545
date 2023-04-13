package com.miu.lab2.entity.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class CommentDto {
    int id;
    int postId;
    String content;
}