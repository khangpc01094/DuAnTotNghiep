var app = angular.module("buyer-app", []);
app.controller("buyer-ctrl", function($scope, $http) {

  $scope.datacart = [];

   $scope.cart = {

    items : [],

    loadCart(){
        $http.get(`/rest/cart/user1`).then(resp => {
            this.items = resp.data;
        }).catch(error => {
            alert("loi truy van"),
            console.log(error);
        })
    },

    add(id) {
			var item = this.items.find(item => item.product.id == id);
			if (item) {
        alert(item)
        alert("item update");
				// item.quantity++;
				// $http.put(`/rest/cart/{item.id}`, item).then(resp => {
        //   $scope.items.push(resp.data);
        // }).catch(error => {
        //   console.log(error)
        // })
			} else {
        alert(id)
        $http.get(`/rest/product/${id}`).then( resp => {
          $scope.datacart = resp.data;
          // console.log(datacart)
          Console.WriteLine(datacart);
        })
        //var itemu = angular.copy($scope.datacart);
				// $http.post(`/rest/createCart`, itemu).then(resp => {
				// 	this.items.push(resp.data);
          
				// }).catch(error => {
        //   console.log(error);
        // })
        
			}
		}, 

    

   }

   $scope.cart.loadCart();

});



app.filter("groupBy",["$parse","$filter",function($parse,$filter){
    return function(array,groupByField){
      var result	= [];
              var prev_item = null;
              var groupKey = false;
              var filteredData = $filter('orderBy')(array,groupByField);
              for(var i=0;i<filteredData.length;i++){
                groupKey = false;
                if(prev_item !== null){
                  if(prev_item[groupByField] !== filteredData[i][groupByField]){
                    groupKey = true;
                  }
                } else {
                  groupKey = true;  
                }
                if(groupKey){
                  filteredData[i]['group_by_key'] =true;  
                } else {
                  filteredData[i]['group_by_key'] =false;  
                }
                result.push(filteredData[i]);
                prev_item = filteredData[i];
              }
              return result;
    }
  }])