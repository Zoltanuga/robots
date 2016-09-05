package by.robots.model.robot.impl;

import by.robots.model.robot.AbstractRobot;
import by.robots.model.task.TaskType;
import by.robots.model.viewObjects.ProcessStatus;

import java.util.List;
import java.util.concurrent.TimeUnit;


public class Cleaner extends AbstractRobot {

    public Cleaner(int robotId, List<TaskType> enabledTasks) {
        super(robotId, enabledTasks);
    }

    @Override
    public void run() {
        getLogger().log(getCurrentTask(), this, ProcessStatus.IN_PROCESS);
        getCurrentTask().setTimeComplexity(getCurrentTask().getTimeComplexity() / getPowerLevel());
        try {
            TimeUnit.SECONDS.sleep(getCurrentTask().getTimeComplexity());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getLogger().log(getCurrentTask(), this, ProcessStatus.COMPLETE);
        finishMaintenance();
    }

    @Override
    public String toString() {
        return "Cleaner" + super.toString();
    }
}
