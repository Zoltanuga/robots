package by.robots.service;


import by.robots.model.robot.RobotTracker;

import by.robots.model.task.Task;
import by.robots.model.task.TaskProcessLogger;
import by.robots.model.viewObjects.EventLogViewObject;
import by.robots.model.robot.Robot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service("eventLogService")
public class EventLogServiceImpl implements EventLogService {
    private TaskProcessLogger logger = TaskProcessLogger.getInstance(30);
    @Autowired
    private RobotTracker tracker ;


    @Override
    public ConcurrentLinkedQueue<EventLogViewObject> findAllEvents() {
        return logger.getLog();
    }

    @Override
    public ConcurrentLinkedQueue<Robot> findAllRobots() {
        return tracker.getRobots();
    }

    @Override
    public boolean addTask(Task task) {
       return tracker.addTask(task);
    }

    @Override
    public BlockingQueue<Task> findAllTasks() {
        return tracker.getTasks();
    }
}
