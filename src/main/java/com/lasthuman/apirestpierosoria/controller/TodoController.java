/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.lasthuman.apirestpierosoria.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lasthuman.apirestpierosoria.model.TodoDTO;
import com.lasthuman.apirestpierosoria.services.TodoServices;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.lasthuman.apirestpierosoria.exception.TodoCollectionException;

import jakarta.validation.ConstraintViolationException;




/**
 *
 * @author Piero
 */

@RestController
public class TodoController {


    @Autowired
    private TodoServices todoServices;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos(){
        List<TodoDTO> todos = todoServices.getAllTodos();
        return new ResponseEntity<>(todos, !todos.isEmpty() ? HttpStatus.OK:HttpStatus.NOT_FOUND);
    }

    @PostMapping("/todos")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todo){
        try {
            todoServices.createTodo(todo);
            return new ResponseEntity<TodoDTO>(todo,HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id){
        try {
            return new ResponseEntity<>(todoServices.getSingleTodo(id),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<?> updatebyId(@PathVariable("id") String id,@RequestBody TodoDTO todo) {
       try {
        todoServices.updatetodo(id, todo);
        return new ResponseEntity<>("Update Todo with id "+ id,HttpStatus.OK);
       } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
       } catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deletebyId(@PathVariable("id") String id){
        try {
            todoServices.deletetodo(id);
            return new ResponseEntity<>("Successfully delete todo with id" + id,HttpStatus.OK);
        } catch (TodoCollectionException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        
    }
    
}
