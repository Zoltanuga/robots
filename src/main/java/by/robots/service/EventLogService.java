package by.robots.service;


import by.robots.model.task.Task;
import by.robots.model.viewObjects.EventLogViewObject;
import by.robots.model.robot.Robot;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public interface EventLogService {
    ConcurrentLinkedQueue<EventLogViewObject> findAllEvents();
    ConcurrentLinkedQueue<Robot> findAllRobots();
    boolean addTask(Task task);
    BlockingQueue<Task> findAllTasks();
}
