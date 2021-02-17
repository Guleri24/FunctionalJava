package com.guleri.functionalBasic;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class JoiningElements {
    public static void main(String[] args) {
        final List<String> friends = Arrays.asList("guleri", "sid", "negi", "jai");

        // Print friends with ',' as separator
        for (String name : friends)
            System.out.print(name + ", ");
        System.out.println();

        /*
        Darn it: there's a stinking comma at the end
        How do we tell Java not to place a comma there?
        Unfortunately, the loop will run its course and there's no easy way to tell
        the last element apart from the rest.
         */

        for (int i = 0; i < friends.size() - 1; i++)
            System.out.print(friends.get(i) + ", ");
        System.out.println(friends.get(friends.size() - 1));

        // The result looks good, but the code to produce the output does not.
        // Beam us up, modern Java :-)

        /*
        A StringJoiner class cleans up all that mess in Java8 and the String class has an
        added convenience method join() to turn that smelly code into a simple one-liner;
         */

        System.out.println(String.join(", ", friends));

        System.out.println(
                friends.stream()
                        .map(String::toUpperCase)
                        .collect(joining(", "))
        );

        /*
        We could use the reduce() method to concatenate elements into a string, but that
        would require some effort on out part. The JDK has a convenience method named collect(),
        which is another form of reduce that can help us collect valued into a target destination.
         */

        // As we can see the operation performed in the above function doesn't affect the original collection
        friends.forEach(System.out::println);
    }
}
