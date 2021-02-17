package com.guleri.functionalBasic;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Collection2SingleValue {
    public static void main(String[] args) {
        final List<String> Comments = Arrays.asList("nice villa", "excellent facility", "customer service is great", "no pool otherwise excellent place");

        // Let's read over the values in the 'Comments' collection of comments and determine the total
        // number of character for text processing.

        System.out.println("Total number of characters in all comments: " +
                Comments.stream()
                        .mapToInt(String::length)
                        .sum());
        // for other tasks: instead of sum() we can use min(), max(), sorted(), average(), etc
        /*
            MapReduce pattern:
                 With the map() method being the spread operation and the sum() method being the special case of the more general
                 reduce operation. In fact, the implementation of the sum() method in the JDK uses a reduce() method.
         */
        // Check for the longest comment in Comments collection
        final Optional<String> aLongComment =
                Comments.stream()
                        .reduce((comment1, comment2) -> comment1.length() >= comment2.length() ? comment1 : comment2);
        aLongComment.ifPresent(comment -> System.out.printf("A long comment: %s%n", comment));
        /*
          The lambda expression we're passing to the reduce() method takes two parameters, 'comment1' and 'comment2', and
           returns one of them based on the length.
          The reduce() method has no clue about our specific intent. That concern is separated from this method into the lambda
           expression that we pass to it - this is a lightweight application of the strategy pattern.

           The result of the reduce() method is an Optional because the list on which reduce() is called may be empty.
           If the list has only one element (comment), then reduce() would return that element and the lambda expression we
           pass would not be invoked.
         */

        final List<String> names = Arrays.asList("Guleri", "Negi", "Jai", "Sid");

        // Suppose you have to find longer name than a base name
        // Example: base name = Jai
        //          longer name = Guleri

        final String JaiOrLonger =
                names.stream()
                        .reduce("Jai", (name1, name2) -> name1.length() >= name2.length() ? name1 : name2);
        System.out.println(JaiOrLonger);

        /*
        If any name was longer than the given base name, it would get picked up; otherwise the function
        would return the base value.
         */

    }
}
