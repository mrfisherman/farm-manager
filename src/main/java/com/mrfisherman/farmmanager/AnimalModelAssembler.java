package com.mrfisherman.farmmanager;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class AnimalModelAssembler implements RepresentationModelAssembler<Animal, EntityModel<Animal>> {

    @Override
    public EntityModel<Animal> toModel(Animal animal) {
        EntityModel<Animal> animalModel = EntityModel.of(animal,
                linkTo(methodOn(AnimalController.class).one(animal.getId())).withSelfRel(),
                linkTo(methodOn(AnimalController.class).all()).withRel("animals"));

        return animalModel;
    }

}
