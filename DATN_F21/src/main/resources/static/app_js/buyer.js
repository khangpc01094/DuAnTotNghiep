const app = angular.module("buyer-app", []);
app.controller("buyer-ctrl", function($scope, $http) {

    $scope.form = {};

    $scope.reset = function() {
		$scope.form = {
				
		}
	}

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
            return Swal.fire({
                width: '400px',
                title: 'Lỗi đăng ký!',
                icon: 'error',
            })
            // console.log("Error", error);
        })
    }

    // $scope.hello = function(){
    //     alert("hello")
    // }

    // $scope.address = function () {
    //     var item = angular.copy($scope.form);
    //     $http.post(`/buyer/add_address`, item).then(resp => {
    //         $scope.reset();
    //         return Swal.fire({
    //             width: '400px',
    //             title: 'Thêm thành công!',
    //             icon: 'success'
    //         })
    //     }).catch(error => {
    //         return Swal.fire({
    //             width: '400px',
    //             title: 'Lỗi thêm!',
    //             icon: 'error'
    //         })
    //         console.log("Error", error);
    //     })
    // }

    // $scope.search = function() {
	// 	var item = angular.copy($scope.form);
	// 	$http.get(`/rest/search/${item.name}`).then(resp => {
	// 		this.items = resp.data;
    //         console.log(this.items);
    //     }).catch(error => {
    //         console.log(error)
    //     })
	// }

})