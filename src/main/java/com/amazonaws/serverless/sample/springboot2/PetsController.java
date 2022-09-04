/*
 * Copyright 2016 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package com.amazonaws.serverless.sample.springboot2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class PetsController {
    @PostMapping(path = "/pets",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Pet createPet(@RequestBody Pet newPet) {
        log.info("Request payload {}", newPet);
        if (newPet.getName() == null || newPet.getBreed() == null) {
            return null;
        }

        newPet.setId(UUID.randomUUID().toString());
        return newPet;
    }

    @GetMapping(path = "/pets", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pet> listPets() {
        log.info("New request for get all");
        List<Pet> pets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            pets.add(new Pet()
                    .setId(UUID.randomUUID().toString())
                    .setName(PetData.getRandomName())
                    .setBreed(PetData.getRandomBreed())
                    .setDateOfBirth(PetData.getRandomDoB()));
        }
        log.info("Response {}", pets);
        return pets;
    }

    @GetMapping(path = "/pets/{petId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Pet getPetById(@PathVariable String petId) {
        log.info("Request {}", petId);
        return new Pet()
                .setId(petId)
                .setBreed(PetData.getRandomBreed())
                .setDateOfBirth(PetData.getRandomDoB())
                .setName(PetData.getRandomName());
    }

}
