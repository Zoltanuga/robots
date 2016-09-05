package by.robots.model.robot;

import by.robots.model.task.Task;
import by.robots.model.task.TaskGenerator;
import by.robots.model.task.TaskType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class RobotTracker {
    public static final int LIMIT = 50;
    public static final int CRITICAL_DELTA = 10;
    private ConcurrentLinkedQueue<Robot> robots = new ConcurrentLinkedQueue<>();
    private BlockingQueue<Task> tasks = new LinkedBlockingQueue<>(LIMIT);
    private RobotFactory factory = new RobotFactory();

    public RobotTracker() {
    }

    public RobotTracker(ConcurrentLinkedQueue<Robot> robots, BlockingQueue<Task> tasks) {
        this.robots = robots;
        this.tasks = tasks;
    }

    public RobotTracker(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public boolean addTask(Task task) {
        if (isAssigned(task)) {
            if (findAssignedRobot(task) != null) {
                tasks.add(task);
                return true;
            }
        } else {
            tasks.add(task);
            return true;
        }
        return false;
    }

    @Scheduled(fixedDelay = 3000)
    public void track() {
        if (!tasks.isEmpty()) {
            serveTasks();
        }
        removeDeadRobots();
    }

    private void serveTasks() {
        Robot appropriateRobot;
        TaskType lastUsefulTask = null;
        for (Task task : tasks) {
            removeDeadRobots();
            appropriateRobot = findFirstAppropriateRobot(task);
            if (appropriateRobot != null) {
                appropriateRobot.addTask(task);
                tasks.remove(task);
                appropriateRobot.doTask();
            }
            if (!task.isMaintenanceTask()) {
                lastUsefulTask = task.getType();
            }
            boolean isValidConditionForCreating =
                    (!isAssigned(task) && (appropriateRobot == null)
                            || (LIMIT - tasks.size()) < CRITICAL_DELTA) || robots.isEmpty();
            if (isValidConditionForCreating) {
                robots.add(createRobot(convertToRobotType(lastUsefulTask)));
            }
        }
    }

    private Robot createRobot(RobotType robotType) {
        return robotType != null ? factory.create(robotType.toString())
                : factory.create(new TaskGenerator().generateUseful().getType() + "er");
    }

    private Robot findFirstAppropriateRobot(Task task) {
        return isAssigned(task) ? findAssignedRobot(task) : findFreeRobot(task);
    }

    boolean isAssigned(Task task) {
        return task.getAssignedRobotId() != 0;
    }

    private Robot findFreeRobot(Task task) {
        for (Robot robot : robots) {
            if (robot.getEnabledTasks().contains(task.getType())) {
                if (!robot.isBusy()) {
                    return robot;
                }
            }
        }
        return null;
    }

    private Robot findAssignedRobot(Task task) {
        Robot robot = findRobotById(task.getAssignedRobotId());
        if (robot != null) {
            if (robot.getEnabledTasks().contains(task.getType())) {
                return robot;
            }
        }
        return null;
    }


    private Robot findRobotById(int robotId) {
        for (Robot robot : robots) {
            if (robot.getRobotId() == robotId) {
                return robot;
            }
        }
        return null;
    }

    private void removeDeadRobots() {
        for (Robot robot : robots) {
            if (robot.isDead()) {
                robots.remove(robot);
            }
        }
    }

    private RobotType convertToRobotType(TaskType taskType) {

        return taskType == null ? null : RobotType.valueOf(taskType.toString().toUpperCase() + "ER");

    }

    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public ConcurrentLinkedQueue<Robot> getRobots() {
        return robots;
    }

    public void setRobots(ConcurrentLinkedQueue<Robot> robots) {
        this.robots = robots;
    }

}
