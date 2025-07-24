package com.pt.technical.infrastructure.mappers;

import com.pt.technical.domain.models.Task;
import com.pt.technical.domain.models.TaskLine;
import com.pt.technical.infrastructure.dto.TaskRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {
    public Task toModel(TaskRequest request) {
        List<TaskLine> lines = request.getLines() == null ?
            new ArrayList<>() : request
                .getLines()
                .stream()
                .map(line ->
                    new TaskLine(
                        null,
                        line.getName(),
                        line.getDescription()
                    )
                ).toList();

        return new Task(
        null,
            request.getTitle(),
            request.getDescription(),
            request.isCompleted(),
            null,
            null,
            null,
            lines
        );
    }
}
