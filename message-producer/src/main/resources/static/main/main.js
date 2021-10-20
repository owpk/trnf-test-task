angular.module('app').controller('mainController',
    function ($scope, $http, $localStorage) {

    $scope.fillTableWithOrders = function () {
        $http({
            url: './rest/api/order/all/' + $localStorage.currentUser.id,
            method: 'GET',
        }).then(function (response) {
               console.log("order all :: " + response.data);
               $scope.Orders = response.data;
        });
    };

    $scope.fillTableWithItems = function() {
        $http({
            url: "./rest/api/item/all",
            method: 'GET',
        }).then(function (response) {
            $scope.Items = response.data;
        });
    };

    $scope.addToCart = function(id) {
        $http({
            url: './rest/api/order/cart/add/' + id,
            method: 'GET',
        })
            .then(function (response) {
                $scope.cartContentRequest();
            });
    };

    $scope.removeItem = function(id) {
        $http({
            url: "./rest/api/order/cart/remove/" + id,
            method: 'GET',
        }).then(function (response) {
             $scope.cartContentRequest();
          });
    };

    $scope.createOrder = function () {
        $http({
            url: './rest/api/order',
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
            url: './rest/api/order/cart',
            method: 'GET'
        })
            .then(function (response) {
                console.log(response.data);
                $scope.Cart = response.data;
            });
    };

    $scope.checkIfUserLogged = function() {
        if (!$localStorage.currentUser) {
            window.location.href = '#!/auth';
        } else {
            $scope.fillTableWithItems();
            $scope.cartContentRequest();
            $scope.fillTableWithOrders();
        }
    }

    $scope.checkIfUserLogged();
});
