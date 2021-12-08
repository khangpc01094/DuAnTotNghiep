const app = angular.module("seller-app", ['toaster', 'ngAnimate']);
app.controller("seller-ctrl", function($scope, $http, toaster) {
    // ['toastr']
    $scope.form = {};
    $scope.category = {};
    $scope.user = {};
    $scope.items = [];

    $scope.initialize = function() {
        const url = "http://localhost:8080/rest/store/category"
        $http.get(url).then(resp => {
            $scope.category = resp.data;
        });

        $http.get(`rest/store`).then(resp => {
            $scope.user = resp.data;
        });

        $http.get(`rest/store/allProduct`).then(resp => {
            $scope.items = resp.data;
            // console.log("items: ", resp.data);
        });

    }

    $scope.loadDatatable = function() {
        const url = "http://localhost:8080/rest/store/allProduct"
        $http.get(url).then(resp => {
            $scope.items = resp.data;
            // console.log("items: ", resp.data);
        });
    };

    $scope.initialize();
    // Display an info toast with no title
    // $scope.pop = function() {
    //     toaster.pop('info', "title", "text");
    // };

    // alert($('#sid').val());

    $scope.find = function() {
        var cate = $('#cate').val();
        var name1 = $('#product').val();
        var sid1 = $('#sid').val();
        $http.get(`http://localhost:8080/rest/store/finds/${name1}/${sid1}`).then(resp => {
            if (name1 === null) {
                $scope.initialize();
            } else {
                $scope.items = resp.data;
                console.log("items find: ", resp.data);
            }
        });
    };

    //xóa form
    $scope.reset = function() {
        $scope.form = {
            // createDate: new Date(),
            image: 'cloud-upload.jpg',
            available: true,
        };
    };

    //hiển thị lên form
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
        // $("#pills-tab button:eq(0)").tab('show');
    };

    //thêm sp mới
    $scope.create = function() {
        var item = angular.copy($scope.form);
        var url = `/rest/products`;
        $http.post(url, item).then(resp => {
            // resp.data.createDate = new Date(resp.data.createDate)
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Thêm mới sản phẩm thành công");
            $scope.initialize();
        }).catch(error => {
            alert("Lỗi thêm mới sản phẩm");
            console.log("Error", error)
        });
    };
    //cập nhật sp mới
    $scope.update = function() {
        var item = angular.copy($scope.form); //lay sp ra
        var url = `/rest/products/${item.id}`;
        $http.put(url, item).then(resp => { //resp la data moi tra ve
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
            alert("Cập nhật sản phẩm thành công!");
            $scope.loadDatatable();
        }).catch(error => {
            alert("Lỗi cập nhật sản phẩm!");
            console.log("Error", error)
        })
    };
    //xóa sp
    $scope.delete = function(item) {
        var url = `/rest/products/${item.id}`;
        $http.delete(url).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items.splice(index, 1); //splice la ham trong js de xóa, 1 la xoa 1 phan tu
            $scope.reset();
            alert("xóa sản phẩm thành công");
            $scope.initialize();
        }).catch(error => {
            alert("lỗi xóa sản phẩm");
            console.log("Error", error)
        })
    };
    //upload hình
    $scope.imageChanged = function(files) {
        var data = new FormData(); //tao doi tuong
        data.append('files', files[0]); //lay file ngta chon bo vo formdata
        $http.post('/rest/upload/images', data, { //post len 
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.images = resp.data.name; //lay name the vao thuoc tinh image
        }).catch(error => {
            alert("error upload image");
            console.log("error", error);
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

app.controller("update-seller-ctrl", function($scope, $http) {

    $scope.form = {};
    $scope.items = [];
    $scope.images = [];

    $scope.initialize = function() {
        const url = "http://localhost:8080/rest/proimg/all"
            // const url = "http://localhost:8080/rest/store/allProduct"
        $http.get(url).then(resp => {
            $scope.items = resp.data;
        });
    }

    $scope.loadImages = function() {
        //     // const url = "http://localhost:8080/rest/proimg/all"
        //     // const url = "http://localhost:8080/rest/store/allProduct"
        var pid = $('#pid').val();
        console.log('pid: ' + pid);
        $http.get(`rest/proimg/images/1`).then(resp => {
            $scope.images = resp.data;
            console.log('images loaded: ', resp.data);
        });

    }

    $scope.initialize();
    // $scope.loadImages();

    //hiển thị lên form
    $scope.edit = function(item) {

        $scope.form = angular.copy(item);
        // $("#pills-tab button:eq(0)").tab('show');
    };

    //upload hình
    $scope.imageChanged = function(files) {
        var data = new FormData(); //tao doi tuong
        data.append('files', files[0]); //lay file ngta chon bo vo formdata
        $http.post('/rest/upload/images', data, { //post len 
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.images = resp.data.name; //lay name the vao thuoc tinh image
        }).catch(error => {
            alert("error upload image");
            console.log("error", error);
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