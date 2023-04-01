package com.example.graphqlservice;

import java.util.Arrays;
import java.util.List;

public record Author (String id, String firstName, String lastName) {

    private static List<Author> authors = Arrays.asList(
            new Author("author-1", "Emmanuel", "Cadet"),
            new Author("author-2", "Damian", "Suski"),
            new Author("author-3", "Test1", "Test2")
    );

    public static Author getById(String id) {
        return authors.stream()
                .filter(author -> author.id().equals(id))
                .findFirst()
                .orElse(null);
    }
}
