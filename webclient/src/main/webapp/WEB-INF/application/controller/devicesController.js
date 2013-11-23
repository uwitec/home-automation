var devicesControllers = angular.module('devicesControllers', []);

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
    }]);