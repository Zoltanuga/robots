package by.robots.model.task;


import by.robots.model.robot.Robot;
import by.robots.model.viewObjects.EventLogViewObject;
import by.robots.model.viewObjects.ProcessStatus;

import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskProcessLogger {
    private static TaskProcessLogger instance;
    private ConcurrentLinkedQueue<EventLogViewObject> log;
    private int capacity;

    private TaskProcessLogger(int capacity) {
        this.capacity = capacity;
        log = new ConcurrentLinkedQueue<>();
    }

    public static synchronized TaskProcessLogger getInstance(int capacity) {
        if (instance == null) {
            instance = new TaskProcessLogger(capacity);
        }
        return instance;
    }

    public ConcurrentLinkedQueue getLog() {
        return log;
    }

    public void setLog(ConcurrentLinkedQueue log) {
        this.log = log;
    }


    public void log(Task task, Robot robot, ProcessStatus processStatus) {
        log.add(new EventLogViewObject(robot, task, processStatus));
        if (log.size() > capacity) {
            log.poll();
        }
    }
}



