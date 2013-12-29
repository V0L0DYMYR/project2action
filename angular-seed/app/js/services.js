'use strict';

angular.module('myApp.services', [])
    .factory('IdeaService', ['$http', function($http){
        var service = {
            onError:function (data, status, headers, config) {
                if(status == 500 && headers('Error') == 1000 && headers('Message') == 'Unauthorised')
                    window.location = '/project2action/test-signin.html';
                    //window.location = '/InQueue/signin.html';
            },
            load:function(callback){
                $http.get('/api/idea').success(callback).error(service.onError);
            },
            create: function(idea, callback){
                $http.post('/api/idea', idea).success(callback);
            },
            getPoll: function(pollId, callback){
                $http.get('/api/poll/'+pollId).success(callback);
            },

            removeQueue:function(queue){
                $http.delete('/api/queue/'+ queue.id);
            },
            getPeople:function(queueId, callback){
                $http.get('/api/person/'+queueId).success(callback).error(service.onError);
            },
            insertPerson:function(person, callback){
                $http.post('/api/person', person).success(callback);
            },
            removePerson:function(person){
                $http.delete('/api/person/'+ person.id);
            },
            find:function(input, callback){
                $http.get('/api/queue/find/'+input).success(callback).error(service.onError);
            },
            jumpIn: function(queue){
                $http.post('/api/queue/'+queue.id+'/jumpIn');
            },
            loadMemberQueue: function(callback){
                $http.get('/api/queue/memberIn').success(callback);
            }
        }
        return service;
    }])
    .value('version', '0.1');
