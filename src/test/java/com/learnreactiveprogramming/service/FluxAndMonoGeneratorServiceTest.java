package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class FluxAndMonoGeneratorServiceTest {

    FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux(){
        var namesFlux = service.namesFlux();

        StepVerifier.create(namesFlux)
                .expectNext("allison")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void nameMono(){
        var nameMono = service.nameMono();

        StepVerifier.create(nameMono)
                .expectNext("clarice")
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMap(){
        var nameMono = service.nameMonoFlatMap();

        StepVerifier.create(nameMono)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void nameMonoFlatMapMany(){
        var nameMono = service.nameMonoFlatMapMany();

        StepVerifier.create(nameMono)
                .expectNextCount(7)
                .verifyComplete();
    }

    @Test
    void namesFluxMap(){
        var namesFlux = service.namesFluxMap();

        StepVerifier.create(namesFlux)
                .expectNext("ALLISON", "BEN", "CHLOE")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMap(){
        var namesFlux = service.namesFluxFlatMapAsync();

        StepVerifier.create(namesFlux)
                .expectNextCount(15)
                .verifyComplete();
    }

    @Test
    void namesFluxConcatMap(){
        var namesFlux = service.namesFluxConcatMap();

        StepVerifier.create(namesFlux)
                .expectNextCount(15)
                .verifyComplete();
    }

    @Test
    void namesFluxDefault(){
        var namesFlux = service.namesFluxSwitch(10);

        StepVerifier.create(namesFlux)
                .expectNext("No data found")
                .expectNext("Really that is nothing here")
                .expectNext("Go away")
                .verifyComplete();
    }

    @Test
    void namesFluxConcat(){
        var namesFlux = service.namesFluxConcat();

        StepVerifier.create(namesFlux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void namesFluxMerge(){
        var namesFlux = service.namesFluxMerge();

        StepVerifier.create(namesFlux)
                .expectNextCount(4)
                .verifyComplete();
    }

    @Test
    void namesFluxZip(){
        var namesFlux = service.namesFluxZip();

        StepVerifier.create(namesFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void namesFluxDo(){
        var namesFlux = service.namesFluxDo(3);

        StepVerifier.create(namesFlux)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void namesFluxError(){
        var namesFlux = service.namesFluxHandlingError();

        StepVerifier.create(namesFlux)
                .expectNext("ALLISON")
                //.expectNext("ben")
                //.expectNext("CHLOE")
                //.expectNext("Opsie!")
                //.verifyComplete();
                .expectError(IllegalStateException.class)
                .verify();
    }
}
