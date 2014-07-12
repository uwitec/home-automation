var devicesApp = angular.module('devicesApp', [
    'ngRoute',
    'devicesControllers',
    'mainControl',
    'lightsControl',
    'sensorControl'
]);

devicesApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/index', {
                templateUrl: 'application/partials/main.html',
                controller: 'MainCtrl'
            }). when('/devices', {
                templateUrl: 'application/partials/device-list.html',
                controller: 'DeviceListCtrl'
            }).
            when('/devices/:name', {
                templateUrl: 'application/partials/lights.html',
                controller: 'DeviceDetailCtrl'
            }).
            when('/devices/:name/Lights', {
                templateUrl: 'application/partials/lights.html',
                controller: 'LightsCtrl'
            }).
            when('/sensors', {
                templateUrl: 'application/partials/sensors.html',
                controller: 'SensorCtrl'
            }).
            otherwise({
                redirectTo: 'index'
            });
    }]);