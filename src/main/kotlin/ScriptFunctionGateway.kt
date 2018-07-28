import com.google.gson.Gson
import jdk.nashorn.api.scripting.ScriptObjectMirror
import org.json.JSONObject
import javax.script.Invocable
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

class ScriptFunctionGateway(private val lowLevelFunctions: String) {

    companion object {
        val INNER_LOGIC =
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
    fun invoke(scriptToRun: String, event: Map<String,Any>, context: Map<String,Any>): JSONObject {
        val se = ScriptEngineManager().getEngineByName("js")
        se.eval(this.lowLevelFunctions + INNER_LOGIC + scriptToRun)
        return JSONObject((se as Invocable).invokeFunction("perform", event, context) as ScriptObjectMirror)
    }
}
