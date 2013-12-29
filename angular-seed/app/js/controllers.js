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
    .controller('FindCtrl', ['$scope', 'IdeaService', function ($scope, IdeaService) {
        $scope.me = {
            findInput: '',
            result: [],
            find: function () {
                if (this.findInput.trim() != '')
                    IdeaService.find(this.findInput, function (data) {
                        $scope.me.result = data;
                    });
            }
        }
    }])
    .controller('CreateCtrl', ['$scope', 'IdeaService', function ($scope, IdeaService) {
        $scope.me = {
            newPoll: '',
            messageOnCreate: '',
            create: function () {
                var p = $scope.me;
                IdeaService.create(this.newPoll, function (data) {
                    q.messageOnCreate = 'Poll "' + q.newPoll + '" has been created.';
                });
            }
        };
    }])
;