/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.lasthuman.apirestpierosoria.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.lasthuman.apirestpierosoria.model.TodoDTO;

import java.util.Optional;

/**
 *
 * @author Piero
 */

@Repository
public interface TodoRepository extends MongoRepository<TodoDTO, String> {

    @Query("{'todo':?0}")
    Optional<TodoDTO> findByTodo(String todo);
}
