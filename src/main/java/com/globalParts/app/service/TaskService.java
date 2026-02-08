package com.globalParts.app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.globalParts.app.domain.entity.TaskEntity;
import com.globalParts.app.domain.enums.TaskStatus;
import com.globalParts.app.dto.request.CreateTaskRequest;
import com.globalParts.app.dto.request.UpdateTaskRequest;
import com.globalParts.app.dto.response.TaskResponse;
import com.globalParts.app.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;

    public Map<String, List<TaskResponse>> getKanbanBoard() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.groupingBy(
                        task -> task.status().name()
                ));
    }

    public void create(CreateTaskRequest request) {
        repository.save(TaskEntity.builder()
                .title(request.title())
                .description(request.description())
                .priority(request.priority())
                .status(request.status())
                .date(request.date().atStartOfDay())
                .build());
    }

    public void update(Long id, UpdateTaskRequest request) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setPriority(request.priority());
        task.setStatus(request.status());
        task.setDate(request.date().atStartOfDay());

        repository.save(task);
    }

    public void changeStatus(Long id) {
        TaskEntity task = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        TaskStatus next =
                task.getStatus() == TaskStatus.TODO ? TaskStatus.DOING :
                task.getStatus() == TaskStatus.DOING ? TaskStatus.DONE :
                TaskStatus.TODO;

        task.setStatus(next);
        repository.save(task);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private TaskResponse toResponse(TaskEntity entity) {
        LocalDate dueDate = null;
        if (entity.getDate() != null) {
            dueDate = entity.getDate().toLocalDate();
        }

        TaskResponse response = new TaskResponse(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getPriority(),
                entity.getStatus(),
                dueDate
        );
        return response;
    }
}
