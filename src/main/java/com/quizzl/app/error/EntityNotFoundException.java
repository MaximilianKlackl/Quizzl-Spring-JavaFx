package com.quizzl.app.error;

public class EntityNotFoundException extends Exception{

    EntityNotFoundException(String error){
        super(error);
    }
}
