(function() {
    var LoginService = function($resource) {
        return $resource(':action', {}, {
            authenticate: {
                method: 'POST',
                params: {'action': 'authenticate'},
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            }
        });
    };

    angular.module('filey.services', ['ngResource']).factory('LoginService', LoginService);
})();