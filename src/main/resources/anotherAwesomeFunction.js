var anotherAwesomeFunction = function(event, context, callback){

    var llf2 = new LowLevelFunction2();

    llf2.run();

    callback({
        status : 500,
        message: "Another awesome function",
        error: null
    })
}




