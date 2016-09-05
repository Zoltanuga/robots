package by.robots.model.robot;

import java.util.concurrent.atomic.AtomicInteger;

public class RobotFactory {
    public static final int MAX_POWER_LEVEL = 10;
    public static AtomicInteger currentRobotId = new AtomicInteger(1);

    public Robot create(String robotTypeParam) {
        RobotType robotType = RobotType.valueOf(robotTypeParam.toUpperCase());
        Robot robot = robotType.createRobot(currentRobotId.getAndIncrement());
        robot.setPowerLevel(generatePowerLevel());
        return robot;
    }

    private int generatePowerLevel() {
        int randomValue = (int) (Math.random() * MAX_POWER_LEVEL);
        return randomValue == 0 ? 1 : randomValue;
    }
}