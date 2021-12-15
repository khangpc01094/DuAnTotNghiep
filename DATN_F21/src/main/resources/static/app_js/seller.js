const app = angular.module("seller-app", []);
$(document).ready(function() {
	$('#datatable1').hide('fast');
	// $('#title1').hide('fast');
	$('#button123').hide('fast');
	$('#ImageProduct').hide('fast');
	// $('#ProductImage').hide('fast');
	$('#DetailImage').hide('fast');

	$('#create1').prop('disabled', false);
	$('#update1').prop('disabled', true);

	$('#updateImg').prop('disabled', true);
	$('#deleteImg').prop('disabled', true);
});

app.controller("seller-ctrl", function($scope, $http) {

	$scope.Order = {
		SumOrderStatusOne: {},
		OrderOne: [],
		OrderTwo: [],
		OrderFather: [],
		OrderFour: [],

		getCheckAllStatusOne() {
			$http.get(`/rest/order/checkAll`).then(resp => {
				$scope.Order.getOrderOne();
				$scope.Order.getOrderTwo();
				$scope.Order.getOrderFather();
				$scope.Order.getOrderFour();
			}).catch(error => {
				console.log(error);
			})
		},

		getSumStatusOne() {
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
			$http.get(`/rest/order/orderConfirm/${id}`).then(resp => {
				$scope.Order.getOrderOne();
				$scope.Order.getOrderTwo();
				$scope.Order.getOrderFather();
				$scope.Order.getOrderFour();
			}).catch((error) => {
				alert("cap nhat khong dc");
				console.log(error);
			});
		},

		orderRefuse(id) {
			$http.get(`/rest/order/orderRefuse/${id}`).then(resp => {
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


	$scope.form = {};
	$scope.category = {};
	$scope.loaisp = {};
	$scope.user = {};
	$scope.items = [];

	$scope.images = [];
	$scope.image = {};
	$scope.product = {};


	$scope.initialize = function() {
		$http.get('/rest/store/category').then(resp => {
			$scope.category = resp.data;
		});

		$http.get(`/rest/store`).then(resp => {
			$scope.user = resp.data;
		});

		$http.get(`/rest/store/allProduct`).then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createdate = new Date(item.createdate)
				$http.get(`/rest/proimg/quanlity_by_product/${item.id}`).then(resp2 => {
					item.quanlity = resp2.data;
				});
			});
		});

		$scope.form.status = true;
	};

	$scope.loadTable = function() {
		$http.get(`/rest/store/allProduct`).then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				$http.get(`/rest/proimg/quanlity_by_product/${item.id}`).then(resp2 => {
					item.quanlity = resp2.data;
				});
			})
		})
	};


	$scope.loadDatatable = function() {
		const url = "http://localhost:8080/rest/store/allProduct"
		$http.get(url).then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createdate = new Date(item.createdate)
			});
		});
	};


	$scope.initialize();




	//hiển thị lên form
	// $scope.edit = function(item) {
	//     $scope.form = angular.copy(item);
	//     $('#update1').prop('disabled', false);
	//     $('#create1').prop('disabled', true);
	// };

	$scope.resetImg = function() {
		$scope.image = {
			// createDate: new Date(),
			image: 'cloud-upload.jpg',
			available: true,
		};
	};

	$scope.edit = function(item) {
		$('#ImageProduct').hide('fast');
		$scope.form = angular.copy(item);
		// console.log('id', item.id);

		$http.get(`/rest/proimg/images/${item.id}`).then(resp => {
			$scope.images = resp.data;
		});
		$scope.resetImg();
		$('#title1').show(1000);
		$('#title2').show(1000);
		$('#button123').hide('fast');
		$('#ImageProduct').hide('fast');
		$('#ProductImage').show(2000);
		$('#DetailImage').show(2000);
		// $('#title1').show(2000);
		// $('#datatable1').show(2000);

		$('#updateImg').prop('disabled', false);
		$('#deleteImg').prop('disabled', false);

		$('#update1').prop('disabled', false);
		$('#create1').prop('disabled', true);
	};

	$scope.editImages = function(img) {
		$('#ImageProduct').show(500);
		$scope.image = img;
		console.log('img: ', img);
		$('#updateImg').prop('disabled', false);
		$('#deleteImg').prop('disabled', false);

		$('#title1').show(1000);
		$('#title2').hide('fast');
		$('#button123').show(2000);
		$('#ImageProduct').show(2000);
		$('#ProductImage').hide('fast');
		$('#DetailImage').show(2000);
		// $scope.imgShow = img.picture;
	};

	//upload hình
	$scope.imageChanged = function(files) {
		var data = new FormData(); //tao doi tuong
		data.append('files', files[0]); //lay file ngta chon bo vo formdata
		$http.post('/rest/upload/images', data, { //post len 
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.image.picture = resp.data.name;
		}).catch(error => {
			console.error("error", error);
			return Swal.fire({
				width: '400px',
				title: 'Error upload image!',
				icon: 'error',
			});
		});
	};

	//cập nhật sp mới
	$scope.updateImg = function() {
		var item = angular.copy($scope.image); //lay sp ra
		var url = `/rest/proimg/${item.id}`;
		$http.put(url, item).then(resp => { //resp la data moi tra ve
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			$scope.initialize();
			return Swal.fire({
				width: '400px',
				title: 'Cập nhật hình ảnh sản phẩm thành công!',
				icon: 'success',
			})
		}).catch(error => {
			console.log("Error", error)
			return Swal.fire({
				width: '400px',
				title: 'Lỗi cập nhật hình ảnh sản phẩm!',
				icon: 'error',
			});
		})
	};

	//xóa hình ảnh sản phẩm
	$scope.deleteImg = function() {
		$scope.image.picture = 'macdinh.png';
		var item = angular.copy($scope.image); //lay sp ra
		var url = `/rest/proimg/${item.id}`;
		$http.put(url, item).then(resp => { //resp la data moi tra ve
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			$scope.initialize();
			return Swal.fire({
				width: '400px',
				title: 'Xóa hình ảnh sản phẩm thành công!',
				icon: 'success',
			});
		}).catch(error => {
			console.log("Error", error)
			return Swal.fire({
				width: '400px',
				title: 'Lỗi xóa hình ảnh sản phẩm!',
				icon: 'error',
			})
		})
	};

	$scope.find = function() {
		var cate = $('#cate').val();
		var name1 = $('#product').val();
		var sid1 = $('#sid').val();

		$http.get(`http://localhost:8080/rest/store/finds/${name1}/${sid1}`).then(resp => {
			if (name1 === null) {
				$scope.initialize();
			} else {
				$scope.items = resp.data;
				$scope.items.forEach(item => {
					$http.get(`/rest/proimg/quanlity_by_product/${item.id}`).then(resp2 => {
						item.quanlity = resp2.data;
					});
				})
				console.log("items find: ", resp.data);
			}
		});

	};

	$scope.findCates = function() {
		var loaisp = $('#loaisp').val();
		console.log(loaisp);
		var name1 = $('#product').val();
		var sid1 = $('#sid').val();
		$http.get(`http://localhost:8080/rest/product/category/${loaisp}/${sid1}`).then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				$http.get(`/rest/proimg/quanlity_by_product/${item.id}`).then(resp2 => {
					item.quanlity = resp2.data;
				});
			})
			console.log("items findCates: ", resp.data);
		});
	};

	//xóa form
	$scope.reset = function() {
		$('#title1').show(1000);
		$('#button123').hide('fast');
		$('#ImageProduct').hide('fast');
		$('#detai').hide('fast');
		$('#ProductImage').show(2000);
		$('#DetailImage').show(2000);
		$scope.form = {
			createDate: new Date().toLocaleString(),
			image: 'macdinh.png',
		};
		$scope.form.nameProduct = "";
		$scope.form.status = true;
		$scope.initialize();
		$('#update1').prop('disabled', true);
		$('#create1').prop('disabled', false);
	};

	$scope.resetForm = function() {
		$scope.form = {
			createDate: new Date().toLocaleString(),
			image: 'cloud-upload.jpg',
		};
		$scope.form.nameProduct = "";
		$scope.form.status = true;
		$scope.initialize();
		$('#update1').prop('disabled', true);
		$('#create1').prop('disabled', false);
	};


	//thêm sp mới
	$scope.create = function() {
		var item = angular.copy($scope.form);
		var img = "macdinh.png";
		var url = `/rest/product`;
		$http.post(url, item).then(resp => {
			if ($scope.form.images === undefined) {
				$scope.form.images = img;
			}
			resp.data.createdate = new Date(resp.data.createdate)
			$scope.items.push(resp.data);
			$scope.reset();
			$scope.initialize();
			return Swal.fire({
				width: '400px',
				title: 'Thêm sản phẩm thành công!',
				icon: 'success',
			})
		}).catch(error => {
			console.log("Error", error)
			return Swal.fire({
				width: '400px',
				title: 'Lỗi thêm sản phẩm!',
				icon: 'error',
			})
		});
	};
	//cập nhật sp mới
	$scope.update = function() {
		var img = $scope.form.images;
		var item = angular.copy($scope.form); //lay sp ra
		var url = `/rest/product/${item.id}`;
		$http.put(url, item).then(resp => { //resp la data moi tra ve
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items[index] = item;
			if ($scope.form.images === undefined) {
				$scope.form.images = img;
			};
			$scope.initialize();
			return Swal.fire({
				width: '400px',
				title: 'Cập nhật sản phẩm thành công!',
				icon: 'success',
			})

		}).catch(error => {
			console.log("Error", error)
			return Swal.fire({
				width: '400px',
				title: 'Lỗi cập nhật sản phẩm!',
				icon: 'error',
			})
		})
	};
	//xóa sp
	$scope.delete = function(item) {
		var url = `/rest/product/${item.id}`;
		$http.delete(url).then(resp => {
			var index = $scope.items.findIndex(p => p.id == item.id);
			$scope.items.splice(index, 1); //splice la ham trong js de xóa, 1 la xoa 1 phan tu
			$scope.reset();
			$scope.initialize();
			return Swal.fire({
				width: '400px',
				title: 'xóa sản phẩm thành công!',
				icon: 'success',
			})
		}).catch(error => {
			console.log("Error", error)
			return Swal.fire({
				width: '400px',
				title: 'Lỗi xóa sản phẩm!',
				icon: 'error',
			})
		})
	};

	// upload hình
	$scope.imageChangedProduct = function(files) {
		var data = new FormData(); //tao doi tuong
		data.append('files', files[0]); //lay file ngta chon bo vo formdata
		$http.post('/rest/upload/images', data, { //post len 
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.images = resp.data.name; //lay name the vao thuoc tinh image
		}).catch(error => {
			console.log("error", error);
			return Swal.fire({
				width: '400px',
				title: 'error upload image!',
				icon: 'error',
			})

		});
	};




	$scope.pager = {
		page: 0,
		size: 5,
		get items() { //lọc sp theo trang
			var start = this.page * this.size; //vị trí bắt đầu
			return $scope.items.slice(start, start + this.size); //slice để cắt
		},
		get count() { //tổng số trang
			return Math.ceil(1.0 * $scope.items.length / this.size); //tổng số phần tử chia kích thước 1 trang và làm tròn
		},
		first() {
			this.page = 0;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
		},
		last() {
			this.page = this.count - 1;
		}
	};
});

