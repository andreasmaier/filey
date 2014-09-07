(function() {
    var SignupService = function($resource) {
        return $resource(':action', {}, {
            signup: {
                method: 'POST',
                params: {'action': 'signup'},
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/x-www-form-urlencoded'
                }
            }
        });
    };

    angular.module('filey.services', ['ngResource']).factory('SignupService', SignupService);
})();