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
            alert("ban vua cập nhật mot san pham");
            $scope.cart.loadCart();
            $scope.cart.getCart();
            $scope.cart.loadsum();
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
            $scope.cart.loadCart();
            $scope.cart.getCart();
            $scope.cart.loadsum();
          })
          .catch((error) => {
            alert("lỗi thêm vao giỏ hàng");
          });
      }
    },

    apartquantity(id) {
      var item = this.items.find((item) => item.id == id);
      var u = item.quantity;
      if (u == 1) {
        var cart = confirm("Bạn chắc sẽ xóa sản phẩm ???");
        if (cart == true) {
          $http
            .get(`/rest/apartquantity/${id}`)
            .then((resp) => {
              $scope.cart.loadCart();
              $scope.cart.getCart();
              $scope.cart.loadsum();
            })
            .catch((error) => {
              alert(error);
            });
        } else {
          null;
        }
      } else {
        $http
          .get(`/rest/apartquantity/${id}`)
          .then((resp) => {
            $scope.cart.loadCart();
            $scope.cart.getCart();
            $scope.cart.loadsum();
          })
          .catch((error) => {
            alert(error);
          });
      }
    },

    remove(id){
      
    },

    addquantity(id) {
      $http
        .get(`/rest/addquantity/${id}`)
        .then((resp) => {
          $scope.cart.loadCart();
          $scope.cart.getCart();
          $scope.cart.loadsum();
        })
        .catch((error) => {
          alert(error);
        });
    },

    ckeck(id) {
      $http
        .get(`/rest/checkStatus/${id}`)
        .then((resp) => {
          $scope.cart.loadCart();
          $scope.cart.loadsum();
        })
        .catch((error) => {
          alert(error);
        });
    },

    sumall: [],
    
    loadsum() {
      $http
        .get(`/rest/cart/total`)
        .then((resp) => {
          this.sumall = resp.data;
        })
        .catch((error) => {
          alert("loi truy van"), console.log(error);
        });
    },

    carts: [],

    getCart() {
      $http.get(`/rest/cartTrue/user1`).then((resp) => {
        this.carts = resp.data;
      });
    },

    get amount() {
      return this.sumall
        .map((item) => item.thanhtoan)
        .reduce((total, thanhtoan) => (total += thanhtoan), 0);
    },

    get count() {
      return this.items
        .map((item) => item.quantity)
        .reduce((total, quantity) => (total += quantity), 0);
    }
  };

  $scope.cart.loadCart();

  $scope.cart.loadsum();

  $scope.cart.getCart();

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
