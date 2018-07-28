var compositeFunction = function(event, context, callback){

    print(event)
    print(context)

    if (event.name === 'anderson'){
        new LowLevelFunction1().run();
    } else {
        new LowLevelFunction3().run();
    }

    new LowLevelFunction2().run();


    callback({
        status : 200,
        message: "Functions, functions, functions...",
        error: null
    })
}




