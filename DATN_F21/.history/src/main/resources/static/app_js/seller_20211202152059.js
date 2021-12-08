const app = angular.module("seller-app", []);
app.controller("seller-ctrl", function($scope, $http) {


    $scope.initialize = function() {
        alert("alert")
    }
    $scope.initialize();

})