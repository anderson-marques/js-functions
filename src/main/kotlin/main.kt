import java.io.File

fun main(args: Array<String>) {
    val lowLevelFunctions =  "var LowLevelFunction1 = Java.type('LowLevelFunction1');" +
            "var LowLevelFunction2 = Java.type('LowLevelFunction2');" +
            "var LowLevelFunction3 = Java.type('LowLevelFunction3');"

    val level2Function = File(ClassLoader.getSystemResource( "level2function.js").file).readText()

    val event = mutableMapOf<String, Any>()
    val context = mutableMapOf<String, Any>()

    event.put("name", "anderson")
    context.put("timeLimit", 2000)
    val result = ScriptFunctionGateway(lowLevelFunctions).invoke(level2Function, event, context)

    print(result.toString())
}
