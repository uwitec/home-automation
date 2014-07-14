var devicesControllers = angular.module('devicesControllers', []);


devicesControllers.controller('DeviceListCtrl', ['$scope', '$http',
    function ($scope, $http) {
        initFastButtons();
        setDimensions();

        $http.get('devices/list').success(function (data) {
            $scope.devices = data;
        });
    }]);



