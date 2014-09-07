(function () {
    var SignupController = function ($scope, $location, SignupService) {
        $scope.signup = function () {
            SignupService.signup($.param({username: $scope.username, password: $scope.password}),
                function (response) {
                    debugger;
                    $location.path('/login');
                },
                function () {
                    debugger;
                    $location.path('/login');
                });
        };
    };

    angular.module('fileyApp').controller("SignupController", SignupController);
})();