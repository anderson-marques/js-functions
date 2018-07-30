import com.google.gson.Gson
import jdk.nashorn.api.scripting.ScriptObjectMirror
import org.json.JSONObject
import java.io.File
import javax.script.Invocable
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class ScriptFunctionGateway(private val lowLevelFunctions: String) {
    val scriptEngine:ScriptEngine

    init {
        this.scriptEngine = ScriptEngineManager().getEngineByName("js")
    }

    fun load(functionName: String):ScriptFunctionGateway{
        val performLogic =

        " var perform_$functionName = function(event, context){ "+
        "     var functionResponse = {};             " +
        "     var callback = function(response){ " +
        "        functionResponse = response "+
        "     }; "+
        "     $functionName(event, context, callback); "+
        "     return functionResponse; " +
        " }; "
        val functionCode = File(ClassLoader.getSystemResource( "$functionName.js").file).readText()
        this.scriptEngine.eval(this.lowLevelFunctions + performLogic + functionCode)
        return this
    }

    @Throws(ScriptException::class, NoSuchMethodException::class)
    fun invoke(functionName: String, event: Map<String,Any>, context: Map<String,Any>): JSONObject {
        return JSONObject((this.scriptEngine as Invocable).invokeFunction("perform_$functionName", event, context) as ScriptObjectMirror)
    }
}
