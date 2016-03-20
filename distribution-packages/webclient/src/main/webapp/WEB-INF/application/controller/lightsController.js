var lightControl = angular.module('lightsControl', []);


lightControl.controller('LightsCtrl', ['$scope', '$routeParams', '$http',
    function ($scope, $routeParams, $http) {
        initFastButtons();
        setDimensions();

        $http.get('devices/' + $routeParams.name+"/capability/Light").success(function (data) {
            $scope.runmodes = data;
        });
    }]);

