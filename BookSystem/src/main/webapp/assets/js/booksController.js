var app = angular.module('BookSystem', []);

app.controller('BookController', function($http) {
	
	var me = this;
		
	me.mvBooks = [];
	me.mpBooks = [];
	
	me.fetchBooks = function() {
		
		
		$http.get('/MemberVeiw/json/data/mv/books')
			.then(function(response) {
				me.mvBooks = response.data;
		});
			
			
		$http.get('/MemberVeiw/json/data/mp/books')
		.then(function(response) {
			me.mpBooks = response.data;
		});
				
	}
	
});