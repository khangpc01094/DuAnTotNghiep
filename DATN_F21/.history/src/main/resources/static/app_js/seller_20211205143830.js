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
            // console.log("products: ", resp.data);
        });

    }

    $scope.initialize();

    $scope.find = function() {
        var cate = $('#cate').val();
        var name1 = $('#product').val();
        var sid = $('#sid').val();

        console.log("name1: ", name1);
        console.log("cate: ", cate);
        console.log("sid: ", sid);

        $http.get(`http://localhost:8080/rest/store/finds/${name1}/${sid}`).then(resp => {
            if (name1 === null) {
                $scope.initialize();
            } else {
                $scope.products = resp.data;
                console.log("products find: ", resp.data);
            }
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
        //  $scope.insert = function() {
        //     var item = angular.copy($scope.form);
        //     $http.post(`/rest/wallet`, item).then(resp => {
        //         if (resp.status == 200) {
        //             alert("save thanh cong");
        //         }
        //     }).catch(error => {
        //         if (error.status == 400) {
        //             alert("Thông tin ngân hàng không chính xác");
        //         }
        //     });
        // }

})