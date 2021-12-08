const app = angular.module("buyer-app", []);
app.controller("buyer-ctrl", function ($scope, $http) {
  $scope.form = {};

  $scope.reset = function () {
    $scope.form = {};
  };

  $scope.address = [];

  //get dia chi
  $scope.getaddreess = function () {
    $http.get(`/rest/address/us`).then((resp) => {
      this.address = resp.data;
    });
  };

  $scope.getaddreess();

  //dang ky
  $scope.regis = function () {
    var item = angular.copy($scope.form);
    $http
      .post(`/rest/buyer/regis`, item)
      .then((resp) => {
        $scope.reset();
        return Swal.fire({
          width: "400px",
          title: "Đăng ký thành công!",
          icon: "success",
        });
      })
      .catch((error) => {
        console.log(error);
        return Swal.fire({
          width: "400px",
          title: "Lỗi đăng ký!",
          icon: "error",
        });
      });
  };

  $scope.deleteaddress = function (id) {
    Swal.fire({
      title: "Are you sure?",
      text: "You won't be able to revert this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Yes, delete it!",
    }).then((result) => {
      if (result.isConfirmed) {
        $http
          .delete(`/rest/address/buyer/del_address/${id}`)
          .then((resp) => {});
        Swal.fire(
          "Deleted!",
          "Your file has been deleted.",
          "success",
          location.reload()
        );
      }
    });
  };

  //load len form updateaddress
  $scope.editaddress = function (item) {
    $scope.form = angular.copy(item);
  };

  //update address
  $scope.updateaddress = function () {
    var item = angular.copy($scope.form);
    $http
      .put(`/rest/address/buyer/edit_address`, item)
      .then((resp) => {
        // $scope.reset();

        return (
          Swal.fire({
            width: "400px",
            title: "Cập nhật thành công!",
            icon: "success",
            timer: 99500,
          }),
          location.reload()
        );
      })
      .catch((error) => {
        return Swal.fire({
          width: "400px",
          title: "Lỗi thêm!",
          icon: "error",
        });
      });
  };

  $scope.add_address = function () {
    var item = angular.copy($scope.form);
    $http
      .post(`/rest/address/buyer/add_address`, item)
      .then((resp) => {
        $scope.reset();
        return Swal.fire({
          width: "400px",
          title: "Thêm địa chỉ thành công!",
          icon: "success",
        });
      })
      .catch((error) => {
        return Swal.fire({
          width: "400px",
          title: "Thêm địa chỉ thất bại!",
          icon: "error",
        });
        console.log("Error", error);
      });
  };

   //dang ky nguoiban
   $scope.regisSeller = function () {
    var item = angular.copy($scope.form);
    $http.post(`/seller/regis`, item).then(resp => {
        $scope.reset();
        return Swal.fire({
            width: '400px',
            title: 'Đăng ký thành công!',
            icon: 'success',
        })
    }).catch(error => {
        console.log(error);
        return Swal.fire({
            width: '400px',
            title: 'Lỗi đăng ký!',
            icon: 'error',
        })
        
    })
}

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
            $scope.cart.getSumQuantity();
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
            $scope.cart.getSumQuantity();
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
              $scope.cart.getSumQuantity();
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
            $scope.cart.getSumQuantity();
            $scope.cart.loadsum();
          })
          .catch((error) => {
            alert(error);
          });
      }
    },

    remove(id) {},

    addquantity(id) {
      $http
        .get(`/rest/addquantity/${id}`)
        .then((resp) => {
          $scope.cart.loadCart();
          $scope.cart.getCart();
          $scope.cart.loadsum();
          $scope.cart.getSumQuantity();
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
          $scope.cart.getCart();
          $scope.cart.loadsum();
          $scope.cart.getSumQuantity();
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

    sumquantity: {},

    getSumQuantity() {
      $http.get(`/rest/cart/sumquatity`).then((resp) => {
        this.sumquantity = resp.data;
      });
    },

    get amount() {
      return this.sumall
        .map((item) => item.pay)
        .reduce((total, pay) => (total += pay), 0);
    },

    //   get count() {
    // 	return this.items
    // 	  .map((item) => item.quantity)
    // 	  .reduce((total, quantity) => (total += quantity), 0);
    //   }
  };

  $scope.notification = [];

  $scope.getNotification = function(){
    $http.get(`/rest/order/notification`).then(resp => {
      this.notification = resp.data;
    }).catch(error => {
      console.log(error);
    })
  }

  $scope.getNotification();


  $scope.cart.loadCart();

  $scope.cart.loadsum();

  $scope.cart.getCart();

  $scope.cart.getSumQuantity();
});

app.controller("login-ctrl", function ($scope, $http) {
  $scope.email;
  $scope.password;
});

