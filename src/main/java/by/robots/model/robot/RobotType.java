package by.robots.model.robot;


import by.robots.model.robot.impl.Bender;
import by.robots.model.robot.impl.Cleaner;
import by.robots.model.robot.impl.Washer;
import by.robots.model.task.TaskType;

import java.util.ArrayList;
import java.util.List;

public enum RobotType {
    BENDER {
        @Override
        Robot createRobot(int id) {
            List<TaskType> enabledTasks = new ArrayList<>();
            enabledTasks.add(TaskType.BEND);
            enabledTasks.add(TaskType.SELF_DESTRUCT);
            return new Bender(id, enabledTasks);
        }
    },
    WASHER {
        @Override
        Robot createRobot(int id) {
            List<TaskType> enabledTasks = new ArrayList<>();
            enabledTasks.add(TaskType.WASH);
            enabledTasks.add(TaskType.SELF_DESTRUCT);
            return new Washer(id, enabledTasks);
        }
    },
    CLEANER {
        @Override
        Robot createRobot(int id) {
            List<TaskType> enabledTasks = new ArrayList<>();
            enabledTasks.add(TaskType.CLEAN);
            enabledTasks.add(TaskType.SELF_DESTRUCT);
            return new Cleaner(id, enabledTasks);
        }
    };

    abstract Robot createRobot(int id);
}
