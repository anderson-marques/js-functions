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
    companion object {
        const val INNER_LOGIC =
            "  var functionResponse = {}; " +

            "  var callback = function(response){ " +
            "    functionResponse = response "+
            "  }; "+

            " var perform = function(event, context){ "+
            "    compositeFunction(event, context, callback); "+
            "    return functionResponse; " +
            " }; "
    }

    fun load(functionName: String):ScriptFunctionGateway{
        val renaming = "compositeFunction = $functionName;"
        val functionCode = File(ClassLoader.getSystemResource( "$functionName.js").file).readText()
        this.scriptEngine.eval(this.lowLevelFunctions + INNER_LOGIC + functionCode + renaming)
        return this
    }

    @Throws(ScriptException::class, NoSuchMethodException::class)
    fun invoke(functionName: String, event: Map<String,Any>, context: Map<String,Any>): JSONObject {
        return JSONObject((this.scriptEngine as Invocable).invokeFunction("perform", event, context) as ScriptObjectMirror)
    }
}
