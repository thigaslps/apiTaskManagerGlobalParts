package com.globalParts.app.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globalParts.app.dto.request.CreateTaskRequest;
import com.globalParts.app.dto.request.UpdateTaskRequest;
import com.globalParts.app.dto.response.KanbanResponse;
import com.globalParts.app.service.TaskService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class KanbanController {

    private final TaskService service;

    @GetMapping("/kanbanBoard")
    public ResponseEntity<KanbanResponse> getBoard() {
        return ResponseEntity.ok(
                new KanbanResponse(service.getKanbanBoard(), "Dados carregados com sucesso")
        );
    }

    @PostMapping("/addTask")
    public ResponseEntity<?> add(@Valid @RequestBody CreateTaskRequest request) {
        service.create(request);
        return ResponseEntity.status(201).body(
                Map.of("message", "Tarefa adicionada com sucesso")
        );
    }

    @PutMapping("/editTask/{id}")
    public ResponseEntity<?> edit(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTaskRequest request
    ) {
        service.update(id, request);
        return ResponseEntity.ok(
                Map.of("message", "Tarefa editada com sucesso")
        );
    }

    @PutMapping("/changeStatusTask/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id) {
        service.changeStatus(id);
        return ResponseEntity.ok(
                Map.of("message", "Status atualizado com sucesso")
        );
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(
                Map.of("message", "Tarefa excluída com sucesso")
        );
    }
}
