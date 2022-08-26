package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FluxAndMonoGeneratorService {

    private static final String CLARICE = "clarice";
    private static final List<String> NAMES_LIST = List.of("allison", "ben", "chloe");
    private static final String[] FRUITS = {"Mango", "Orange"};
    private static final String[] VEGGIES = {"Tomato", "Carrot"};

    public static void main(String[] args) {
        FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();
        service.namesFlux().subscribe(name -> System.out.println("Name is: " + name));
        service.nameMono().subscribe(name -> System.out.println("Mono Name is: " + name));
    }

    public Flux<String> namesFlux(){
        return getStringFlux().log();    // flux should be coming from db or remote service
    }

    public Flux<String> namesFluxMap(){
        return getStringFlux()
                .map(String::toUpperCase)
                .log();    // flux should be coming from db or remote service
    }

    public Flux<String> namesFluxFlatMapAsync(){
        return getStringFlux()
                .flatMap(name-> Flux.just(name.split(""))
                        .delayElements(Duration.ofMillis(new Random().nextInt(1000))))
                .log();    // flux should be coming from db or remote service
    }

    public Flux<String> namesFluxConcatMap(){
        return getStringFlux()
                .concatMap(name-> Flux.just(name.split(""))
                        .delayElements(Duration.ofMillis(new Random().nextInt(1000))))
                .log();    // flux should be coming from db or remote service
    }

    public Flux<String> namesFluxSwitch(int size){
        return getStringFlux()
                .transform(data -> data.filter(name-> name.length() > size))
                //.filter(name-> name.length() > size)
                //.defaultIfEmpty("No data found")
                .switchIfEmpty(Flux.just("No data found", "Really that is nothing here", "Go away"))
                .log();    // flux should be coming from db or remote service
    }

    // Join two datasource's info
    public Flux<String> namesFluxConcat(){
        /*return Flux.concat(Flux.just("Mango", "Orange"),
                        (Flux.just("Tomato", "Carrot")))
                .log();*/
        return Flux.just(FRUITS)
                .concatWith(Flux.just("Tomato", "Carrot"))
                .log();
    }

    // Subscribe to two datasource asynchronously
    public Flux<String> namesFluxMerge(){
        /*return Flux.merge(Flux.just("Mango", "Orange"),
                        (Flux.just("Tomato", "Carrot")))
                .log();*/
        return Flux.just(FRUITS).delayElements(Duration.ofMillis(50))
                .mergeWith(Flux.just(VEGGIES).delayElements(Duration.ofMillis(75)))
                .log();
    }

    public Flux<String> namesFluxZip(){
        return Flux.zip(Flux.just(FRUITS),
                        Flux.just(VEGGIES),
                        Flux.just("Beans", "Potato"))
                .map(objects -> objects.getT1() + objects.getT2() + objects.getT3())
                .log();
/*        return Flux.just("Mango", "Orange")
                .zipWith(Flux.just("Tomato", "Carrot"), (first, second) -> first + second)
                .log();*/
    }

    public Flux<String> namesFluxDo(int size){
        return getStringFlux()
                .transform(data -> data.filter(name-> name.length() > size))
                .filter(name-> name.length() > size)
                .doOnSubscribe(s -> System.out.println("Subscribed to " +  s.toString()))
                .doOnNext(name-> System.out.println("Got a name: " + name))
                .doOnComplete(() -> System.out.println("Completed!!!"))
                .log();    // flux should be coming from db or remote service
    }

    public Flux<String> namesFluxHandlingError(){
        return getStringFlux()
                .map(name -> {
                    if (name.equals("ben"))
                        throw new RuntimeException("exception occurred for one element: " + name);
                    return name.toUpperCase();
                })
                //.onErrorReturn("Opsie!")
/*                .onErrorContinue((error, something) -> {
                    System.out.println("error: " + error);
                    System.out.println("on element " + something);
                })*/
                .onErrorMap(error -> {
                    System.out.println("error" + error);
                    return new IllegalStateException("bad item");
                })
                //.doOnError(error -> System.out.println("error" + error)) //this is like a try-catch-throw same exception
                .log();
    }

    private Flux<String> getStringFlux() {
        return Flux.fromIterable(NAMES_LIST);
    }

    public Mono<String> nameMono(){
        return Mono.just(CLARICE).log();
    }

    public Mono<List<String>> nameMonoFlatMap(){
        return Mono.just(CLARICE)
                .flatMap(name -> Mono.just(List.of(name.split(""))))
                .log();
    }

    //flatMapMany transform Mono to FLux
    public Flux<String> nameMonoFlatMapMany(){
        return Mono.just(CLARICE)
                .flatMapMany(name -> Flux.just(name.split("")))
                .log();
    }

    //concatenating Monos result to FLux
    public Flux<String> nameMonoConcat(){
        return Mono.just(CLARICE).
                concatWith(Mono.just("Octavio"))
                .log();
    }

    public Mono<String> nameMonoZip(){
        return Mono.just(CLARICE)
                .zipWith(Mono.just("Ben"), (first, second) -> first + second)
                .log();
    }
}
