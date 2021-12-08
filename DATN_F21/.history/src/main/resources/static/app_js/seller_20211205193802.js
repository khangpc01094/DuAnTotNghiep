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


    $scope.click = function(idp) {
        alert($('#idp').val());
    }

    $scope.find = function() {
        var cate = $('#cate').val();

        var name1 = $('#product').val();
        var sid1 = $('#sid').val();
        $http.get(`http://localhost:8080/rest/store/finds/${name1}/${sid1}`).then(resp => {
            if (name1 === null) {
                $scope.initialize();
            } else {
                $scope.products = resp.data;
                console.log("products find: ", resp.data);
            }
        });
    }

    $scope.pager = {
        page: 0,
        size: 10,
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