app.controller("information-ctrl", function ($scope, $http) {
  $scope.form = {};

  $scope.director;
  $scope.initialize = function () {
    $http.get("/rest/user/information").then((resp) => {
      $scope.form = resp.data;
      $scope.form.birthday = new Date($scope.form.birthday);
    });
  };

  $scope.initialize();

  $scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/user/information/update`, item).then(resp => {
			if (resp.status == 200) {
				return Swal.fire({
					width: '400px',
					title: 'Cập nhật thành công!',
					icon: 'success',
					showConfirmButton: false,
					timer: 1500
				})
			}
		}).catch(error => {
			if (error.status == 404) {
				return Swal.fire({
					width: '400px',
					title: 'Không tìm thấy người dùng này!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			Swal.fire({
				width: '400px',
				title: 'Lỗi cập nhật!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error", error);
		});
	}


  $scope.imageChanged = function (files) {
    var data = new FormData();
    data.append("file", files[0]);
    $http
      .post("/rest/upload/user", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.picture = resp.data.name;
      })
      .catch((error) => {
        return Swal.fire({
				width: '400px',
				title: 'Lỗi uplaod hình ảnh!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
      
        console.log("Error", error);
      });
  };
});

app.controller("wallet-ctrl", function ($scope, $http) {
  $scope.cardbrand = [];
  $scope.form = {};

  var url = "http://localhost:2021/rest/cardbrand";
  $http
    .get(url)
    .then((resp) => {
      $scope.cardbrand = resp.data;
    })
    .catch((error) => {
      return Swal.fire({
        width: '400px',
        title: 'Lỗi server bank!',
        icon: 'error',
        confirmButtonText: 'Ok',
      })	  
    });

    $scope.save = function() {	
      var item = angular.copy($scope.form);
      $http.post(`/rest/wallet`, item).then(resp => {
        if (resp.status == 200) {		
          return Swal.fire({
            width: '400px',
            title: 'Lưu thành công!',
            icon: 'success',
            showConfirmButton: false,
            timer: 1500
          })
        }
      }).catch(error => {
        if (error.status == 400) {		
          return Swal.fire({
            width: '400px',
            title: 'Thông tin ngân hàng không chính xác hoặc thẻ đã hết hạn!',
            icon: 'error',
            confirmButtonText: 'Ok',
          })
        }
      });
    }  
});

app.controller("topup-ctrl", function ($window, $scope, $http) {
  $scope.cardbrand = [];
  $scope.form = {};

  var url = "http://localhost:2021/rest/cardbrand";
  $http
    .get(url)
    .then((resp) => {
      $scope.cardbrand = resp.data;
    })
    .catch((error) => {
      return Swal.fire({
        width: '400px',
        title: 'Lỗi server bank!',
        icon: 'error',
        confirmButtonText: 'Ok',
      })
  
    });

    $scope.contineu_money = function() {	
      var item = angular.copy($scope.form);
      $http.post(`/rest/wallet/topup`, item).then(resp => {
        if (resp.status == 200) {
          Swal.fire({
            width: '400px',
            title: 'Hợp lệ!',
            icon: 'success',
            showConfirmButton: false,
            timer: 1500
          })
          $window.location.href = '/account/wallet/topup/checkmoney';
        }
      }).catch(error => {
        if (error.status == 400) {		
          return Swal.fire({
            width: '400px',
            title: 'Thông tin ngân hàng không chính xác hoặc thẻ đã hết hạn!',
            icon: 'error',
            confirmButtonText: 'Ok',
          })
        }
      });
    }  
});

app.controller("changepassword-ctrl", function ($scope, $http) {
  $scope.pw_present;
  $scope.pw_new;
  $scope.pw_confirm;
});

app.controller("checkmoney-ctrl", function ($scope, $http) {
  $scope.money = 0.0;
});

app.controller("transaction-ctrl", function ($scope, $http) {
  $scope.items = [];
  $scope.finddate = {};

  $scope.initialize = function () {
    $http.get("/rest/transaction/byuserid").then((resp) => {
      $scope.items = resp.data;
      $scope.items.forEach((item) => {
        item.createdate = new Date(item.createdate);
      });
    });
  };

  $scope.pager = {
    page: 0,
    size: 5,
    get items() {
      var start = this.page * this.size;
      return $scope.items.slice(start, start + this.size);
    },
    get count() {
      return Math.ceil((1.0 * $scope.items.length) / this.size);
    },

    first() {
      this.page = 0;
      $scope.no = this.page * 10;
    },
    prev() {
      this.page--;
      if (this.page < 0) {
        this.last();
      }
      $scope.no = this.page * 10;
    },
    next() {
      this.page++;
      if (this.page >= this.count) {
        this.first();
      }
      $scope.no = this.page * 10;
    },
    last() {
      this.page = this.count - 1;
      $scope.no = this.page * 10;
    },
  };

  $scope.find = function () {
    var finddate = angular.copy($scope.finddate);
    $http
      .post(`/rest/transaction/findbydate`, finddate)
      .then((resp) => {
        $scope.items = resp.data;
        $scope.items.forEach((item) => {
          item.createdate = new Date(item.createdate);
        });
      })
      .catch((error) => {
        alert("Lỗi");
      });
  };

  $scope.initialize();
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
