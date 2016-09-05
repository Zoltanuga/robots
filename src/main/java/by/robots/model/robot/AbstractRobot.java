package by.robots.model.robot;


import by.robots.model.task.Task;
import by.robots.model.task.TaskProcessLogger;
import by.robots.model.task.TaskType;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstractRobot implements Robot {
    public static final int LOG_CASH_SIZE = 30;
    private int robotId;
    private int powerLevel;
    private List<TaskType> enabledTasks;
    private ConcurrentLinkedQueue<Task> assignedTasks = new ConcurrentLinkedQueue<>();
    private ExecutorService executorService;
    private TaskProcessLogger logger = TaskProcessLogger.getInstance(LOG_CASH_SIZE);


    public AbstractRobot(int robotId, List<TaskType> enabledTasks) {
        this.robotId = robotId;
        this.enabledTasks = enabledTasks;
        executorService = Executors.newSingleThreadExecutor();
    }

    public AbstractRobot(int robotId, int powerLevel) {
        this.robotId = robotId;
        this.powerLevel = powerLevel;
        executorService = Executors.newSingleThreadExecutor();
    }

    public AbstractRobot(int robotId) {
        this.robotId = robotId;
    }

    public String getRobotType() {
        return this.getClass().getSimpleName().toLowerCase();
    }

    public boolean isDead() {
        return executorService.isTerminated();
    }

    public void selfDestruct() {
        executorService.shutdownNow();
    }

    public void doTask() {
        executorService.execute(this);
    }

    public void finishMaintenance() {
        if (getCurrentTask().isMaintenanceTask()) {
            selfDestruct();
        }
        resetCurrentTask();
    }

    public TaskProcessLogger getLogger() {
        return logger;
    }

    public void setLogger(TaskProcessLogger logger) {
        this.logger = logger;
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public synchronized void resetCurrentTask() {
        assignedTasks.remove();
    }

    public synchronized Task getCurrentTask() {
        return assignedTasks.peek();
    }

    public ConcurrentLinkedQueue<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(ConcurrentLinkedQueue<Task> currentTask) {
        this.assignedTasks = currentTask;
    }

    public void addTask(Task task) {
        assignedTasks.offer(task);
    }

    final public List<TaskType> getEnabledTasks() {
        return enabledTasks;
    }

    final public void setEnabledTasks(List<TaskType> enabledTasks) {
        this.enabledTasks = enabledTasks;
    }

    public int getRobotId() {
        return robotId;
    }

    public void setRobotId(int robotId) {
        this.robotId = robotId;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(int powerLevel) {
        this.powerLevel = powerLevel;
    }

    public boolean isBusy() {
        return !assignedTasks.isEmpty();
    }

    @Override
    public String toString() {
        return "{" +
                "robotId=" + robotId +
                ", powerLevel=" + powerLevel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractRobot that = (AbstractRobot) o;

        return robotId == that.robotId;

    }

    @Override
    public int hashCode() {
        return robotId;
    }
}
