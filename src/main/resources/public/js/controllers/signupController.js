(function () {
    var SignupController = function ($scope, $location, SignupService) {
        $scope.signup = function () {
            SignupService.signup($.param({username: $scope.username, password: $scope.password}),
                function (response) {
                    $location.path('/login');
                },
                function (response) {
                    // TODO: show errors
                    $location.path('/signup');
                });
        };
    };

    angular.module('fileyApp').controller("SignupController", SignupController);
})();