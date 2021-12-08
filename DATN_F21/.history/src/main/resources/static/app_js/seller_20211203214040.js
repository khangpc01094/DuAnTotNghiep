const app = angular.module("seller-app", []);
app.controller("seller-ctrl", function($scope, $http) {
    $scope.form = {};
    $scope.category = {};
    $scope.user = {};
    $scope.products = [];

    $scope.initialize = function() {
        const url = "http://localhost:8080/rest/store/category"
        $http.get(url).then(resp => {
            $scope.category = resp.data;
        });

        $http.get(`rest/store`).then(resp => {
            $scope.user = resp.data;
        });

        $http.get(`rest/store/allProduct`).then(resp => {
            $scope.products = resp.data;
        });

    }

    // $scope.initialize();





});
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