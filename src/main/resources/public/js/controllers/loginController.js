(function() {
    var LoginController = function($scope, $rootScope, $location, $http, $cookieStore, xAuthTokenHeaderName) {

        $scope.login = function () {
            LoginService.authenticate($.param({username: $scope.username, password: $scope.password}), function (user) {
                $rootScope.user = user;
                $http.defaults.headers.common[ xAuthTokenHeaderName ] = user.token;
                $cookieStore.put('user', user);
                $location.path('/');
            });
        };
    };

    angular.module('fileyApp').controller("LoginController", LoginController);
})();