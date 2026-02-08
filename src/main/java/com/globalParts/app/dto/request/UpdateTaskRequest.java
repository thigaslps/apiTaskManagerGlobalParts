package com.globalParts.app.dto.request;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globalParts.app.domain.enums.TaskPriority;
import com.globalParts.app.domain.enums.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateTaskRequest(

        @NotBlank String title,
        String description,

        @NotNull TaskPriority priority,
        @NotNull TaskStatus status,
        @JsonFormat(pattern = "yyyy-MM-dd") @NotNull LocalDate date
) {}
