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
  $routeProvider.when('/idea/:ideaId', {templateUrl: 'partials/viewIdea.html', controller: 'IdeaCtrl'});
  $routeProvider.when('/createProject/:ideaId', {templateUrl: 'partials/createProject.html', controller: 'CreateProjectCtrl'});
  $routeProvider.when('/statistics', {templateUrl: 'partials/poll/statistics.html', controller: 'StatCtrl'});
  $routeProvider.otherwise({redirectTo: '/home'});
}]);
