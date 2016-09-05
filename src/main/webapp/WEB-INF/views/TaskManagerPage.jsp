<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Robots</title>

    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>

    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.8/angular.js"></script>

    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"/>

</head>
<body>

<div ng-app="myApp" class="ng-cloak">

    <div class="generic-container" ng-controller="EventLogController as ctrl">

        <div class="panel panel-default">
            <div class="panel-heading"><span class="lead">Add Task Form </span></div>
            <div class="formcontainer">
                <form ng-submit="ctrl.submit()" name="CreateTaskForm" class="form-horizontal">
                    <input type="hidden" ng-model="ctrl.task.taskId"/>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label">Type</label>

                            <div class="col-md-7">
                                <select ng-model="ctrl.task.type" ng-options="x for x in taskTypes" name="typeInput"
                                        class="form-control input-sm" required>
                                </select>

                                <div class="has-error" ng-show="CreateTaskForm.$dirty">
                                    <span ng-show="CreateTaskForm.typeInput.$error.required">This is a required field</span>
                                    <span ng-show="CreateTaskForm.typeInput.$error.minlength">Minimum length required is 3</span>
                                    <span ng-show="CreateTaskForm.typeInput.$invalid">This field is invalid </span>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label">Complexity</label>

                            <div class="col-md-7">
                                <input type="number" min='1' max="6000"
                                       ng-model="ctrl.task.timeComplexity"
                                       name="complexityInput"
                                       class="form-control input-sm"
                                       placeholder="Enter time complexity" ng-required="true"/>

                                <div class="has-error" ng-show="CreateTaskForm.$dirty">
                                    <span ng-show="CreateTaskForm.complexityInput.$error.required">This is a required field</span>
                                    <span ng-show="CreateTaskForm.complexityInput.$error.pattern">too huge or negative numbers not allowed</span>
                                    <span ng-show="CreateTaskForm.complexityInput.$invalid">This field is invalid </span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-2 control-label" >Robot Id from table</label>

                            <div class="col-md-7">
                                <input type="number" min="1" ng-pattern="/^[0-9]{1,5}$/" name="assignedRobotIdInput"
                                       ng-model="ctrl.task.assignedRobotId"
                                       class="form-control input-sm"
                                       placeholder="Enter assigned robot Id. [This field is optional]"/>

                                <div class="has-error" ng-show="CreateTaskForm.$dirty">
                                    <span ng-show="CreateTaskForm.assignedRobotIdInput.$error.pattern">too huge or negative numbers not allowed</span>
                                    <span ng-show="CreateTaskForm.assignedRobotIdInput.$invalid">This field is invalid </span>
                                </div>
                            </div>

                            <div class="form-actions floatRight">
                                <input type="submit" value="add"
                                       class="btn btn-primary btn-sm"
                                       ng-disabled="CreateTaskForm.$invalid">
                                <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm"
                                        ng-disabled="CreateTaskForm.$pristine">Reset Form
                                </button>
                            </div>
                        </div>


                    </div>

                </form>
            </div>
        </div>
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Robots </span></div>
            <div class="tablecontainer">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>robot Type</th>
                        <th>robot Id</th>
                        <th>Power</th>
                        <th width="20%"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="robot in ctrl.robots">
                        <td><span ng-bind="robot.robotType"></span></td>
                        <td><span ng-bind="robot.robotId"></span></td>
                        <td><span ng-bind="robot.powerLevel"></span></td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


    <div class="generic-container" ng-controller="EventLogController as logCtrl">
        <div class="panel panel-default">
            <!-- Default panel contents -->
            <div class="panel-heading"><span class="lead">List of Events</span></div>

            <div class="tablecontainer">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Date</th>
                        <th>Robot id</th>
                        <th>Task id</th>
                        <th>Task type</th>
                        <th>Task status</th>

                        <th width="20%"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr ng-repeat="view in logCtrl.logViews">
                        <td><span>{{view.date| date:'yyyy-MM-dd HH:mm:ss Z'}}</span></td>
                        <td><span ng-bind="view.robotId"></span></td>
                        <td><span ng-bind="view.taskId"></span></td>
                        <td><span ng-bind="view.taskType"></span></td>
                        <td><span ng-bind="view.status"></span></td>

                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="<c:url value='/static/js/app.js' />"></script>
<script src="<c:url value='/static/js/service/event_service.js' />"></script>
<script src="<c:url value='/static/js/controller/event_controller.js' />"></script>
</body>
</html>