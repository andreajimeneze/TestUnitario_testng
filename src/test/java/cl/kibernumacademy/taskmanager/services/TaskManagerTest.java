package cl.kibernumacademy.taskmanager.services;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import cl.kibernumacademy.taskmanager.models.Task;

public class TaskManagerTest {
    private TaskManager taskManager;

    @BeforeClass
    public void setUp() {
        taskManager = new TaskManager();
    }

    @AfterClass
    public void shutDown() {
        taskManager = null;
    }

    @BeforeMethod
    public void cleanTasks() {
        taskManager.getAllTasks().clear();
    }

    @Test
   @Parameters({ "title", "description" })
    public void testAddTask(@Optional String title, @Optional String description) {
        Task task = new Task(title, description);
       
        taskManager.addTask(task);
        Assert.assertTrue(taskManager.getAllTasks().contains(task), "La tarea no fue agregada correctamente");
        Assert.assertEquals(taskManager.getAllTasks().size(), 1, "Tamaño de la lista de tareas");
        Assert.assertTrue(taskManager.getAllTasks().contains(task));
    }

    @Test
    @Parameters({ "title" })
    public void testChangeStatus_Successful(@Optional String title) {
        Task task = new Task(title, "Descripción de la tarea");
        taskManager.addTask(task);
        boolean changeSuccessful = taskManager.markAsCompleted(title);

        Assert.assertTrue(changeSuccessful, "La tarea no fue marcada como completada");
        Assert.assertEquals(task.getStatus(), Task.Status.COMPLETADA);
    }

    @Test
    @Parameters({ "title" })
    public void testChangeStatus_Failed() {
        boolean changeFailed = taskManager.markAsCompleted("Otra tarea");
        Assert.assertFalse(changeFailed, "La tarea no existe");
    }

    @Test
    public void testFilterByStatus() {
        Task task1 = new Task("Tarea 3", "Tarea a realizar");
        Task task2 = new Task("Tarea 4", "Otra tarea");

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        taskManager.markAsCompleted(task1.getTitle());

        List<Task> taskCompleted = taskManager.filterByStatus(Task.Status.COMPLETADA);
        List<Task> taskPending = taskManager.filterByStatus(Task.Status.PENDIENTE);

        SoftAssert softA = new SoftAssert();

        softA.assertTrue(taskCompleted.contains(task1));
        softA.assertTrue(taskPending.contains(task2));
        softA.assertAll();
    }
}
