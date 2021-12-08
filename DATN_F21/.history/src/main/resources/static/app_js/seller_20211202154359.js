const app = angular.module("seller-app", []);
app.controller("seller-ctrl", function($scope, $http) {


    $scope.initialize = function() {
        const url = "http://localhost:8080/rest/store/category"
        $http.get(url).then(resp => {
            $scope.form = resp.data;
        });
    }

    $scope.initialize();

})