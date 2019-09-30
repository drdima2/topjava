package ru.javawebinar.topjava;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) throws IOException {
        execute(() -> System.out.println("Hello Topjava Enterprise!") );
        //consume(s -> System.out.println(s),"ccc");
        consume(System.out::println,"ccc");

        Path dir=Paths.get("c:\\__testjs");
        Stream<Path> stream = Files.walk(dir);
        stream
                .filter(p -> p.toString().endsWith("html"))
                .forEach(System.out::println);

    }

    private static void execute(Runnable runnable){
        System.out.println("Start runner");
        runnable.run();
        System.out.println("End runner");
    }

    private static void consume(Consumer<String> consumer, String out){
        System.out.println("Start consume");
        consumer.accept(out);
        System.out.println("End consume");
    }
}
