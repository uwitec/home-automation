'use strict';

(function () {
    angular.module('lightsControl').directive('lightsPanel', function () {
        return {
            replace: true,
            restrict: 'AEC',
            controller: 'LightsCtrl',
            scope: {
                device: '=?'
            },

            templateUrl: 'application/partials/lights'
        }
    });
})();
