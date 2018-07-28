import java.io.File

fun main(args: Array<String>) {
    val lowLevelFunctions =  "var LowLevelFunction1 = Java.type('LowLevelFunction1');" +
            "var LowLevelFunction2 = Java.type('LowLevelFunction2');" +
            "var LowLevelFunction3 = Java.type('LowLevelFunction3');"



    val event = mutableMapOf<String, Any>()
    val context = mutableMapOf<String, Any>()

    event.put("name", "anderson")
    context.put("timeLimit", 2000)

    val result = ScriptFunctionGateway(lowLevelFunctions).invoke("awesomeFunction", event, context)

    println(result.toString())

    val result2 = ScriptFunctionGateway(lowLevelFunctions).invoke("anotherAwesomeFunction", event, context)

    println(result2.toString())
}
