'use strict';


var templates = 'simple'; // 'main'
// Declare app level module which depends on filters, and services
angular.module('myApp', [
  'ngRoute',
  'myApp.filters',
  'myApp.services',
  'myApp.directives',
  'myApp.controllers'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {templateUrl: 'partials/'+templates+'/home.html', controller: 'HomeCtrl'});
  $routeProvider.when('/newidea', {templateUrl: 'partials/'+templates+'/new-idea.html', controller: 'NewIdeaCtrl'});
  $routeProvider.when('/idea/:ideaId', {templateUrl: 'partials/'+templates+'/view-idea.html', controller: 'IdeaCtrl'});
  $routeProvider.when('/createProject/:ideaId', {templateUrl: 'partials/'+templates+'/create-project.html', controller: 'CreateProjectCtrl'});
  $routeProvider.when('/statistics', {templateUrl: 'partials/'+templates+'/poll/statistics.html', controller: 'StatCtrl'});
  $routeProvider.otherwise({redirectTo: '/home'});
}]);
