package ca.georgiancollege.copr.comp1011;

import java.security.SecureRandom;
import java.util.*;
import java.util.stream.*;
import java.util.regex.*;

public class Streams {

    public static void main(String[] args) {

        //primativeSteams2();
        //usingFilter();
        //usingMapping();
        usingFilterAndMapping();
    }
    static void createStreams(){

        //from arrays
        String[] names = {"Ben", "John", "Mary"};
        Stream<String> namesStream = Arrays.stream(names);

        int[] nums = {1, 2, 4, 5};
        IntStream numsStream = Arrays.stream(nums);

        //from collections
        PriorityQueue<Double> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(10d);
        priorityQueue.add(1.0);
        priorityQueue.add(-20d);
        priorityQueue.add(-2.0);

        Stream<Double> priorityQueueStream = priorityQueue.stream();


        //create own stream

        Stream<Integer> values = Stream.of(100, -30, 50, 52);
        Stream<Double> values1 = Stream.of(100d, -30d, 50d, 52d);
        Stream<Object> values2 = Stream.of("100", 'A', true, -3.0, 50, 52);

        Stream<Object> empty = Stream.empty();

        Stream<Object> custom = Stream.builder()
                .add("hello")
                .add('H')
                .add(1)
                .add(false)
                .build();

    }
    static void primativeSteams1(){

        IntStream values1 = IntStream.builder()
                .add(100)
                .add(-100)
                .add(50)
                .add(-50)
                .add(80)
                .build();

        //int sum = values1.sum();System.out.println(sum);

        //double average = values1.average().getAsDouble();
        OptionalDouble average = values1.average();
        //OptionalDataType
        //OptionalString, OptionalInteger aka nullable
        System.out.println(average);
    }
    static void primativeSteams2(){

        IntStream nums1to10_1 = IntStream.range(1, 11);
        IntStream nums1to10_2 = IntStream.rangeClosed(1, 10);

        IntSummaryStatistics stats = nums1to10_1.summaryStatistics();
        int min = stats.getMin();
        System.out.println(min);
    }
    static void primativeSteams3() {
        SecureRandom sran = new SecureRandom();
        IntStream example1 = sran.ints(10); //10 random integers from MIN to MAX
        IntStream example2 = sran.ints(10, 300, 400);
    }

    static void usingFilter(){

        /*
            create a stream of 10 integers in range of 1 to 100
            output all values that are factors of 3
         */
        SecureRandom sran = new SecureRandom();
        sran.ints(10, 1, 100)
                .filter( x-> x % 3 == 0 )
                .forEach(System.out::println);

        System.out.println();
        ArrayList<Integer> nums = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            int randomNumber = sran.nextInt(1, 100);
            if(randomNumber % 3 ==0)
                nums.add(randomNumber);
        }
        for(int currentNumber : nums){
            System.out.println(currentNumber);
        }

    }
    static void usingMapping(){

        //output the first 10 values of the multiplication table for 5's
            //5     10      15      20      25      .....   50
        //IntStream: create values from 1 to 10 (inc)
        //using map, multiplie these values by 5
        IntStream
                .rangeClosed(1, 10)
                .map(x-> x * 5)

                .forEach(System.out::println);
                //.forEach(x -> System.out.println(x));
    }
    static void usingFilterAndMapping(){

        /*
            create a stream of 5 names
            lets get the first occurence of a name that is
                at least 4 characters long
                and begins with a vowel
         */

        Pattern pattern = Pattern.compile("[aieou]]");
        Matcher matcher = pattern.matcher("Benny");
        while(matcher.find()){
            System.out.println(matcher.group());
        }

        Stream.of("Ben", "Benny", "Sanskar", "Soham", "Thomas", "Anthony", "Al", "Emma", "Isabella", "Eugene", "Kiwi", "Alex")
                .filter(x -> x.length() >=4)
                //.filter(x-> {char first = x.toLowerCase().charAt(0);return first == 'a'||first== 'e' || first == 'i' || first == 'o' || first =='u'; })
                 .map(String::toLowerCase)
                .filter(x -> Pattern.matches("^[aeiou]]", x))
                .map(x -> x + " Jr")

                /*
                .findFirst()
                .toString();

                 */
                        .forEach(System.out::println);

       // System.out.println(result);

    }
    }
