'use strict';

angular.module('myApp.controllers', []).
    controller('HomeCtrl', ['$scope', '$http', 'IdeaService', function ($scope, $http, IdeaService) {
        $scope.me = {
            ideas: [],
            load: function () {
                IdeaService.load(function (data) {
                    for(var i in data){
                        var idea = data[i];
                        idea.name = U.string(idea.name).truncate(25);
                        idea.description = U.string(idea.description).truncate(200);
                    }
                    $scope.me.ideas = data;
                });
            },
            like: function (idea) {
                IdeaService.like(idea, function (idea) {
                    U.array($scope.me.ideas).findFirst({id: idea.id})['likes'] = idea.likes;
                });
            }
        }
    }])
    .controller('NewIdeaCtrl', ['$scope', 'IdeaService', '$routeParams', function ($scope, IdeaService, $routeParams) {
        $scope.me = {
            newIdea: {},
            message:'',
            create: function () {
                IdeaService.create(this.newIdea, function (data) {
                    $scope.me.message = 'Ідея "' + $scope.me.newIdea.name + '" створено.';
                });
            }
        }
    }])
    .controller('IdeaCtrl', ['$scope', 'IdeaService', '$routeParams', function ($scope, IdeaService, $routeParams) {
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
    .controller('CreateProjectCtrl', ['$scope', 'IdeaService', '$routeParams', function ($scope, IdeaService, $routeParams) {
        var ideaId = $routeParams.ideaId;
        $scope.me = {
            newProject: {idea_id: ideaId, resolution:''},
            message: '',
            create: function () {
                var me = $scope.me;
                me.newProject.idea_id = ideaId;
                IdeaService.createProject(this.newProject, function (data) {
                    me.message = 'Проект "' + me.newProject.name + '" створено.';
                });
            }
        };
    }])
;
var U = {
    array: function (arr) {
        return {
            findFirst: function (prop) {
                for (var key in prop) {
                    if (!key) continue;
                    for (var i in arr) {

                        if(arr[i][key] === prop[key])
                            return arr[i];

                    }
                }
            }
        }
    },
    string: function(str) {
        return {
            truncate: function(l){
                return !str ? '': str.substring(0, l-3).concat('...');
            }
        }
    }
}