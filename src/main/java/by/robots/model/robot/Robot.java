package by.robots.model.robot;


import by.robots.model.task.Task;
import by.robots.model.task.TaskType;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface Robot extends Runnable {
    List<TaskType> getEnabledTasks();

    int getRobotId();

    void doTask();

    void setPowerLevel(int powerLevel);

    void addTask(Task task);

    boolean isBusy();

    boolean isDead();

    void selfDestruct();

    ConcurrentLinkedQueue<Task> getAssignedTasks();

    void setAssignedTasks(ConcurrentLinkedQueue<Task> currentTask);

}
