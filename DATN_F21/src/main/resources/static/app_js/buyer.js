const app = angular.module("buyer-app", []);
app.controller("buyer-ctrl", function($scope, $http) {

    $scope.form = {};

    $scope.reset = function() {
		$scope.form = {
				
		}
	}

    $scope.address = [];

    //get dia chi
    $scope.getaddreess = function(){
        $http.get(`/address/us`).then( resp => {
            this.address = resp.data;
        })
    }

    $scope.getaddreess();

    
    //dang ky
    $scope.regis = function () {
        var item = angular.copy($scope.form);
        $http.post(`/buyer/regis`, item).then(resp => {
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

    //dang ky nguoiban
    $scope.regisSeller = function () {
        var item = angular.copy($scope.form);
        $http.post(`/seller/regis`, item).then(resp => {
            $scope.reset();
            return Swal.fire({
                width: '400px',
                title: 'Đăng ký cửa hàng thành công!',
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

    $scope.deleteaddress = function(id) {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
          }).then((result) => {
            if (result.isConfirmed) {
                $http.delete(`/buyer/del_address/${id}`).then(resp => {
                    
                })
              Swal.fire(
                'Deleted!',
                'Your file has been deleted.',
                'success',
                location.reload()
              )
            }
          })
        
    }

    //load len form updateaddress
    $scope.editaddress = function(item){
        $scope.form = angular.copy(item)
    }

    //update address
    $scope.updateaddress = function () {
        var item = angular.copy($scope.form);
        $http.put(`/buyer/edit_address`, item).then(resp => {
            // $scope.reset();
            
            return Swal.fire({
                width: '400px',
                title: 'Cập nhật thành công!',
                icon: 'success',
                timer: 99500
            }),
            location.reload();
        }).catch(error => {
            return Swal.fire({
                width: '400px',
                title: 'Lỗi thêm!',
                icon: 'error'
            })
        })
    }

    // $scope.address = function () {
    //     var item = angular.copy($scope.form);
    //     $http.post(`/buyer/add_address`, item).then(resp => {
            
    //         return Swal.fire({
    //             width: '400px',
    //             title: 'Thêm địa chỉ thành công!',
    //             icon: 'success'
    //         }),
    //         $scope.reset();
    //     }).catch(error => {
    //         return Swal.fire({
    //             width: '400px',
    //             title: 'Lỗi thêm địa chỉ!',
    //             icon: 'error'
    //         })
    //         console.log("Error", error);
    //     })
    // }

    $scope.add_address = function() {
        var item = angular.copy($scope.form);
        $http.post(`/buyer/add_address`, item).then(resp => {
            $scope.reset();
            return Swal.fire({
                width: '400px',
                title: 'Thêm địa chỉ thành công!',
                icon: 'success'
            })
        }).catch(error => {
            return Swal.fire({
                width: '400px',
                title: 'Thêm địa chỉ thất bại!',
                icon: 'error'
            })
            console.log("Error", error);
        })
    }

})