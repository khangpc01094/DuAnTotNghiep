const app = angular.module("seller-app", []);
app.controller("seller-ctrl", function ($scope, $http) {


  $scope.Order = {
    SumOrderStatusOne: {},
    OrderOne: [],
    OrderTwo: [],
    OrderFather: [],
    OrderFour: [],

    getSumStatusOne(){
      $http.get(`/rest/order/sumStatus`).then(resp => {
        this.SumOrderStatusOne = resp.data;
      });
    },

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
    
    orderConfirm(id) {
      $http.get(`/rest/order/orderConfirm/${id}`).then( resp => {
        $scope.Order.getOrderOne();
        $scope.Order.getOrderTwo();
        $scope.Order.getOrderFather();
        $scope.Order.getOrderFour();
      }).catch((error) => {
        alert("cap nhat khong dc");
        console.log(error);
      });
    },

    orderRefuse(id){
      $http.get(`/rest/order/orderRefuse/${id}`).then( resp => {
        $scope.Order.getOrderOne();
        $scope.Order.getOrderTwo();
        $scope.Order.getOrderFather();
        $scope.Order.getOrderFour();
      }).catch((error) => {
        alert("cap nhat khong dc");
        console.log(error);
      });
    }
  };


  $scope.Order.getOrderOne();
  $scope.Order.getOrderTwo();
  $scope.Order.getOrderFather();
  $scope.Order.getOrderFour();
  $scope.Order.getSumStatusOne();
});
