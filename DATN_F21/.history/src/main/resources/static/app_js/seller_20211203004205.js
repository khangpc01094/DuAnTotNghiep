const app = angular.module("seller-app", []);
app.controller("seller-ctrl", function($scope, $http) {

    $scope.form = {};
    $scope.category = {};
    $scope.user = [];

    $scope.initialize = function() {
        const url = "http://localhost:8080/rest/store/category"
        $http.get(url).then(resp => {
            $scope.category = resp.data;
        });
    }

    $scope.initialize();

    $scope.initialize1 = function() {
        const url = "http://localhost:8080/rest/store"
        $http.get(url).then(resp => {
            $scope.user = resp.data;
            console.log("data user: " + resp.data);
        });
    }

    $scope.initialize1();



})
app.controller("update-seller-ctrl", function($scope, $http) {

    $scope.form = {};
    $scope.category = {};

    $scope.initialize = function() {
        const url = "http://localhost:8080/rest/store/category"
        $http.get(url).then(resp => {
            $scope.category = resp.data;
        });
    }

    $scope.initialize();

})