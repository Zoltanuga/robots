'use strict';

angular.module('myApp').controller('EventLogController', ['$scope', '$interval', 'EventLogService', function ($scope, $interval, EventLogService) {
    var self = this;
    $scope.taskTypes = ["clean", "wash", "bend", "self_destruct"];
    self.task = {type: '', timeComplexity: null, assignedRobotId: null};
    self.robot = {robotType: '', robotId: null, powerLevel: null};
    self.logView = {date: null, robotId: null, taskId: null, taskType: '', status: ''};
    self.logViews = [];
    self.robots = [];
    self.submit = submit;
    self.reset = reset;
    var refreshEventsPeriod = 6000;
    var refreshRobotsPeriod = 2000;

    $interval(function fetchAllEvents() {
        EventLogService.getAllEvents()
            .then(
                function (d) {
                    self.logViews = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Events');
                }
            );
    }, refreshEventsPeriod);

    $interval(function fetchAllRobots() {
        EventLogService.getAllRobots()
            .then(
                function (d) {
                    self.robots = d;
                },
                function (errResponse) {
                    console.error('Error while fetching Robots');
                }
            );
    }, refreshRobotsPeriod);


    function createTask(task) {
        EventLogService.createTask(task)
            .then(
                console.info('task created'),
                function (errResponse) {
                    alert('Error while creating Task! Be careful when you are assigning robot');
                    console.error('Error while creating Task');
                }
            );
    }

    function submit() {
        console.log('Saving New task', self.task);
        createTask(self.task);
        reset();
    }

    function reset() {
        self.task = {taskType: '', timeComplexity: null};
        self.assignedRobotId = null;
        $scope.CreateTaskForm.$setPristine();
    }
}]);
