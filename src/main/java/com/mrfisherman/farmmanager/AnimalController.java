package com.mrfisherman.farmmanager;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class AnimalController {

    private final AnimalRepository repository;
    private final AnimalModelAssembler assembler;

    public AnimalController(AnimalRepository repository, AnimalModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/animals")
    CollectionModel<EntityModel<Animal>> all() {
        List<EntityModel<Animal>> animals = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(animals,
                linkTo(methodOn(AnimalController.class).all()).withSelfRel());
    }

    @PostMapping("/animals")
    ResponseEntity<?> newAnimal(@RequestBody Animal newAnimal) {
        EntityModel<Animal> entityModel = assembler
                .toModel(repository.save(newAnimal));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/animals/{id}")
    EntityModel<Animal> one(@PathVariable String id) {
        Animal animal = repository.findById(id)
                .orElseThrow(() -> new AnimalNotFoundException(id));

        return assembler.toModel(animal);
    }

    @PutMapping("/animals/{id}")
    ResponseEntity<?> replaceAnimal(@RequestBody Animal newAnimal,
                                      @PathVariable String id) {
        Animal updatedAnimal = repository.findById(id)
                .map(animal -> {
                    animal.setName(newAnimal.getName());
                    animal.setAge(newAnimal.getAge());
                    animal.setSpecies(newAnimal.getSpecies());
                    animal.setSex(newAnimal.getSex());
                    animal.setPregnancy(newAnimal.getPregnancy());
                    return repository.save(animal);
                })
                .orElseGet(() -> {
                    newAnimal.setId(id);
                    return repository.save(newAnimal);
                });

        EntityModel<Animal> entityModel = assembler.toModel(updatedAnimal);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

    }

    @DeleteMapping("/animals/{id}")
    ResponseEntity<?> deleteAnimal(@PathVariable String id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
