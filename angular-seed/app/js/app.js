'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', [
  'ngRoute',
  'myApp.filters',
  'myApp.services',
  'myApp.directives',
  'myApp.controllers'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {templateUrl: 'partials/home.html', controller: 'HomeCtrl'});
  $routeProvider.when('/newidea', {templateUrl: 'partials/newidea.html', controller: 'NewIdeaCtrl'});
  $routeProvider.when('/create', {templateUrl: 'partials/poll/create.html', controller: 'CreateCtrl'});
  $routeProvider.when('/poll/:pollId', {templateUrl: 'partials/poll/view.html', controller: 'ViewCtrl'});
  $routeProvider.when('/statistics', {templateUrl: 'partials/poll/statistics.html', controller: 'StatCtrl'});
  $routeProvider.otherwise({redirectTo: '/home'});
}]);
