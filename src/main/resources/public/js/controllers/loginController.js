(function() {
    var LoginController = function($scope, $rootScope, $location, $http, $cookieStore, LoginService, xAuthTokenHeaderName) {

        $scope.login = function () {

            console.log("WHHHHAAAAAAAAAAA");

            LoginService.authenticate($.param({username: $scope.username, password: $scope.password}), function (user) {
                $rootScope.user = user;
                $http.defaults.headers.common[ xAuthTokenHeaderName ] = user.token;
                $cookieStore.put('user', user);
                $location.path('/');
            });

            debugger;
        };
    };

    angular.module('fileyApp').controller("LoginController", LoginController);
})();