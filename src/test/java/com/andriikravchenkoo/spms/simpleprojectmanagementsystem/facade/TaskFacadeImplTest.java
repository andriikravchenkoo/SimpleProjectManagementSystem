package com.andriikravchenkoo.spms.simpleprojectmanagementsystem.facade;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.dto.TaskDto;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.exception.UnauthorizedAccessException;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.facade.impl.TaskFacadeImpl;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.Project;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.Task;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.entity.User;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.model.enums.TaskStatus;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.ProjectService;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.TaskService;
import com.andriikravchenkoo.spms.simpleprojectmanagementsystem.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TaskFacadeImplTest {

    @Mock private TaskService taskService;

    @Mock private ProjectService projectService;

    @Mock private UserService userService;

    @InjectMocks private TaskFacadeImpl taskFacade;

    @Test
    void testShouldFindAllTasks() {
        Long expectedId = 1L;
        Task expectedTask = new Task();
        expectedTask.setId(expectedId);
        Project expectedProject = new Project();
        expectedProject.setId(expectedId);
        User expectedUser = new User();
        expectedUser.setId(expectedId);
        expectedTask.setProject(expectedProject);
        expectedTask.setAssignedUser(expectedUser);

        when(taskService.findAll()).thenReturn(Collections.singletonList(expectedTask));
        when(projectService.findByTaskId(expectedTask.getId())).thenReturn(expectedProject);
        when(userService.findByTaskId(expectedTask.getId())).thenReturn(expectedUser);

        TaskDto taskDto = new TaskDto();
        taskDto.setId(expectedId);
        taskDto.setProjectId(expectedId);
        taskDto.setAssignedUserId(expectedId);

        List<TaskDto> result = taskFacade.processFindAllTasks();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1, result.size()),
                () -> assertEquals(taskDto.getId(), result.get(0).getId()),
                () -> assertEquals(taskDto.getProjectId(), result.get(0).getProjectId()),
                () -> assertEquals(taskDto.getAssignedUserId(), result.get(0).getAssignedUserId()));

        verify(taskService).findAll();
        verify(projectService).findByTaskId(expectedId);
        verify(userService).findByTaskId(expectedId);
    }

    @Test
    void testShouldFindAllTasksByUser() {
        Long expectedId = 1L;
        Task expectedTask = new Task();
        expectedTask.setId(expectedId);
        Project expectedProject = new Project();
        expectedProject.setId(expectedId);
        User expectedUser = new User();
        expectedUser.setId(expectedId);
        expectedTask.setProject(expectedProject);
        expectedTask.setAssignedUser(expectedUser);

        when(taskService.findAllByUser()).thenReturn(Collections.singletonList(expectedTask));
        when(projectService.findByTaskId(expectedId)).thenReturn(expectedProject);
        when(userService.findByTaskId(expectedId)).thenReturn(expectedUser);

        TaskDto taskDto = new TaskDto();
        taskDto.setId(expectedId);
        taskDto.setProjectId(expectedId);
        taskDto.setAssignedUserId(expectedId);

        List<TaskDto> result = taskFacade.processFindAllTasksByUser();

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(1, result.size()),
                () -> assertEquals(taskDto.getId(), result.get(0).getId()),
                () -> assertEquals(taskDto.getProjectId(), result.get(0).getProjectId()),
                () -> assertEquals(taskDto.getAssignedUserId(), result.get(0).getAssignedUserId()));

        verify(taskService).findAllByUser();
        verify(projectService).findByTaskId(expectedId);
        verify(userService).findByTaskId(expectedId);
    }

    @Test
    void testShouldFindTaskById() {
        Long expectedId = 1L;
        Task expectedTask = new Task();
        expectedTask.setId(expectedId);
        Project expectedProject = new Project();
        expectedProject.setId(expectedId);
        User expectedUser = new User();
        expectedUser.setId(expectedId);
        expectedTask.setProject(expectedProject);
        expectedTask.setAssignedUser(expectedUser);

        when(taskService.findById(expectedId)).thenReturn(expectedTask);
        when(projectService.findByTaskId(expectedId)).thenReturn(expectedProject);
        when(userService.findByTaskId(expectedId)).thenReturn(expectedUser);

        TaskDto taskDto = new TaskDto();
        taskDto.setId(expectedId);
        taskDto.setProjectId(expectedId);
        taskDto.setAssignedUserId(expectedId);

        TaskDto result = taskFacade.processFindTaskById(expectedId);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(expectedTask.getId(), result.getId()),
                () -> assertEquals(taskDto.getId(), result.getId()),
                () -> assertEquals(taskDto.getProjectId(), result.getProjectId()),
                () -> assertEquals(taskDto.getAssignedUserId(), result.getAssignedUserId()));

        verify(taskService).findById(expectedId);
        verify(projectService).findByTaskId(expectedId);
        verify(userService).findByTaskId(expectedId);
    }

    @Test
    void testShouldSaveTask() {
        Long expectedId = 1L;
        TaskDto expectedTaskDtoRequest = new TaskDto();
        expectedTaskDtoRequest.setProjectId(expectedId);
        expectedTaskDtoRequest.setAssignedUserId(expectedId);

        Project expectedProject = new Project();
        expectedProject.setId(expectedId);

        User expectedAssignedUser = new User();
        expectedAssignedUser.setId(expectedId);

        Task expectedTask = new Task();
        expectedTask.setId(expectedId);
        expectedTask.setProject(expectedProject);
        expectedTask.setAssignedUser(expectedAssignedUser);

        when(projectService.findById(expectedId)).thenReturn(expectedProject);
        when(userService.findById(expectedId)).thenReturn(expectedAssignedUser);
        when(taskService.save(any(Task.class))).thenReturn(expectedTask);
        when(projectService.findByTaskId(expectedId)).thenReturn(expectedProject);
        when(userService.findByTaskId(expectedId)).thenReturn(expectedAssignedUser);

        TaskDto result = taskFacade.processSaveTask(expectedTaskDtoRequest);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(expectedTask.getId(), result.getId()),
                () -> assertEquals(expectedProject.getId(), result.getProjectId()),
                () -> assertEquals(expectedAssignedUser.getId(), result.getAssignedUserId()));

        verify(projectService).findById(expectedId);
        verify(userService).findById(expectedId);
        verify(taskService).save(any(Task.class));
        verify(projectService).findByTaskId(expectedId);
        verify(userService).findByTaskId(expectedId);
    }

    @Test
    void testShouldUpdateStatusTaskUnauthorizedAccess() {
        Long expectedId = 1L;
        TaskStatus status = TaskStatus.DONE;

        when(taskService.isTaskAssignedToUser(expectedId)).thenReturn(false);

        assertThrows(
                UnauthorizedAccessException.class,
                () -> {
                    taskFacade.processUpdateStatusTask(status, expectedId);
                });

        verify(taskService).isTaskAssignedToUser(expectedId);
        verifyNoMoreInteractions(taskService);
    }

    @Test
    void testShouldUpdateStatusTask() {
        Long expectedId = 1L;
        TaskStatus status = TaskStatus.DONE;

        when(taskService.isTaskAssignedToUser(expectedId)).thenReturn(true);

        assertDoesNotThrow(() -> taskFacade.processUpdateStatusTask(status, expectedId));

        verify(taskService).isTaskAssignedToUser(expectedId);
        verify(taskService).updateStatus(status, expectedId);
    }
}
