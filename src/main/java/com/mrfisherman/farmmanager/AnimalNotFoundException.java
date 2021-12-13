package com.mrfisherman.farmmanager;

class AnimalNotFoundException extends RuntimeException {

    AnimalNotFoundException(String id) {
        super("Could not find animal " + id);
    }
}
