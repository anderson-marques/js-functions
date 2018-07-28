import com.google.gson.Gson
import jdk.nashorn.api.scripting.ScriptObjectMirror
import org.json.JSONObject
import java.io.File
import javax.script.Invocable
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class ScriptFunctionGateway(private val lowLevelFunctions: String) {

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

    @Throws(ScriptException::class, NoSuchMethodException::class)
    fun invoke(functionName: String, event: Map<String,Any>, context: Map<String,Any>): JSONObject {
        val renaming = "compositeFunction = $functionName;"
        val functionCode = File(ClassLoader.getSystemResource( "$functionName.js").file).readText()

        val se = ScriptEngineManager().getEngineByName("js")
        se.eval(this.lowLevelFunctions + INNER_LOGIC + functionCode + renaming)
        return JSONObject((se as Invocable).invokeFunction("perform", event, context) as ScriptObjectMirror)
    }
}
