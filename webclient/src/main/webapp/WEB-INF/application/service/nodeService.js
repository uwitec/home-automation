var iotServices = angular.module('iotServices', ['ngResource']);

iotServices.factory('allNodes', ['$resource',
    function ($resource) {
        return $resource('nodes/:phoneId.json', {}, {
            query: {method: 'GET', params: {phoneId: 'phones'}, isArray: true}
        });
    }]);