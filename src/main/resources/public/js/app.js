angular.module('fileyApp', ['angularMoment', 'ngRoute', 'ngCookies', 'filey.services'])
    .config(['$routeProvider', '$locationProvider', '$httpProvider', function($routeProvider, $locationProvider, $httpProvider) {

        $routeProvider.when('/login', {
            templateUrl: 'partials/login.html',
            controller: 'LoginController'
        });

        $routeProvider.when('/signup', {
            templateUrl: 'partials/signup.html',
            controller: 'SignupController'
        });

        $routeProvider.otherwise({
            templateUrl: 'partials/login.html',
            controller: 'LoginController'
        });

        $locationProvider.hashPrefix('!');

        var interceptor = function($rootScope, $q, $location){
            function success(response){
                return response;
            }

            function error(response) {
                var status = response.status;
                var config = response.config;
                var method = config.method;
                var url = config.url;

                if( status == 401 ){
                    $lcoation.path('/login');
                }
                else {
                    $rootScope.error = method + " on " + url + " failed with status " + status;
                }

                return $q.reject(response);
            }

            return function(promise) {
                return promise.then(success, error);
            };
        };

        $httpProvider.responseInterceptors.push(interceptor);
    } ]
    ).run(function ($rootScope, $http, $location, $cookieStore, xAuthTokenHeaderName) {

        $rootScope.$on('$viewContentLoaded', function() {
            delete $rootScope.error;
        });

        $rootScope.hasRole = function (role) {

            if($rootScope.user === undefined){
                return false;
            }

            if($rootScope.user.roles[role] === undefined){
                return false;
            }

            return $rootScope.user.roles[role];
        };

        $rootScope.logout = function () {
            delete $rootScope.user;
            delete $http.defaults.headers.common[xAuthTokenHeaderName];
            $cookieStore.remove('user');
            $location.path('/login');
        };

        var originalPath = $location.path();
        $location.path('/login');
        var user = $cookieStore.get('user');
        if( user !== undefined ){
            $rootScope.user = user;
            $http.defaults.headers.common[xAuthTokenHeaderName] = user.token;

            $location.path(originalPath);
        }

        $rootScope.initialized = true;
    }
).constant( 'xAuthTokenHeaderName', 'x-auth-token');