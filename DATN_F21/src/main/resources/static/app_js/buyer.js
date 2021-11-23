var app = angular.module("buyer-app", []);
app.controller("buyer-ctrl", function ($scope, $http) {
  $scope.datacart = [];

  $scope.cart = {
    items: [],

    loadCart() {
      $http
        .get(`/rest/cart/user1`)
        .then((resp) => {
          this.items = resp.data;
        })
        .catch((error) => {
          alert("loi truy van"), console.log(error);
        });
    },

    add(id) {
      var item = this.items.find((item) => item.product.id == id);
      if (item) {
        $http
          .get(`/rest/putcart/${id}`)
          .then((resp) => {
            alert("ban vua them mot san pham");
          })
          .catch((error) => {
            alert("cap nhat khong dc");
            console.log(error);
          });
      } else {
        $http
          .get(`/rest/createCart/${id}`)
          .then((resp) => {
            alert("đã them vao giỏ hàng");
          })
          .catch((error) => {
            alert("đã them vao giỏ hàng");
            console.log(error);
          });
      }
    },

    apartquantity(id) {
      var item = this.items.find((item) => item.id == id);
      var u  = item.quantity;
      if(u == 1){
        var cart = confirm("Bạn chắc sẽ xóa sản phẩm ???");
        if (cart == true) {
          $http.get(`/rest/apartquantity/${id}`).then((resp) => {
              $scope.cart.loadCart();
            })
            .catch((error) => {
              alert(error);
            });
        } else {
          null
        }
      }else {
        $http.get(`/rest/apartquantity/${id}`).then((resp) => {
          $scope.cart.loadCart();
        })
        .catch((error) => {
          alert(error);
        });
      }
        
    },

    addquantity(id) {
      $http
        .get(`/rest/addquantity/${id}`)
        .then((resp) => {
          $scope.cart.loadCart();
        })
        .catch((error) => {
          alert(error);
        });
    },
  };

  $scope.cart.loadCart();








  
});

app.filter("groupBy", [
  "$parse",
  "$filter",
  function ($parse, $filter) {
    return function (array, groupByField) {
      var result = [];
      var prev_item = null;
      var groupKey = false;
      var filteredData = $filter("orderBy")(array, groupByField);
      for (var i = 0; i < filteredData.length; i++) {
        groupKey = false;
        if (prev_item !== null) {
          if (prev_item[groupByField] !== filteredData[i][groupByField]) {
            groupKey = true;
          }
        } else {
          groupKey = true;
        }
        if (groupKey) {
          filteredData[i]["group_by_key"] = true;
        } else {
          filteredData[i]["group_by_key"] = false;
        }
        result.push(filteredData[i]);
        prev_item = filteredData[i];
      }
      return result;
    };
  },
]);
