var lightControl = angular.module('sensorControl', []);


lightControl.controller('SensorCtrl', ['$scope', '$routeParams', '$http',
    function ($scope, $routeParams, $http) {
        initFastButtons();
        setDimensions();

        $http.get('sensor').success(function (data) {
            $scope.sensorReadings = data;
        });

        $scope.setColor = function (color) {
            $http.put('devices/' + $routeParams.name + "/Light/color/" + color);
        };

        $scope.setRunmode = function (mode) {
            $http.put('devices/' + $routeParams.name + "/Light/runmode/" + mode);
        }


    }]);

