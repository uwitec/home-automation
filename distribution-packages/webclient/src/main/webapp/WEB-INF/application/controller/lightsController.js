var lightControl = angular.module('lightsControl', []);


lightControl.controller('LightsCtrl', ['$scope', '$routeParams', '$http',
    function ($scope, $routeParams, $http) {
        initFastButtons();
        setDimensions();

        $http.get('devices/' + $routeParams.name).success(function (data) {
            $scope.device = data;
        });

        $http.get('devices/' + $routeParams.name+"/capability/Light").success(function (data) {
            $scope.runmodes = data;
        });

        $scope.setRunmode = function(runmode){
            $http.put('devices/' + $routeParams.name + "/capability/Light/runmode/" + runmode);
        };

        $scope.toggleFilter = function(runmode, filter){
            $http.put('devices/' + $routeParams.name + "/capability/Light/runmode/" + runmode+"/filter/"+filter);
        };
    }]);

