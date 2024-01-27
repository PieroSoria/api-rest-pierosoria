/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.lasthuman.apirestpierosoria.services;

import java.util.List;

import com.lasthuman.apirestpierosoria.exception.TodoCollectionException;
import com.lasthuman.apirestpierosoria.model.TodoDTO;

import jakarta.validation.ConstraintViolationException;



/**
 *
 * @author Piero
 */
public interface TodoServices {
    public void createTodo(TodoDTO todo)  throws ConstraintViolationException ,TodoCollectionException;

    public List<TodoDTO> getAllTodos();

    public TodoDTO getSingleTodo(String id) throws TodoCollectionException;

    public void updatetodo(String id, TodoDTO todo) throws TodoCollectionException;

    public void deletetodo(String id) throws TodoCollectionException;
}
