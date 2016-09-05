package by.robots.model.viewObjects;


import by.robots.model.task.Task;
import by.robots.model.robot.Robot;

import java.util.Date;

public class EventLogViewObject {
    private Date date;
    private int robotId;
    private int taskId;
    private String taskType;
    private String status;

    public EventLogViewObject() {
    }


    public EventLogViewObject(Robot robot, Task task, ProcessStatus status) {
        date = new Date();
        this.robotId = robot.getRobotId();
        this.taskId = task.getTaskId();
        this.taskType = task.getType().toString();
        this.status = status.toString();
    }

    public int getRobotId() {
        return robotId;
    }

    public void setRobotId(int robotId) {
        this.robotId = robotId;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "EventLogViewObject{" +
                "date=" + date +
                ", robotId=" + robotId +
                ", taskId=" + taskId +
                ", taskType='" + taskType + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventLogViewObject that = (EventLogViewObject) o;

        if (robotId != that.robotId) return false;
        if (taskId != that.taskId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (taskType != null ? !taskType.equals(that.taskType) : that.taskType != null) return false;
        return !(status != null ? !status.equals(that.status) : that.status != null);

    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + robotId;
        result = 31 * result + taskId;
        result = 31 * result + (taskType != null ? taskType.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
