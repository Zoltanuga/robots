package by.robots.model.task;


public class TaskGenerator {
    TaskType[] types = {TaskType.BEND, TaskType.CLEAN, TaskType.WASH, TaskType.SELF_DESTRUCT};

    public Task generate() {
        int rand = (int) (Math.random() * types.length);
        return new Task(types[rand]);
    }

    public Task generateUseful() {
        int rand = (int) (Math.random() * 3);
        return new Task(types[rand]);
    }
}
