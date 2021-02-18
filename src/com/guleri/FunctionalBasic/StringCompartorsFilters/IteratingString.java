package com.guleri.FunctionalBasic.StringCompartorsFilters;

public class IteratingString {
    public static void main(String[] args) {

        final String DrJava = "James Gosling";
        DrJava.chars()
                .forEach(System.out::println); // or :-( .forEach(ch -> System.out.println(ch));

        /*
        The chars() method return a Stream over which we can iterate, using the forEach()
        internal iterator.
        But the result is not quite what we'd except. Instead of seeing letters we're seeing numbers.
        That's because the chars() method return a stream of integers representing the letters instead
        of a stream of Characters.

        In this example, the method reference, again to an instance method, is based on an expression
        -an instance of PrintStream accessed through the static reference System.out.
         */

        /*
        In this example, although the code is concise, the output is not satisfactory. We want to see
        letters and not numbers in their place. To fix that, let's write a convenience method that prints
        an int as a letter. > printChar(ch : int) : void -> (char) ch;
         */

        DrJava.chars()
                .forEach(IteratingString::printChar); // forEach(<class_name>::<method_name>)
        // The output of this version will display letters.

        // If we want to process characters and not int from the start, we can convert the ints
        // to characters right after the call to the chars() method, like so:
        DrJava.chars()
                .mapToObj(ch -> (char) ch)
                .forEach(System.out::println);

        // Print for digits in String
        final String Data = "18 Feb 2021";
        System.out.println("Print digits in String : ");

        Data.chars()
                .filter(ch -> Character.isDigit(ch))
                .forEach(ch -> printChar(ch));

        /*
        Once again, instead of the lambda expression we passed to the filter() method and the
        forEach() method, we can use references to the respective methods.
         */

        Data.chars()
                .filter(Character::isDigit)
                .forEach(IteratingString::printChar);

        /* The method references here helped remove the mundane(ordinary, not exciting) parameter routing
        In addition, in this example we see yet another variation of method references compared to the previous
        two instances where we used them. When we first saw method references, we created one for an
        instance method. Later we created one for a call on a static reference. Now we're creating a method
        reference for a static method - method references seem to keep on giving.

         */

    }

    private static void printChar(int ch) {
        System.out.println((char) (ch));
    }

}
