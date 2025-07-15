package cl.kibernumacademy.taskmanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cl.kibernumacademy.taskmanager.models.Task;

public class TaskManager {
    private List<Task> tasksList = new ArrayList<>();

    public void addTask(Task task) {
        tasksList.add(task);
    }

    public boolean markAsCompleted(String title) {
        if(title == null) {
            return false;
        }

        for(Task task : tasksList) {
            if(task != null && task.getTitle().equals(title)) { 
                task.changeStatus();
                return true;
            }
        }
        return false;
    }

    public List<Task> filterByStatus(Task.Status status) {
        return tasksList.stream()
                .filter(task -> task.getStatus() == status)
                .collect(Collectors.toList());
    }

    public List<Task> getAllTasks() {
        return tasksList;
    }
}
