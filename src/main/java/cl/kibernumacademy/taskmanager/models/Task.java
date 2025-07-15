package cl.kibernumacademy.taskmanager.models;

public class Task {
    private String title;
    private String description;
    private Status status;

    public enum Status {
        PENDIENTE,
        COMPLETADA
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = Status.PENDIENTE;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public void changeStatus() {
        this.status = Status.COMPLETADA;
    }

    
}
