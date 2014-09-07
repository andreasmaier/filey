(function() {
    var HomeController = function() {
        this.time = moment().format("MMM Do YY, HH-mm-ss");
    };

    angular.module('fileyApp').controller('HomeController', HomeController);
})();
