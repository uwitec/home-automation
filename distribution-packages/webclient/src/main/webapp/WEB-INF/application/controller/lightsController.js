var lightControl = angular.module('lightsControl', []);


lightControl.controller('LightsCtrl', ['$scope', '$routeParams', '$http',
    function ($scope, $routeParams, $http) {
        initFastButtons();
        setDimensions();

        $http.get('devices/' + $routeParams.name).success(function (data) {
            $scope.device = data;
        });

        $http.get('devices/' + $routeParams.name+"/Light/information").success(function (data) {
            $scope.runmodes = data;
        });

        $scope.setColor = function (color) {
            $http.put('devices/' + $routeParams.name + "/Light/color/" + color);
        };

        $scope.setRunmode = function (mode) {
            $http.put('devices/' + $routeParams.name + "/Light/runmode/" + mode);
        }


    }]);

