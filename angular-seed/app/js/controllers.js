'use strict';

angular.module('myApp.controllers', []).
    controller('HomeCtrl', ['$scope', '$http', 'PollService', function ($scope, $http, PollService) {
        $scope.me = {
            polls: null,
            load: function () {
                PollService.load(function (data) {
                    $scope.me.polls = data;
                });
            }
        }
    }])
    .controller('NewIdeaCtrl', ['$scope', 'PollService', '$routeParams', function ($scope, PollService, $routeParams) {
        var pollId = $routeParams.pollId;
        $scope.me = {
            poll:null,
            getPoll: function () {
                PollService.getPoll(pollId, function (data) {
                    $scope.me.poll = data;
                });
            }
        }


    }])
    .controller('FindCtrl', ['$scope', 'PollService', function ($scope, PollService) {
        $scope.me = {
            findInput: '',
            result: [],
            find: function () {
                if (this.findInput.trim() != '')
                    PollService.find(this.findInput, function (data) {
                        $scope.me.result = data;
                    });
            }
        }
    }])
    .controller('CreateCtrl', ['$scope', 'PollService', function ($scope, PollService) {
        $scope.me = {
            newPoll: '',
            messageOnCreate: '',
            create: function () {
                var p = $scope.me;
                PollService.create(this.newPoll, function (data) {
                    q.messageOnCreate = 'Poll "' + q.newPoll + '" has been created.';
                });
            }
        };
    }])
;