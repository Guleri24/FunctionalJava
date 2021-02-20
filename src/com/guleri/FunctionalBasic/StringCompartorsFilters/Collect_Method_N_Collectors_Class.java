package com.guleri.FunctionalBasic.StringCompartorsFilters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Collect_Method_N_Collectors_Class {
    public static void main(String[] args) {

        final List<Person> peeps = Arrays.asList(
                new Person("Abhishek", "Male", 20),
                new Person("Anshul", "Male", 19),
                new Person("Jai", "Male", 21),
                new Person("Sid", "Male", 21)
        );

        List<Person> olderThan20 = new ArrayList<>();
        peeps.stream()
                .filter(peep -> peep.getAge() > 20)
                .forEach(olderThan20::add);
        System.out.println("People older than 20: " + olderThan20);

        /*
        The code produced the desired result, but there are a few issues.
        First, the operation of adding an element into the target collection is
        pretty low level imperative rather than declarative.
        If we decide to make the iteration concurrent, we immediately have to deal
        with thread-safety concerns - the mutability makes it hard to parallelize.
        Fortunately, we can easily alleviate these concerns using the collect() method.
         */

        /*
        The collect() method takes a stream of elements and collects or gathers them into a
        result container. To do that, the method needs to know three things:
            * How to make a result container
            * How to add a single element to a result container
            * How to merge one result container into another

         The last item may not be necessary for purely sequential operations; the code is
         designed to work for both sequential and parallel execution.
         */

        List<Person> olderThan19 =
                peeps.stream()
                        .filter(peep -> peep.getAge() > 19)
                        .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        System.out.println("People older than 19: " + olderThan19);

        /*
        This version of code produces the same result as the previous version; however, this
        version has many benefits.
        First, we're programming with intention and more expressively
        Secondly, since we're not performing any explicit mutation in code, it's easy to parallelize
        the execution of the iteration.
        The collect() method can perform parallel additions, as appropriate, into different
        sublists, and then merge them in a thread-safe manner into a large list.
         */
    }


}

class Person {
    final String name;
    final String sex;
    final int age;

    public Person(final String name, final String sex, final int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return String.format("Name : %s, sex %s, age %d", name, sex, age);
    }

}