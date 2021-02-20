package com.guleri.FunctionalBasic.StringCompartorsFilters;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class MultipleFluentComparisons {
    public static void main(String[] args) {

        final List<Friends> friends = Arrays.asList(
                new Friends("Guleri", 20),
                new Friends("Negi", 19),
                new Friends("Jai", 21),
                new Friends("Sid", 21)
        );

        friends.stream()
                .sorted(comparing(Friends::getName))
                .forEach(System.out::println);

        System.out.println("By name");
        final Function<Friends, String> byName = Friends::getName;
        friends.stream()
                .sorted(comparing(byName))
                .forEach(System.out::println);

        System.out.println("By age");
        final Function<Friends, Integer> byAge = Friends::getAge;
        friends.stream()
                .sorted(comparing(byAge))
                .forEach(System.out::println);

        printFriends("Sorted in ascending order by age and name: ",
                friends.stream()
                        .sorted(comparing(byAge).thenComparing(byName))
                        .collect(toList()));

    }

    private static void printFriends(final String message, List<Friends> friends) {
        System.out.println(message);
        friends.forEach(System.out::println);
    }
}

class Friends {

    final String name;

    final int age;

    public Friends(final String name, final int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int ageDifference(Friends friend) {
        return age - friend.age;
    }

    public String toString() {
        return String.format("name %s , age %d", name, age);
    }
}

