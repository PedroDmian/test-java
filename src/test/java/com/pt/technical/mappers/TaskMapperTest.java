package com.pt.technical.mappers;

import com.pt.technical.domain.models.Task;
import com.pt.technical.domain.models.TaskLine;
import com.pt.technical.infrastructure.dto.TaskLineRequest;
import com.pt.technical.infrastructure.dto.TaskRequest;
import com.pt.technical.infrastructure.mappers.TaskMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TaskMapperTest {
    private TaskMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new TaskMapper();
    }

    @Test
    void testToModelWithLines() {
        TaskLineRequest line1 = new TaskLineRequest();
        line1.setName("Linea 1");
        line1.setDescription("Descripción 1");

        TaskLineRequest line2 = new TaskLineRequest();
        line2.setName("Linea 2");
        line2.setDescription("Descripción 2");

        TaskRequest request = new TaskRequest();
        request.setTitle("Mi tarea");
        request.setDescription("Descripción principal");
        request.setCompleted(true);
        request.setLines(List.of(line1, line2));

        Task result = mapper.toModel(request);

        assertNotNull(result);
        assertEquals("Mi tarea", result.getTitle());
        assertEquals("Descripción principal", result.getDescription());
        assertTrue(result.isCompleted());
        assertEquals(2, result.getLines().size());

        TaskLine mappedLine1 = result.getLines().get(0);
        assertNull(mappedLine1.getId());
        assertEquals("Linea 1", mappedLine1.getName());
        assertEquals("Descripción 1", mappedLine1.getDescription());
    }

    @Test
    void testToModelWithNullLines() {
        TaskRequest request = new TaskRequest();
        request.setTitle("Tarea sin líneas");
        request.setDescription("Sin descripción");
        request.setCompleted(false);
        request.setLines(null);

        Task result = mapper.toModel(request);

        assertNotNull(result);
        assertEquals("Tarea sin líneas", result.getTitle());
        assertEquals("Sin descripción", result.getDescription());
        assertFalse(result.isCompleted());
        assertNotNull(result.getLines());
        assertTrue(result.getLines().isEmpty());
    }
}
