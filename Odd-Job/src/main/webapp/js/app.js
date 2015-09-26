'use strict';

/**
 * @ngdoc object
 * @name oddjobApp
 * @requires $routeProvider
 * @requires jobControllers
 * @requires ui.bootstrap
 *
 * @description
 * Root app, which routes and specifies the partial html and controller depending on the url requested.
 *
 */
var app = angular.module('oddjobApp',
    ['odd-jobControllers', 'ngRoute', 'ui.bootstrap']).
    config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider.
	            when('/profile/:websafeProfileKey', {
	                templateUrl: '/partials/profile_detail.html',
	                controller: 'ProfileDetailCtrl'
	            }).                
	            when('/profile', {
                    templateUrl: '/partials/profile.html',
                    controller: 'ProfileCtrl'
                }).
                when('/job/post', {
                    templateUrl: '/partials/post_job.html',
                    controller: 'JobCtrl'
                }).
                when('/job/:websafeJobKey', {
                    templateUrl: '/partials/job_detail.html',
                    controller: 'JobDetailCtrl'
                }).
                when('/job', {
                    templateUrl: '/partials/show_jobs.html',
                    controller: 'ShowJobCtrl'
                }).
                when('/', {
                    templateUrl: '/partials/home.html'
                }).
                otherwise({
                    redirectTo: '/'
                });
        }]);

/**
 * @ngdoc filter
 * @name startFrom
 *
 * @description
 * A filter that extracts an array from the specific index.
 *
 */
app.filter('startFrom', function () {
    /**
     * Extracts an array from the specific index.
     *
     * @param {Array} data
     * @param {Integer} start
     * @returns {Array|*}
     */
    var filter = function (data, start) {
        return data.slice(start);
    }
    return filter;
});


/**
 * @ngdoc constant
 * @name HTTP_ERRORS
 *
 * @description
 * Holds the constants that represent HTTP error codes.
 *
 */
app.constant('HTTP_ERRORS', {
    'UNAUTHORIZED': 401
});

app.constant('formInformation',{
	cities : [ 'Seattle', 'Redmond', 'Bellevue', 'Clyde HIll',
	      			'Issaquah', 'Medina', 'Mercer Island', 'Newcastle', 'Sammamish',
	      			'Woodinville', 'Bothell', 'Edmonds', 'Kenmore', 'Lake Forest Park',
	      			'Shoreline', 'Kirkland', 'Renton' ],
	states: [ 'Washington' ]

});

/**
 * 
 */
app.factory('jobProvider', function(){
	
});



/**
 * @ngdoc service
 * @name oauth2Provider
 *
 * @description
 * Service that holds the OAuth2 information shared across all the pages.
 *
 */
app.factory('oauth2Provider', function ($modal) {
    var oauth2Provider = {
        CLIENT_ID: '897649555819-5inb0bcbu709l5716g0ojtv3qpmhclnt.apps.googleusercontent.com',
        SCOPES: 'https://www.googleapis.com/auth/userinfo.email profile',
        signedIn: false
    };

    /**
     * Calls the OAuth2 authentication method.
     */
    oauth2Provider.signIn = function (callback) {
        gapi.auth.signIn({
            'clientid': oauth2Provider.CLIENT_ID,
            'cookiepolicy': 'single_host_origin',
            'accesstype': 'online',
            'approveprompt': 'auto',
            'scope': oauth2Provider.SCOPES,
            'callback': callback
        });
    };

    /**
     * Logs out the user.
     */
    oauth2Provider.signOut = function () {
        gapi.auth.signOut();
        // Explicitly set the invalid access token in order to make the API calls fail.
        gapi.auth.setToken({access_token: ''})
        oauth2Provider.signedIn = false;
    };

    /**
     * Shows the modal with Google+ sign in button.
     *
     * @returns {*|Window}
     */
    oauth2Provider.showLoginModal = function() {
        var modalInstance = $modal.open({
            templateUrl: '/partials/login.modal.html',
            controller: 'OAuth2LoginModalCtrl'
        });
        return modalInstance;
    };

    return oauth2Provider;
});