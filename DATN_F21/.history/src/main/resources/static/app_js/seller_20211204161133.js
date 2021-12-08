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

    var id1 = $('cate').val();
    var id = document.getElementById("cate").value;
    document.setAttribute("id", id);
    console.log("category 1: ", id);
    console.log("id1: ", id1);
    $scope.find = function(nameProduct, id) {

        var name1 = $('nameProduct').val();
        console.log("name1: ", name1);
        $http.get(`rest/store/find/{product}/{category}`).then(resp => {
            if (nameProduct === undefined) {
                $scope.initialize();
            } else {
                $scope.products = resp.data;
                console.log("products find: ", resp.data);
                console.log("product: ", nameProduct);
                console.log("category: ", id);
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