const app = angular.module("seller-app", []);
app.controller("seller-ctrl", function ($scope, $http) {
  $scope.Order = {
    OrderOne: [],
    OrderTwo: [],
    OrderFather: [],
    OrderFour: [],

    getOrderOne() {
      $http.get(`/rest/order/One`).then((resp) => {
        this.OrderOne = resp.data;
      });
    },

    getOrderTwo() {
      $http.get(`/rest/order/Two`).then((resp) => {
        this.OrderTwo = resp.data;
      });
    },

    getOrderFather() {
      $http.get(`/rest/order/Father`).then((resp) => {
        this.OrderFather = resp.data;
      });
    },

    getOrderFour() {
      $http.get(`/rest/order/Four`).then((resp) => {
        this.OrderFour = resp.data;
      });
    },

    
  };
});
