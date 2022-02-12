package com.example.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.model.TodoEntity;
import com.example.demo.model.TodoRequest;
import com.example.demo.repository.TodoRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    // C
    public TodoEntity add(TodoRequest request){
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setOrder(request.getOrder());
        todoEntity.setCompleted(request.getCompleted());
        return this.todoRepository.save(todoEntity);
    }
    // R
    public TodoEntity searchById(Long id){
        return this.todoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    public List<TodoEntity> searchAll(){
        return this.todoRepository.findAll();
    }
    // U
    public TodoEntity updateById(Long id, TodoRequest request){
        TodoEntity todoEntity = this.searchById(id);
        if(request.getTitle() != null){
            todoEntity.setTitle(request.getTitle());
        }
        if(request.getOrder() != null){
            todoEntity.setOrder(request.getOrder());
        }
        if(request.getCompleted() != null){
            todoEntity.setCompleted(request.getCompleted());
        }
        return this.todoRepository.save(todoEntity);
    }
    // D
    public void deleteById(Long id){
        this.todoRepository.deleteById(id);
    }
    public void deleteAll(){
        this.todoRepository.deleteAll();
    }

}
