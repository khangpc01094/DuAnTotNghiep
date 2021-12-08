const app = angular.module("seller-app", []);
app.controller("seller-ctrl", function($scope, $http) {

    $scope.form = {};
    $scope.category = {};
    $scope.user = {};
    $scope.products = [];

    $scope.initialize = function() {
        const url = "http://localhost:8080/rest/store/category"
        $http.get(url).then(resp => {
            $scope.category = resp.data;
        });

        $http.get(`rest/store`).then(resp => {
            $scope.user = resp.data;
        });

        $http.get(`rest/store/allProduct`).then(resp => {
            $scope.products = resp.data;
            console.log("products: ", resp.data);
        });

    }

    $scope.initialize();

    $scope.find = function() {
        $http.get(`rest/store/find`).then(resp => {
            $scope.products = resp.data;
            console.log("products find: ", resp.data);
        });
    }


});
app.controller("update-seller-ctrl", function($scope, $http) {

    $scope.form = {};
    $scope.category = {};

    $scope.initialize = function() {
        const url = "http://localhost:8080/rest/store/category"
        $http.get(url).then(resp => {
            $scope.category = resp.data;
        });
    }

    $scope.initialize();

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
            alert("Lỗi up hình!")
            console.log("Error", error);
        })
    }
    $scope.insert = function() {
        alert("123")
    }

})