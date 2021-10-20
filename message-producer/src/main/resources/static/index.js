(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider, $httpProvider) {
        $routeProvider
            .when('/', {
                    templateUrl: 'auth/auth.html',
                    controller: 'authController'
            })
            .when('/main', {
                    templateUrl: 'main/main.html',
                    controller: 'mainController'
            })
            .when('/auth', {
                    templateUrl: 'auth/auth.html',
                    controller: 'authController'
            })
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.currentUser) {
            $http.defaults.headers.common.Authorization = $localStorage.currentUser.id;
        }
    }
})();
