# Introduction:

	Lambda expressions in computer programming are byproducts of 
	the mathematical logic system, lambda calculus. 
	It was 'Alonzo Church' who came up with lambda calculus to 
	give structure to the concept of effective computability.

	Lambda expressions are also known 'anonymous functions' because
	in lambda calculus all functions are anonymous (not bound to an identifier).
	Lambda expressions have now been used in computer programming since
	their introduction in Lisp in 1958. 

# Problem:

	The question we are trying to answer is the following:
	Are lamdba expressions in Java 8 faster than non-lambda expressions
	at accomplishing the same tasks.

# Lambda Expression:

	Lambda Expressions are also known as 'anonymous functions' 
	because they are functions without an identifier. These expressions
	can make use of the already programmed functional interfaces,
	such as a Predicate or Function.

	With no identifier, a lambda expression isn't intended to be called
	many times like a method. They are actually commonly used to 
	avoid coding unnecessary methods.

	Thus, if the functionality is only needed once or for a short amount
	of time, lambda expressions help make code clearer and concise.

# Why Use Lambda Expressions?

	The use of functional interfaces paired with anonymous inner classes
	is a common theme in Java. To simplify the coding, functional interfaces
    are taken advantage of for use with lambda expressions, 
	eliminating the need to program inner classes.
	
	The 'horizontal solution' of the lambda expression, solves 
	the 'vertical problem' presented by using inner classes. A
	lambda expression addressed the bulkiness of an inner class by 
	coverting 5, 6, or even more lines of code into a single statement.

# Lambda Comparisons:

	Lambda expressions allow for a much more concise way of iterating over
	a collection of data such as a list. Lambda expressions can use
	multiple functions and interfaces to accomplish a task.

## Reduction:

	* stream.reduce() method
	* It is an identity element that is the starting value of reduction and
	default value if there are no elements in the stream.
	* The method also consists of an 'accumulator' that takes as
	parameters, the partial result so far of the reduction, and the
	next element in the stream.
	
	Example: 

```java
int total=nums.stream().reduce(0,(a,b)->a+b); // 0 is base value
```

## Filtering:

	* stream.filter() method
	* It takes a predicate as an argument and returns a new stream
	containing the elements that matched the conditions of predicate.
	* Each predicate can have multiple conditions that need to be 
	satisfied. 
	* A lambda expression can be passed into stream.filter() method instead.

	Example:

```java 
List<String> filtered = strList.stream()
                .filter(x -> x.length > 3)
                .collect(Collectors.toList());
```

## Collecting:

	The stream.Collectos class has a variety of methods that are of great use 
    to streams and lambda expressions. These methods from the Collectors class
    can be used inside the stream.collect method of the lambda expression.

### To List:

    * Collector.toList() method
    * The method takes all the elements that are left in a stream, and stores 
    them in a list. 
    * This makes it quick and easy to create a new list, filtering out unwanted 
    elements from the old list. 

    Example: Extracting all the numbers in a list that are greater than 5

```java
List<Integer> above5=numberList.stream()
        .filter(x->x>5)
        .collect(Collectoers.toList());
```

### Joining:

    * joining() method
    * The method is a terminal operation that created a non-stream result.
    * Inside the stream.collect method, the joining() method returns a Collector
    that concatenates all the elements in the stream.
    * The joining() method can take a CharSequence as a parameter. 
    * In that case a Collector is returned that concatenates the stream elements
    with the CharSequence separating each element.

    Example:

```java
String con=names.stream()
        .collect(Collectors.joining(","));
```

## Mapping

    Mapping involves taking an object and assigning it to a new value. If for example
    there was a stream filled with Person objects, mapping could create a stream 
    filled with numbers, such as the Person object's age. Mapping can be accomplished
    with the stream.map() method.
    
    Example:

```java
List<String> upperNames=names.stream()
        .map(name->name.toUpperCase())
        .collect(Collectors.toList());
```

## Passing In Functions and Predicates:

    The `java.util.function` package can be used to pass in a Function or Predicate into a
    stream's intermediate operations(s) to replace a lambda expression. Bith a Function and 
    Predicate can return true or false, allowing them to passed in to methods that need to
    evaluate a condition.

### Predicates

    A predicate is a functional interface that can be used as a target for a lambda expression
    or method reference. The syntax for defining a predicate is `Predicate<T>` where T is the 
    type of argument being tested. The `Predicate<T>` then determines if the input object meets 
    some criteria.
    
    Example: 

```java
Predicate<String> startWithA=p->p.startsWith("A");
        List<String> startingWithA=names.stream()
        .filter(startWithA)
        .collect(Collectors.toList());
```

### Functions:

    A function is also a fucntional interface that can be used as a target for a lambda expression
    or method reference. The syntax for defining a function is `Function<T,R>` where T is the type
    of argument being passed in and R is the type of result for the function.
    
    The Function<T,R> takes ina single argument and returns some result. Unlike the predicate the result
    isn't necessarily a Boolean.

    Example:

```java
Function<String, Predicate<String>>startsWithLetter=
        letter->name->name.startsWith(letter);

        List<String> namesStartingWithA=
        names.stream()
        .filter(startsWithLetter.apply("A"))
        .collect(Collectors.toList());
```

    In the above example, a predicate is returned by the function. What makes the function different from
    the predicate example is the ability to check if the string started with any letter.
    Whereas the predicate in previous section was hard coded to only check if the string started with the
    letter "A". 

## Calling Class Methods

    Lambda expressions can be used to call methods written elsewhere in the class or superclass. The method
    could return a boolean for filtering, be used for mapping, or be part of the terminal operation. 
    
    Example:

```java
static boolean isPrime(int n){
        for(int i=2;i<n; i++){
        if(n%i==0)
        return false;
        }
        return true;
        }
```

    Since a Boolean is returned, the isPrime() method is used in the body of a lambda expression.
    The lambda expression is inside the stream.filter() method to filter the stream of numbers. The
    stream.count() method is then used to add up how many elements are left in the stream.

    Example:

```java
int counter=(int)nums.stream()
        .filter(p->isPrime(p))
        .count();
```

# Conclusions:

    The addition of lambda expressions to Java8 provide for more functional, concise and readable coding.
    In addition given enough execution time, the new lambda expressions can provide a performance
    advantage. The report entitled 'Clash of the Lamdbas' also shows that Java's lambda expressions not
    only held their own.

# This .md file contains information from the following:

* SERP'15 >
  The 2015 International Conference on Software Engineering Research and Practice :>
  Performance of Lambda Expressions in Java 8 :
  http://worldcomp-proceedings.com/proc/p2015/SER2509.pdf