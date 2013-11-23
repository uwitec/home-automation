var devicesApp = angular.module('devicesApp', [
    'ngRoute',
    'devicesControllers'
]);

devicesApp.config(['$routeProvider',
    function ($routeProvider) {
        $routeProvider.
            when('/devices', {
                templateUrl: '/application/partials/device-list.html',
                controller: 'DeviceListCtrl'
            }).
            when('/devices/:name', {
                templateUrl: 'application/partials/device-detail.html',
                controller: 'DeviceDetailCtrl'
            }).
            otherwise({
                redirectTo: '/devices'
            });
    }]);