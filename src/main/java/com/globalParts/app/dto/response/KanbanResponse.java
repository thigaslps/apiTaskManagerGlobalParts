package com.globalParts.app.dto.response;

import java.util.List;
import java.util.Map;

public record KanbanResponse(
        Map<String, List<TaskResponse>> response,
        String message
) {}
