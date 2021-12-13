package com.mrfisherman.farmmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AnimalRepository repository) {
        return args -> {
            repository.save(new Animal("1234", "Miss Cow", 4, DairyAnimalSpecies.COW, Sex.FEMALE, Pregnancy.PREGNANT));
            repository.save(new Animal("4321", "Mister Goat", 2, DairyAnimalSpecies.GOAT, Sex.MALE, Pregnancy.INAPPLICABLE));

            repository.findAll().forEach(animal -> {
                log.info("Preloaded " + animal);
            });
        };
    }

}
