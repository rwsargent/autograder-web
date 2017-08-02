require.config({
    baseUrl : dependencies.baseUrl,
    paths : dependencies.paths,
    shim : dependencies.shim
});

require([
    'labview',
], function(labview) {
    $(function() {
        labview(); // init labview
    });
});