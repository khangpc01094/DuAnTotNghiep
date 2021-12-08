const app = angular.module("seller-app", []);
app.controller("seller-ctrl", function($scope, $http) {

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