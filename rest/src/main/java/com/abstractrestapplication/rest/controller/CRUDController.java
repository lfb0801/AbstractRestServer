package com.abstractrestapplication.rest.controller;

import handlers.CRUDHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Collection;

public abstract class CRUDController<T, Identifier extends Serializable> {

    private static CRUDHandler handler;

    public CRUDController(CRUDHandler _handler){
        handler = _handler;
    }

    @PostMapping("/")
    public ResponseEntity<String> create(@RequestParam T entity){
        handler.create(entity);
        return new ResponseEntity<String>("Object created", HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<T> get(@RequestParam Identifier id){
        return new ResponseEntity<>((T) handler.read(id), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Collection<T>> getAll(){
        return new ResponseEntity<>((Collection<T>) handler.readAll(), HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<String> update(@RequestParam T entity){
        handler.update(entity);
        return new ResponseEntity<String>("Object updated", HttpStatus.OK);
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete(@RequestParam Identifier id){
        handler.delete(handler.read(id));
        return new ResponseEntity<String>("Object deleted", HttpStatus.OK);
    }
}