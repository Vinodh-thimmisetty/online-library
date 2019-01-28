package com.vinodh.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    private long id;

    private String title;
    private String author;
    private String description;

    private double price;

    private Set<String> tags;
}
