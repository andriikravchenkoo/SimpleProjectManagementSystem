package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.facade;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.TaskDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.enums.TaskStatus;

import java.util.List;

public interface TaskFacade {

    List<TaskDto> processFindAllTasks();

    List<TaskDto> processFindAllTasksByUser();

    TaskDto processFindTaskById(Long taskId);

    TaskDto processSaveTask(TaskDto taskDtoRequest);

    void processUpdateStatusTask(TaskStatus taskStatus, Long taskId);
}
