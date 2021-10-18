angular.module('app').controller('mainController',
    function ($scope, $http, $localStorage) {

    baseCtx = 'http://localhost:8080/app/rest/api'

    $scope.getUser = function() {
        $http({
            url: baseCtx + "/auth",
            method: 'GET',
        }).then(function (response) {
            console.log("auth :: " + response.data);
            $scope.User = response.data;
            $localStorage.currentUser = {
                     id: $scope.User.id,
                     name: $scope.User.name,
                     email: $scope.User.email
                };
        });
    }

    $scope.fillTableWithOrders = function () {
        $http({
            url: baseCtx + '/order/all/' + $localStorage.currentUser.id,
            method: 'GET',
        }).then(function (response) {
               console.log("order all :: " + response.data);
               $scope.Orders = response.data;
        });
    };

    $scope.fillTableWithItems = function() {
        $http({
            url: baseCtx + "/item/all",
            method: 'GET',
        }).then(function (response) {
            $scope.Items = response.data;
        });
    };

    $scope.addToCart = function(id) {
        $http({
            url: baseCtx + "/order/cart/add/" + id,
            method: 'GET',
        })
            .then(function (response) {
                $scope.cartContentRequest();
            });
    };

    $scope.removeItem = function(id) {
        $http({
            url: baseCtx + "/order/cart/remove/" + id,
            method: 'GET',
        }).then(function (response) {
             $scope.cartContentRequest();
          });
    };

    $scope.createOrder = function () {
        $http({
            url: baseCtx + '/order',
            method: 'POST',
            params: {
                id: $localStorage.currentUser.id
            }
        })
            .then(function (response) {
                alert('Order created');
                $scope.fillTableWithOrders();
                $scope.cartContentRequest();
            });
    };

    $scope.cartContentRequest = function () {
        $http({
            url: baseCtx + '/order/cart',
            method: 'GET'
        })
            .then(function (response) {
                console.log(response.data);
                $scope.Cart = response.data;
            });
    };

    $scope.fillTableWithItems();
    $scope.getUser();
    $scope.cartContentRequest();
    $scope.fillTableWithOrders();
});
