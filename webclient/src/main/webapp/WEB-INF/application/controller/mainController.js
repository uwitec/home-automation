var mainControl = angular.module('mainControl', []);

mainControl.controller('MainCtrl', ['$scope', '$http',
    function ($scope, $http) {
        initFastButtons();
        setDimensions();

        $scope.navigate = function(to) {
            $location.path('#/' + to);
        };
    }]);