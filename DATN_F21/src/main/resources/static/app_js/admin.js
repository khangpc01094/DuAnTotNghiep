const app = angular.module("admin-app", []);
app.controller("admin-ctrl", function($scope, $http) {


})

app.controller("user-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.no;
	$scope.form = {
		birthday: new Date(),
		picture: 'user_default.png',
		gender: true,
	};
	
	
	$scope.initialize = function() {
		$http.get("/rest/user/findall").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.birthday = new Date(item.birthday)
			})
		});
	}
	
	$scope.initialize();
	
	$scope.edit = function(item) {	
		$scope.form = angular.copy(item);		
		$(".nav a:eq(0)").tab('show')
	}
	
	$scope.clear = function() {	
		$scope.form = {
			birthday: new Date(),
			picture: 'user_default.png',
			gender: true,
		};
	}
	
	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},

		first() {
			this.page = 0;
			$scope.no=this.page*10;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
			$scope.no=this.page*10;
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
			$scope.no=this.page*10;
		},
		last() {
			this.page = this.count - 1;
			$scope.no=this.page*10;
		},
	}
	
	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/user/information/update`, item).then(resp => {
			if (resp.status == 200) {
				$scope.initialize();
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


	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/user/buyer/regis`, item).then(resp => {
			if (resp.status == 200) {
				$scope.initialize();
				return Swal.fire({
					width: '400px',
					title: 'Thêm thành công!',
					icon: 'success',
					showConfirmButton: false,
					timer: 1500
				})
			}
		}).catch(error => {
			/*if (error.status == 404) {
				return Swal.fire({
					width: '400px',
					title: 'Không tìm thấy người dùng này!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}*/
			Swal.fire({
				width: '400px',
				title: 'Lỗi Thêm!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})			
			console.log("Error", error);
		});
	}

	

	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/admin/rest/upload/user', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.picture = resp.data.name;
		}).catch(error => {
			return Swal.fire({
				width: '400px',
				title: 'Lỗi tải hình ảnh!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			
			console.log("Error", error);
		})
	}
	
	$scope.search = function(name) {	
		if (name === undefined) {
			$scope.initialize();
			
		}else{
			
			$http.get(`/rest/user/findbyname/${name}`).then(resp => {
				$scope.items = resp.data;
				$scope.items.forEach(item => {
					item.birthday = new Date(item.birthday)
				})
			});
		}		
	}
})


app.controller("store-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.no;
	
	
	
	$scope.initialize = function() {
		$http.get("/rest/store/findall").then(resp => {
			$scope.items = resp.data;		
			$scope.items.forEach(item => {
				$http.get(`/rest/product/quanlity_by_store/${item.id}`).then(resp2 => {
					item.quanlity = resp2.data;		
				});		
			})
		});
	}

	$scope.initialize();
	
		
	
	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},

		first() {
			this.page = 0;
			$scope.no=this.page*10;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
			$scope.no=this.page*10;
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
			$scope.no=this.page*10;
		},
		last() {
			this.page = this.count - 1;
			$scope.no=this.page*10;
		},
	}
	
	$scope.search = function(name) {
		if (name === undefined) {
			$scope.initialize();
		}else{
			$http.get(`/rest/store/findbyname/${name}`).then(resp => {
				$scope.items = resp.data;		
				$scope.items.forEach(item => {
					$http.get(`/rest/product/quanlity_by_store/${item.id}`).then(resp2 => {
						item.quanlity = resp2.data;		
					});		
				})
			});
		}
	}
})

