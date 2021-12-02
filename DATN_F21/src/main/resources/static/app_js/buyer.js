const app = angular.module("buyer-app", []);
app.controller("buyer-ctrl", function($scope, $http) {
})

app.controller("login-ctrl",function($scope,$http){
	$scope.email;
	$scope.password;
});

app.controller("information-ctrl",function($scope,$http){
	$scope.form = {};
		
	$scope.director;
	$scope.initialize = function() {
		$http.get("/rest/user/information").then(resp => {
			$scope.form = resp.data;
			$scope.form.birthday = new Date($scope.form.birthday);		
		});
	}
	
	$scope.initialize();

	
	$scope.update = function() {
		var item = angular.copy($scope.form);
		$http.put(`/rest/user/information/update`, item).then(resp => {
			if (resp.status == 200) {
				/*return Swal.fire({
					width: '400px',
					title: 'Cập nhật khách hàng thành công!',
					icon: 'success',
					showConfirmButton: false,
					timer: 1500
				})*/
				alert("Cap nhat thanh cong");
			}
		}).catch(error => {
			/*if (error.status == 404) {
				return Swal.fire({
					width: '400px',
					title: 'Không tìm thấy khách hàng này!',
					icon: 'error',
					confirmButtonText: 'Ok',
				})
			}
			Swal.fire({
				width: '400px',
				title: 'Lỗi cập nhật khách hàng!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})*/
			alert("Cap nhat loi");
			console.log("Error", error);
		});
	}
	
	
	$scope.imageChanged = function(files) {
		var data = new FormData();
		data.append('file', files[0]);
		$http.post('/rest/upload/user', data, {
			transformRequest: angular.identity,
			headers: { 'Content-Type': undefined }
		}).then(resp => {
			$scope.form.picture = resp.data.name;
		}).catch(error => {
			/*return Swal.fire({
				width: '400px',
				title: 'Lỗi uplaod hình ảnh!',
				icon: 'error',
				confirmButtonText: 'Ok',
			})*/
			alert("Lôi up hinh")
			console.log("Error", error);
		})
	}
});

app.controller("wallet-ctrl",function($scope,$http){
	$scope.cardbrand=[];
	$scope.form = {};
	
	var url = "http://localhost:2021/rest/cardbrand";
	$http.get(url).then(resp =>{
		$scope.cardbrand = resp.data;		
	}).catch(error =>{
		alert("Lỗi");		
	})
	
	$scope.save = function() {	
		var item = angular.copy($scope.form);
		$http.post(`/rest/wallet`, item).then(resp => {
			if (resp.status == 200) {		
				alert("save thanh cong");
			}
		}).catch(error => {
			if (error.status == 400) {		
				alert("Thông tin ngân hàng không chính xác");
			}
		});
	}
});

app.controller("topup-ctrl",function($window,$scope,$http){
	$scope.cardbrand=[];
	$scope.form = {};
	
	var url = "http://localhost:2021/rest/cardbrand";
	$http.get(url).then(resp =>{
		$scope.cardbrand = resp.data;		
	}).catch(error =>{
		alert("Lỗi");		
	})
	
	$scope.contineu_money = function() {	
		var item = angular.copy($scope.form);
		$http.post(`/rest/wallet/topup`, item).then(resp => {
			if (resp.status == 200) {
				alert("hop lệ");
				$window.location.href = '/account/wallet/topup/checkmoney';
			}
		}).catch(error => {
			if (error.status == 400) {		
				alert("Thông tin ngân hàng không chính xác");
			}
		});
	}
});

app.controller("changepassword-ctrl",function($scope,$http){
	$scope.pw_present;
	$scope.pw_new;
	$scope.pw_confirm;
});

app.controller("checkmoney-ctrl",function($scope,$http){
	$scope.money=0.0;
});


app.controller("transaction-ctrl",function($scope,$http){
	$scope.items = [];
	$scope.finddate={};
	
		
	$scope.initialize = function() {
		$http.get("/rest/transaction/byuserid").then(resp => {
			$scope.items = resp.data;
			$scope.items.forEach(item => {
				item.createdate = new Date(item.createdate);	
			})
			
			
		});
	}
	
	$scope.pager = {
		page: 0,
		size: 5,
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
		var finddate = angular.copy($scope.finddate);
		$http.post(`/rest/transaction/findbydate`, finddate).then(resp => {
				$scope.items = resp.data;
				$scope.items.forEach(item => {
					item.createdate = new Date(item.createdate);	
				})		
		}).catch(error => {
				alert("Lỗi");			
		});
	}
	
	$scope.initialize();
});
