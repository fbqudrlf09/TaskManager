package com.example.taskmanagerapplication.dto;

import com.example.taskmanagerapplication.entity.Task;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class FindTasksResponseDto {
    private List<TaskDto> taskDtoList;

    public FindTasksResponseDto(List<Task> tasks) {

        taskDtoList = new ArrayList<>(tasks.size());

        for (Task task : tasks) {
            TaskDto dto = new TaskDto(task.getId(), task.getTaskname(), task.getUsername(), task.getCreatDate(), task.getUpdateDate());
            taskDtoList.add(dto);
        }
    }
}
