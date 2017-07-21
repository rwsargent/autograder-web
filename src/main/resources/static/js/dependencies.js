var dependencies = {
    baseUrl : "/js",
    paths : {
        jquery : "libs/jquery.min",
        materialize : "libs/materialize.min",
    },
    shim : {
        materialize : {
            "deps" : ['jquery']
        }
    }
}