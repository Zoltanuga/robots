package by.robots.model.task;


import java.util.concurrent.atomic.AtomicInteger;

public class Task {
    public static int DEFAULT_TIME_COMPLEXITY = 5;
    private static AtomicInteger firstId = new AtomicInteger(1);
    private int taskId;
    private TaskType type;
    private int timeComplexity;
    private int assignedRobotId;

    public Task() {
        taskId = firstId.incrementAndGet();
        type = TaskType.BEND;
        timeComplexity = DEFAULT_TIME_COMPLEXITY;
    }

    public Task(TaskType type) {
        taskId = firstId.incrementAndGet();
        this.type = type;
        timeComplexity = DEFAULT_TIME_COMPLEXITY;
    }

    public Task(TaskType type, int timeComplexity) {
        taskId = firstId.incrementAndGet();
        this.type = type;
        this.timeComplexity = timeComplexity;
    }


    public Task(TaskType type, int timeComplexity, int assignedRobotId) {
        taskId = firstId.incrementAndGet();
        this.type = type;
        this.timeComplexity = timeComplexity;
        this.assignedRobotId = assignedRobotId;
    }

    public int getAssignedRobotId() {
        return assignedRobotId;
    }

    public void setAssignedRobotId(int assignedRobotId) {
        this.assignedRobotId = assignedRobotId;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = TaskType.valueOf(type.toUpperCase());
    }

    public int getTimeComplexity() {
        return timeComplexity;
    }

    public void setTimeComplexity(int timeComplexity) {
        this.timeComplexity = timeComplexity;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public boolean isMaintenanceTask() {
        return type.equals(TaskType.SELF_DESTRUCT);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (taskId != task.taskId) return false;
        if (timeComplexity != task.timeComplexity) return false;
        if (assignedRobotId != task.assignedRobotId) return false;
        return type == task.type;

    }

    @Override
    public int hashCode() {
        int result = taskId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + timeComplexity;
        result = 31 * result + assignedRobotId;
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", type=" + type +
                ", timeComplexity=" + timeComplexity +
                '}';
    }
}
