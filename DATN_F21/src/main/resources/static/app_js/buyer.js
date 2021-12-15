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
    $http.post(`/rest/user/buyer/regis`, item).then(resp => {
        $scope.reset();
        Swal.fire({
          icon: 'success',
          title: 'Đăng kí thành công',
          showConfirmButton: false,
          timer: 1500
        });
    }).catch(error => {
        console.log(error);
        return Swal.fire({
            width: '400px',
            title: 'Lỗi đăng ký!',
            icon: 'error',
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
            icon: 'success',
            title: 'Cập nhật thành công',
            showConfirmButton: false,
            timer: 1500
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

  $scope.addaddress = function() {
    var item = angular.copy($scope.form);
    alert("voi roi ne")
    $http.post(`/rest/address/add_address`, item).then(resp => {
        $scope.reset();
        return Swal.fire('Thêm thành công')
    }).catch(error => {
      console.log("Error", error);
      return Swal.fire('Thêm thất bại')
        
    })
}

   //dang ky nguoiban
   $scope.regisSeller = function () {
    var item = angular.copy($scope.form);
    $http.post(`/rest/store/seller/regis`, item).then(resp => {
        $scope.reset();
        Swal.fire({
          icon: 'success',
          title: 'Đăng kí thành công',
          showConfirmButton: false,
          timer: 1500
        });
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
        .get(`/rest/cart`)
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
            Swal.fire({
              icon: 'success',
              title: 'Đã Thêm Vào Giỏ Hàng',
              showConfirmButton: false,
              timer: 1500
            });
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
            if(resp.status == 200){
            Swal.fire({
              icon: 'success',
              title: 'Đã Thêm Vào Giỏ Hàng',
              showConfirmButton: false,
              timer: 1500
            });
            $scope.cart.loadCart();
            $scope.cart.getCart();
            $scope.cart.loadsum();
            $scope.cart.getSumQuantity();
          }
          })
          .catch((error) => {
            if(error.status == 404 ){
              return Swal.fire({
                icon: 'error',
                title: 'Vui Lòng Đăng Nhập',
                showConfirmButton: false,
                timer: 1500
              });
            }
            Swal.fire({
              icon: 'error',
              title: 'Lỗi Thêm ',
              showConfirmButton: false,
              timer: 1500
            });
          });
      }
    },

    apartquantity(id) {
      var item = this.items.find((item) => item.id == id);
      var u = item.quantity;
      if (u == 1) {      
        Swal.fire({
          title: "Bạn chắc chắn muốn xóa sản phẩm hay không?",
          // text: "You won't be able to revert this!",
          icon: "warning",
          showCancelButton: true,
          confirmButtonColor: "#3085d6",
          cancelButtonColor: "#d33",
          confirmButtonText: "Đồng Ý",
        }).then((result) => {
          if (result.isConfirmed) {
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
            Swal.fire({
              icon: 'success',
              title: 'Đã Xóa Sản Phẩm',
              showConfirmButton: false,
              timer: 1500
            });
          }
        });
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

    delete(id){
      $http.delete(`/rest/cart/${id}`).then(resp =>{
        $scope.cart.loadCart();
          $scope.cart.getCart();
          $scope.cart.loadsum();
          $scope.cart.getSumQuantity();
      }).catch(error => {
        console.log(error);
      })
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
      $http.get(`/rest/cartTrue`).then((resp) => {
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

      get count() {
    	return this.items
    	  .map((item) => item.quantity)
    	  .reduce((total, quantity) => (total += quantity), 0);
      }
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
	  if($scope.form.birthday!=null){
		 $scope.form.birthday = new Date($scope.form.birthday);
	  }	      
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
      .post("/admin/rest/upload/user", data, {
        transformRequest: angular.identity,
        headers: { "Content-Type": undefined },
      })
      .then((resp) => {
        $scope.form.picture = resp.data.name;
      })
      .catch((error) => {
        return Swal.fire({
				width: '400px',
				title: 'Lỗi tải hình ảnh!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
      
        console.log("Error", error);
      });
  };
});

app.controller("cardlink-ctrl", function ($window,$scope, $http) {
  $scope.cardbrand = [];
  $scope.form = {};

  $scope.initialize = function () {
    $http.get("/rest/wallet").then((resp) => {
      $scope.form = resp.data;
      if($scope.form.cardexpiry!=null){
		$scope.form.cardexpiry = new Date($scope.form.cardexpiry);
	  }     
    });
  };

  $scope.initialize();	

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

    $scope.cartlink = function() {	
      var item = angular.copy($scope.form);
      $http.put(`/rest/wallet/cartlink`, item).then(resp => {
        if (resp.status == 200) {		
          Swal.fire({
            width: '400px',
            title: 'Lưu thành công!',
            icon: 'success',
            showConfirmButton: false,
            timer: 1500
          })
		  $window.location.href = '/account/wallet';	
        }
      }).catch(error => {
        if (error.status == 400) {		
          return Swal.fire({
            width: '400px',
            title: 'Thông tin thẻ không chính xác hoặc thẻ đã hết hạn!',
            icon: 'error',
            confirmButtonText: 'Ok',
          })
        }
      });
    }  
});


app.controller("wallet-ctrl", function ($scope, $http) {
  $scope.wallet = {};
  $scope.money;	

  $scope.initialize = function () {
    $http.get("/rest/wallet").then((resp) => {
      $scope.wallet = resp.data;
      $scope.wallet.cardexpiry = new Date($scope.wallet.cardexpiry);
    });
  };

  $scope.initialize();

  $scope.naptien = function(money) {
		var money = angular.copy($scope.money);
		$http.post(`/rest/wallet/naptien`, money).then(resp => {
			if (resp.status == 200) {
				$scope.initialize();
				$scope.money = null; /*set lại cho nó hiện lên cái form nhập số tiền*/
				return Swal.fire({
					width: '400px',
					title: 'Nạp tiền thành công!',
					icon: 'success',
					showConfirmButton: false,
					timer: 1500
				})
			}
		}).catch(error => {
			if (error.status == 400) {
				return Swal.fire({
					width: '400px',
					title: 'Số tiền bạn nạp phải nhỏ hơn số tiền trong thẻ!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			if (error.status == 404) {
				return Swal.fire({
					width: '400px',
					title: 'Thẻ đã hết hạn. Vui lòng cập nhật lại thẻ!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			Swal.fire({
				width: '400px',
				title: 'Lỗi nạp tiền!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})			
			console.log("Error", error);
		});
	}	

    $scope.unlink = function() {
		Swal.fire({
			width: '400px',
			title: 'Bạn thật sự muốn hủy liên kết thẻ?',
			showCancelButton: true,
			confirmButtonText: `OK`,
		}).then((result) => {
			if (result.isConfirmed) {
				$http.put(`/rest/wallet/unlink`).then(resp => {
					if (resp.status == 200) {	
						$scope.initialize();					
						return Swal.fire({
							width: '400px',
							title: 'Hủy liên kết thẻ thành công!',
							icon: 'success',
							showConfirmButton: false,
							timer: 1500
						})
					}
				}).catch(error => {
					/*if (error.status == 404) {
						return Swal.fire({
							width: '400px',
							title: 'Không tìm thấy loại sản phẩm này!',
							icon: 'error',
							confirmButtonText: 'Ok',
						})
					}*/
					Swal.fire({
						width: '400px',
						title: 'Lỗi hủy liên kết thẻ!',
						icon: 'error',
						confirmButtonText: 'Ok',
					})
					console.log("Error", error);
				});
			}
		})
	}



});


/*app.controller("topup-ctrl", function ($window, $scope, $http) {
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
});*/

app.controller("changepassword-ctrl", function ($scope, $http) {
  $scope.change_password={
		pw_present:'',
		pw_new:'',
		pw_confirm:''
  };

  $scope.change = function() {
		var item = $scope.change_password;
		$http.put(`/rest/user/change_password`, item).then(resp => {
			if (resp.status == 200) {
				$scope.change_password={
					pw_present:'',
					pw_new:'',
					pw_confirm:''
				};
				return Swal.fire({
					width: '400px',
					title: 'Đổi mật khẩu thành công!',
					icon: 'success',
					showConfirmButton: false,
					timer: 1500
				})
			}
		}).catch(error => {
			if (error.status == 404) {
				return Swal.fire({
					width: '400px',
					title: 'Mật khẩu hiện tại không chính xác!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			if (error.status == 400) {
				return Swal.fire({
					width: '400px',
					title: 'Mật khẩu xác nhận không chính xác!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			Swal.fire({
				width: '400px',
				title: 'Lỗi đổi mật khẩu!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error", error);
		});
	}
	
});

/*app.controller("checkmoney-ctrl", function ($scope, $http) {
  $scope.money = 0.0;
});*/

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
