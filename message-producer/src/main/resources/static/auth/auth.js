angular.module('app').controller('authController',
    function ($scope, $http, $localStorage) {

    $scope.tryToAuth = function () {
        $http({
                url: './rest/api/auth',
                method: 'POST',
                headers: {'Content-type': 'application/json'},
                data: $scope.user,
                responseType: 'application/json'
            })
            .then(function successCallback(response) {
                    $localStorage.currentUser = {id: response.data, name: 'User', email: $scope.user.email};
                    window.location.href = '#!/main';
                    console.log($localStorage.currentUser);
            }, function errorCallback(response) {
                window.alert(response.data.message);
                $scope.clearUser();
            });
    };

    $scope.isLogged = function() {
        return !!$localStorage.currentUser;
    };

    $scope.clearUser = function () {
        delete $localStorage.currentUser;
    };
});
