(function() {
    var SignupService = function($resource) {
        return $resource(':action', {}, {
            authenticate: {
                method: 'POST',
                params: {'action': 'signup'},
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        });
    };

    angular.module('filey.services', ['ngResource']).factory('SignupService', SignupService);
})();