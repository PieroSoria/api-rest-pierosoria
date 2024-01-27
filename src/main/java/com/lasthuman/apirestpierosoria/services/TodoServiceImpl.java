/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.lasthuman.apirestpierosoria.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lasthuman.apirestpierosoria.exception.TodoCollectionException;
import com.lasthuman.apirestpierosoria.model.TodoDTO;
import com.lasthuman.apirestpierosoria.repository.TodoRepository;

import jakarta.validation.ConstraintViolationException;

/**
 *
 * @author Piero
 */
@Service
 public class TodoServiceImpl implements TodoServices {
    @Autowired
    private TodoRepository todoRepo;

    @Override
    public void createTodo(TodoDTO todo) throws ConstraintViolationException ,TodoCollectionException {
        Optional<TodoDTO> todoOptional = todoRepo.findByTodo(todo.getTodo());
        if(todoOptional.isPresent()){
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
        }else{
            todo.setCreateAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todo);
        }
    }

    @Override
    public List<TodoDTO> getAllTodos() {
        List<TodoDTO> todos = todoRepo.findAll();
        if(!todos.isEmpty()){
            return todos;
        }else{
            return new ArrayList<TodoDTO>();
        }
    }

    @Override
    public TodoDTO getSingleTodo(String id) throws TodoCollectionException {
        Optional<TodoDTO> optionaltodo = todoRepo.findById(id);
        if(!optionaltodo.isPresent()){
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }else{
            return optionaltodo.get();
        }
    }

    @Override
    public void updatetodo(String id , TodoDTO todo) throws TodoCollectionException {
        Optional<TodoDTO> todowithid = todoRepo.findById(id);
        Optional<TodoDTO> todowithSameName = todoRepo.findByTodo(todo.getTodo());        
        if(todowithid.isPresent()){

            if(todowithSameName.isPresent() && todowithSameName.get().getId().equals(id)){
                throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExists());
            }
            TodoDTO todoUpdate = todowithid.get();
            todoUpdate.setTodo(todo.getTodo());
            todoUpdate.setDescription(todo.getDescription());
            todoUpdate.setCompleted(todo.getCompleted());
            todoUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todoUpdate);
        }else{
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id)); 
        }
    }

    @Override
    public void deletetodo(String id) throws TodoCollectionException {
        Optional<TodoDTO> todOptional = todoRepo.findById(id);
        if(!todOptional.isPresent()){
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }else{
            todoRepo.deleteById(id);
        }
    }
}
