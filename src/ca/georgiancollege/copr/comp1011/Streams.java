package ca.georgiancollege.copr.comp1011;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.PriorityQueue;
import java.util.stream.*;

public class Streams {

    public static void main(String[] args) {

        primativeSteams2();
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
    }
