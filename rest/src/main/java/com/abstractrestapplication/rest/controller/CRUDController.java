package com.abstractrestapplication.rest.controller;

import handlers.CRUDHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

public abstract class CRUDController<T, Handler extends CRUDHandler, Identifier extends Serializable> {

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestParam T entity){
        return new ResponseEntity<String>("Object created", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<T> get(@RequestParam Identifier id) throws InstantiationException, IllegalAccessException{
        return new ResponseEntity<T>((T) CRUDHandler.class.newInstance().read(id), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestParam T entity){
        return new ResponseEntity<String>("Object updated", HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestParam Identifier id){
        return new ResponseEntity<String>("Object deleted", HttpStatus.OK);
    }
}