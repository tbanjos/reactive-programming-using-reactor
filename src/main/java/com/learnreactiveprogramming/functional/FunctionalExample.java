package com.learnreactiveprogramming.functional;

import java.util.List;
import java.util.stream.Collectors;

public class FunctionalExample {
    public static void main(String[] args) {
        var names = List.of("ADAM","allison","ben","chloe","adam");
        var result = namesGreaterThanSize(names, 3);
        System.out.println("Names: " + result);
    }

    private static List<String> namesGreaterThanSize(List<String> names, int size) {
        return names.stream()
                .filter(name -> name.length() > size)
                .map(String::toUpperCase)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}
