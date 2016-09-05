'use strict';

angular.module('myApp').factory('EventLogService', ['$http', '$q', function ($http, $q) {

    var REST_SERVICE_URI = 'http://localhost:8080/robots/log/';

    var factory = {
        getAllEvents: getAllEvents,
        createTask: createTask,
        getAllRobots: getAllRobots
    };

    return factory;

    function getAllEvents() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching Events');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function getAllRobots() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + "robots")
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while fetching Robots');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }

    function createTask(task) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI + "add", task)
            .then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function (errResponse) {
                    console.error('Error while creating Task');
                    deferred.reject(errResponse);
                }
            );
        return deferred.promise;
    }
}]);
