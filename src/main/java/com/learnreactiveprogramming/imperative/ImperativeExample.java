package com.learnreactiveprogramming.imperative;

import java.util.ArrayList;
import java.util.List;

public class ImperativeExample {
    public static void main(String[] args) {
        var names = List.of("allison","ben","chloe","adam");
        var result = namesGreaterThanSize(names, 3);
        System.out.println("Names: " + result);
    }

    private static List<String> namesGreaterThanSize(List<String> names, int size) {
        var namesFiltered = new ArrayList<String>();
        for (String name: names) {
            if(name.length() > size && !namesFiltered.contains(name.toUpperCase())){
                namesFiltered.add(name.toUpperCase());
            }
        }
        return namesFiltered;
    }
}
