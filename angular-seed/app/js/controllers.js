'use strict';

angular.module('myApp.controllers', []).
    controller('HomeCtrl', ['$scope', '$http', 'IdeaService', function ($scope, $http, IdeaService) {
        $scope.me = {
            ideas: null,
            load: function () {
                IdeaService.load(function (data) {
                    $scope.me.ideas = data;
                });
            }
        }
    }])
    .controller('NewIdeaCtrl', ['$scope', 'IdeaService', '$routeParams', function ($scope, IdeaService, $routeParams) {
        $scope.me = {
            newIdea :{},
            create: function () {
                IdeaService.create(this.newIdea, function (data) {
                   // $scope.me.poll = data;
                });
            }
        }


    }])
    .controller('IdeaCtrl', ['$scope', 'IdeaService',  '$routeParams', function ($scope, IdeaService , $routeParams) {
        var ideaId = $routeParams.ideaId;
        $scope.me = {
            idea: {},
            getIdea: function () {
                IdeaService.getIdea(ideaId, function (data) {
                    $scope.me.idea = data;
                });
            }
        }
    }])
    .controller('CreateProjectCtrl', ['$scope', 'IdeaService', function ($scope, IdeaService) {
        $scope.me = {
            newProject: {},
            create: function () {
                var p = $scope.me;
                IdeaService.createProject(this.newProject, function (data) {
                    //q.messageOnCreate = 'Poll "' + q.newPoll + '" has been created.';
                });
            }
        };
    }])
;