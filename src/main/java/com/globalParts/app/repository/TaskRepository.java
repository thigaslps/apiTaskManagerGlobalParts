package com.globalParts.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.globalParts.app.domain.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
