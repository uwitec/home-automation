var phonecatApp = angular.module('phonecatApp', []);

phonecatApp.controller('PhoneListCtrl', function PhoneListCtrl($scope, $http) {
    $http.get('nodes/list').success(function (data) {
        $scope.devices = data;
    });

    $scope.orderProp = 'age';
});