app.controller("category-ctrl", function($scope, $http) {
	$scope.items = [];
	$scope.no;
	$scope.form = {
		status: true,
		picture: 'macdinh.jpg',
	};
	
	
	$scope.initialize = function() {
		$http.get("/rest/category/findall").then(resp => {
			$scope.items = resp.data;		
		});
	}

	$scope.initialize();
	
	$scope.edit = function(item) {
		$scope.form = angular.copy(item);
	}	
	
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/admin/rest/upload/category', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.picture = resp.data.name;
		}).catch(error => {
			return Swal.fire({
				width: '400px',
				title: 'Lỗi tải hình ảnh!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error", error);
		})
	}

	
	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},

		first() {
			this.page = 0;
			$scope.no=this.page*10;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
			$scope.no=this.page*10;
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
			$scope.no=this.page*10;
		},
		last() {
			this.page = this.count - 1;
			$scope.no=this.page*10;
		},
	}
	
	$scope.clear = function() {
		$scope.form = {
			status: true,
			picture: 'macdinh.jpg',
		};
	}
	
	$scope.create = function() {
		var item = angular.copy($scope.form);
		$http.post(`/rest/category`, item).then(resp => {
			if (resp.status == 200) {
				$scope.items.push(resp.data);
				$scope.clear();
				return Swal.fire({
					width: '400px',
					title: 'Thêm mới thành công!',
					icon: 'success',
					showConfirmButton: false,
					timer: 1500
				})
			}
		}).catch(error => {
			if (error.status == 400) {
				return Swal.fire({
					width: '400px',
					title: 'Id đã tồn tại!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			Swal.fire({
				width: '400px',
				title: 'Lỗi thêm mới loại sản phẩm!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error", error);
		});
	}


	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/category/${item.id}`, item).then(resp => {
			if (resp.status == 200) {
				var index = $scope.items.findIndex(a => a.id == item.id);
				$scope.items[index] = item;
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
					title: 'Không tìm thấy loại sản phẩm này!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			Swal.fire({
				width: '400px',
				title: 'Lỗi cập nhật loại sản phẩm!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error", error);
		});
	}

})

app.controller("authorization-ctrl", function($scope, $http) {
	$scope.no;
	$scope.roles = [];
	$scope.users = [];
	$scope.authorities=[];
	
	$scope.initialize = function(){
		$http.get("/rest/role").then(resp =>{
			$scope.roles = resp.data;
		})
		
		$http.get("/rest/user/findall").then(resp =>{
			$scope.users = resp.data;
		})
		
		$http.get("/rest/authorities").then(resp =>{
			$scope.authorities = resp.data;
		})
	}
	
	$scope.initialize();
	
	$scope.authority_of = function(user,role){
		if($scope.authorities){
			return $scope.authorities.find(ur => ur.user.userid == user.userid && ur.role.id == role.id);
		}
	}
	
	$scope.authority_changed = function(user,role){
		var authority = $scope.authority_of(user,role);		
		if(authority){
			$scope.revoke_authority(authority);
		}
		else{
			authority = {user:user,role:role};
			$scope.grant_authority(authority);
		}
	}
	
	$scope.grant_authority = function(authority){		
		$http.post(`/rest/authorities`,authority).then(resp =>{
			$scope.authorities.push(resp.data)
			return Swal.fire({
				width: '400px',
				title: 'Cấp quyền sử dụng thành công!',
				icon: 'success',
				showConfirmButton: false,
				timer: 1500
			})
		}).catch(error =>{
			return Swal.fire({
				width: '400px',
				title: 'Lỗi cấp quyền!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error",error);
		})
	}

	
	$scope.revoke_authority = function(authority){
		$http.delete(`/rest/authorities/${authority.id}`).then(resp =>{
			var index = $scope.authorities.findIndex(a => a.id == authority.id);
			$scope.authorities.splice(index,1);
		    return Swal.fire({
				width: '400px',
				title: 'Thu hồi quyền sử dụng thành công!',
				icon: 'success',
				showConfirmButton: false,
				timer: 1500
			})
		}).catch(error =>{
			return Swal.fire({
				width: '400px',
				title: 'Lỗi thu hồi quyền!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})
			console.log("Error",error);
		})
	}

	
	$scope.pager = {
		page: 0,
		size: 10,
		get users() {
			var start = this.page * this.size;
			return $scope.users.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.users.length / this.size);
		},

		first() {
			this.page = 0;
			$scope.no=this.page*10;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
			$scope.no=this.page*10;
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
			$scope.no=this.page*10;
		},
		last() {
			this.page = this.count - 1;
			$scope.no=this.page*10;
		},
	}
	
	$scope.search = function(name) {
		if (name === undefined) {
			$scope.initialize();
		}else{
			$http.get(`/rest/user/findbyname/${name}`).then(resp => {
				$scope.users = resp.data;		
			});
		}
	}
	
})

app.controller("statistical-ctrl", function($scope, $http) {	
	$scope.items = [];
	$scope.no;
	
	$scope.finddate={};
	
	
	$scope.initialize = function() {
		$scope.total = 0;
		$http.get("/rest/order/statistical").then(resp => {
			$scope.items = resp.data;		
			$scope.items.forEach(item => {
				item.bookingdate = new Date(item.bookingdate);
				$scope.total += item.intomoney;
			})
		});
	}

	$scope.initialize();
	
		
	
	$scope.pager = {
		page: 0,
		size: 10,
		get items() {
			var start = this.page * this.size;
			return $scope.items.slice(start, start + this.size);
		},
		get count() {
			return Math.ceil(1.0 * $scope.items.length / this.size);
		},

		first() {
			this.page = 0;
			$scope.no=this.page*10;
		},
		prev() {
			this.page--;
			if (this.page < 0) {
				this.last();
			}
			$scope.no=this.page*10;
		},
		next() {
			this.page++;
			if (this.page >= this.count) {
				this.first();
			}
			$scope.no=this.page*10;
		},
		last() {
			this.page = this.count - 1;
			$scope.no=this.page*10;
		},
	}
	
	$scope.find = function() {	
		$scope.total = 0;
		var finddate = angular.copy($scope.finddate);
		$http.post(`/rest/order/statistical/findbydate`, finddate).then(resp => {
				$scope.items = resp.data;		
				$scope.items.forEach(item => {
					item.bookingdate = new Date(item.bookingdate);
					$scope.total += item.intomoney;
				})	
		}).catch(error => {
				alert("Lỗi");			
		});
	}
})
