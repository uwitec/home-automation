var devicesControllers = angular.module('devicesControllers', []);
var lightControl = angular.module('lightCtrl', []);

devicesControllers.controller('DeviceListCtrl', ['$scope', '$http',
    function ($scope, $http) {
        $http.get('devices/list').success(function (data) {
            $scope.devices = data;
        });

        $scope.orderProp = 'name';
    }]);

devicesControllers.controller('DeviceDetailCtrl', ['$scope', '$routeParams', '$http',
    function ($scope, $routeParams, $http) {
        $http.get('devices/' + $routeParams.name).success(function (data) {
            $scope.device = data;
        });

        $scope.orderProp = 'name';

        $scope.setColor = function (color) {
            $http.put('devices/' + $routeParams.name + "/Light/color/" + color);
        };

        $scope.setRunmode = function (mode) {
            $http.put('devices/' + $routeParams.name + "/Light/runmode/" + mode);
        }
    }]);


lightControl.controller('LightCtrl', ['$scope', '$routeParams', '$http',
    function ($scope, $routeParams, $http) {
        $scope.setColor = function (color) {
            $http.put('devices/' + $routeParams.name + "/Light/color/" + color);
        };

        $scope.setRunmode = function (mode) {
            $http.put('devices/' + $routeParams.name + "/Light/runmode/" + mode);
        }
    }]);