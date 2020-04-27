package com.team3d;

import com.team3d.traditional.Addition;
import com.team3d.traditional.Calculation;
import com.team3d.traditional.Subtraction;

import java.nio.file.Files;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.FileHandler;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        //traditional way
        //must be supercalss //must be sub class
        Calculation sub = new Subtraction();
        Calculation add = new Addition();

        System.out.println("sub Result is : " + sub.calculate(15, 4));
        System.out.println("add Result is : " + add.calculate(15, 4));

        //------------------------------------------
        //way 2 anonymous classes
        Calculation addition = new Calculation() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };

        new Calculation() {
            @Override
            public int calculate(int a, int b) {
                return a - b;
            }
        };
        //-------------------------------------------------
        //way 3 modern lambda expression
        //first : build a class that implements calculation interface
        //secound : implement calculate method in that class
        //third : create a new instance from that class
        Calculation lambdaAddition = (a, b) -> a + b;
        int addresult = lambdaAddition.calculate(10,8);
        Calculation lambdaSubtraction = (a, b) -> a - b;
        int a = lambdaSubtraction.calculate(18, 14);
        System.out.println("lambda Result : " + a);
        //-------------------------------------------------predicate and map
        Map<String, Person> stringPersonMap = new HashMap<>();

        Person p = new Person("ali", "noori");
        Person p1 = new Person("reza", "kalantar");
        Person p2 = new Person("hossien", "equal");
        Person p3 = new Person("reza", "kalantar");
        Person p4 = new Person("hossien", "equal");
        stringPersonMap.put("1", p);
        stringPersonMap.put("2", p1);
        stringPersonMap.put("3", p2);
        stringPersonMap.put("4", p3);
        stringPersonMap.put("5", p4);

        //its a box thet gets an input and returns an outpur!!!you can use it everywhere
        //the difference is that you cant use if/else like this one.
        Predicate<Map<String, Person>> myPredicate = s -> s.size() == 5;
        Predicate<Map<String, Person>> myPredicate1 = s -> s.get("1").getName().equals("ali");
        Predicate<Map<String, Person>> myPredicate2 = s -> s.containsValue(2);
        System.out.println("preidcate1");
        boolean isValid = myPredicate.test(stringPersonMap);
        System.out.println(isValid);

        boolean isValid2 = myPredicate1.test(stringPersonMap);
        System.out.println(isValid2);

        boolean isValid3 = myPredicate.test(stringPersonMap);
        System.out.println(isValid3);
        //-------------------------------------------------------------------------

        //----------consumer<T> interface- it has an abstract method named accept() which returns void
        //do a job : print something , do something in database etc.
        //con : build a class,implements consumer interface (override accept method) and method body is : system.out.println(a);

        Consumer<Integer> display = con -> System.out.println(con);
        display.accept(19);
        //gets a list of numbers , multiply them and sets to list
        //imagine you have a box which get an array and multiply items
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        //its a box;gets a list of integers and * 2 them all;
        Consumer<List<Integer>> modify =
                lst -> {
                    for (int i = 0; i < lst.size(); i++)
                        lst.set(i, 2 * lst.get(i));
                };
        modify.accept(integers);
        Consumer<List<String>> toUpperCaseAll =
                lst1 ->{
                    for(int i=0;i<lst1.size();++i)
                        lst1.set(i,lst1.get(i).toUpperCase());

                };
        List<String> strings = new ArrayList<>();



        for (int i = 0; i < integers.size(); ++i) {
            System.out.println(integers.get(i));
        }
        ///---------------------end comsumer

        //-------------Function <T,R> interface : T:input R:output - overrides apply() method

        Function<Integer, String> function = (t) -> {
            if (t % 2 == 0)
                return t + " is even number";
            else
                return t + " is odd number";
        };

        String result = function.apply(14);
        System.out.println(result);
        //End Function

//        Predicate<String> xx = s->s.substring(0,1).equals(s.substring(s.length()));
//
//
//        String h = "haaaaah";
//
//        boolean isValid11 = xx.test(h);
//        System.out.println(isValid11);
//

        HashSet<Integer> integers1 = new HashSet<Integer>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        //intermidiates returns streams: stream() limit() distinct()
        //
        long all = integers.stream().limit(2).distinct().count();


        System.out.println(all);

        List<String> words = new ArrayList<>();
        words.add("XX");
        words.add("PoXrn");
        words.add("XXXPorn");
        words.add("XPornX");
        Consumer<String> stringConsumer = i -> System.out.println(i + "hello");
        Stream<String> filterStream = words.stream().filter(word -> word.contains("XX"));
        filterStream.forEach(stringConsumer);

        words.stream()
                .filter(word -> word.contains("XX"))
                .forEach(System.out::println);


        Stream<String> stringStream = Stream.of("Tree", "Cat", "Dog", "Human");
        String str = stringStream.collect(Collectors.joining("."));
        System.out.println(str);


        List<String> list = Arrays.asList("DDF network", "blacked", "HotLegsAndFeet", "PornHub");

        List<String> collected = list
                .stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println(collected);
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> collected1 = list1.stream().map(n -> n * 2).collect(Collectors.toList());
        System.out.println(collected1);

        Map<String,List<String>> stringListMap = words.stream()
                .collect(Collectors.groupingBy(w->w.substring(0,1)));

        for(String key:stringListMap.keySet())
            System.out.println("key : "+key + " : " + stringListMap.get(key));

        List<String> results = words.stream()
                .filter(w->w.length()>2)
                .map(w->w.substring(0,1))
                .map(w->w+"...")
                .distinct()
                .limit(10)
                .collect(Collectors.toList());
        System.out.println(results);
    }
}
