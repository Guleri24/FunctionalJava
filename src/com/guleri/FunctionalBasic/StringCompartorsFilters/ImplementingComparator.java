package com.guleri.FunctionalBasic.StringCompartorsFilters;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class ImplementingComparator {
    public static void main(String[] args) {
        /*
        The Comparator interface is used in hundreds of places in the JDK library,
        searching operations to sorting, reversing, and so on. In Java 8 this has turned
        into a functional interface; the benefit is that we can use charmingly fluent
        syntax to implement comparators.
         */

        // Sorting with a Comparator
        /*
        We'll build an example to sort a list of languages using a few different points of
        comparisons. Let's first create the Language JavaBean
         */

        final List<Language> language = Arrays.asList(
                new Language("Java", 1995),
                new Language("Rust", 2010),
                new Language("Python", 1991),
                new Language("C++", 1985));

        List<Language> ascendingPublishedYear =
                language.stream()
                        .sorted(Language::yearDifference) // comparator method is used automatically
                        .collect(toList());
        printLanguage("Sorted in ascending order by published year ", ascendingPublishedYear);

        // Reusing a Comparator
        /*
        We got the languages sorted in ascending order by published year quite easily, and sorting
        them in descending order is just as easy. Let's give that a shot.
         */

        printLanguage("Sorted in descending order by published year: ",
                language.stream()
                        .sorted((lang1, lang2) -> lang2.yearDifference(lang1))
                        .collect(toList()));

        // But this way we violate the DRY rule : Don't Repeat Yourself
        /*
            If all we want is a reverse of the comparison, the JDL has us covered with
            a reversed() method on the Comparator, marked with a special method modifier
            called default.
         */
        Comparator<Language> compareAscending = Language::yearDifference;
        Comparator<Language> compareDescending = compareAscending.reversed();

        printLanguage("Sorted in ascending order by published year",
                language.stream()
                        .sorted(compareAscending)
                        .collect(toList()));
        printLanguage("Sorted in decreasing order by published year",
                language.stream()
                        .sorted(compareDescending)
                        .collect(toList()));
        printLanguage("Sorted in ascending order by name",
                language.stream()
                        .sorted(Comparator.comparing(Language::getName))
                        .collect(toList()));

        language.stream()
                .min(Language::yearDifference)
                .ifPresent(oldest -> System.out.println("Oldest: " + oldest));

        language.stream()
                .max(Language::yearDifference)
                .ifPresent(newest -> System.out.println("Newest: " + newest));
    }

    private static void printLanguage(final String message, List<Language> language) {
        System.out.println(message);
        language.forEach(System.out::println);
    }
}

class Language {
    private final String name;
    private final int publishedYear;

    public Language(final String name, final int publishedYear) {
        this.name = name;
        this.publishedYear = publishedYear;
    }

    public String getName() {
        return name;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public int yearDifference(final Language other) {
        return publishedYear - other.publishedYear;
    }

    public String toString() {
        return String.format("%s - %d", name, publishedYear);
    }
}
