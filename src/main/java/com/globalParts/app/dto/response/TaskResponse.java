package com.globalParts.app.dto.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.globalParts.app.domain.enums.TaskPriority;
import com.globalParts.app.domain.enums.TaskStatus;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskPriority priority,
        TaskStatus status,
        @JsonFormat(pattern = "yyyy-MM-dd") LocalDate date
) {}
