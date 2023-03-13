package retrofit2.converter.gson

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.gson.TypeAdapter
import okhttp3.MediaType
import okhttp3.RequestBody
import okio.Buffer
import retrofit2.Converter
import java.io.OutputStreamWriter
import java.io.Writer
import java.nio.charset.Charset

class ExtendedGsonRequestBodyConverter<T>(val gson: Gson, private val adapter: TypeAdapter<T>, val readOnlyProperties: Array<out String> = arrayOf()): Converter<T, RequestBody> {

    companion object {
        private val MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8")
        private val UTF_8 = Charset.forName("UTF-8")
    }

    override fun convert(value: T): RequestBody {
        val buffer = Buffer()
       	val writer: Writer = OutputStreamWriter(buffer.outputStream(), UTF_8)
		val jsonWriter = gson.newJsonWriter(writer)
		adapter.write(jsonWriter, value)
		jsonWriter.close()

     	val jsonString: String = buffer.readByteString().string(UTF_8)
     	val jsonTree = JsonParser().parse(jsonString)

		readOnlyProperties.forEach { p ->
			removeFromNode(jsonTree, p.split(",").toTypedArray())
		}

       	return RequestBody.create(MEDIA_TYPE, jsonTree.toString())
    }

    private fun removeFromNode(node: JsonElement, pathItems: Array<String>) {
        if (pathItems.isNotEmpty()) {
            var currentNode: JsonElement = node
            var nodeName = pathItems[0]
            var index = 0
            while (index < pathItems.size - 1) {
                currentNode = node.get(nodeName)!!
                index++
                nodeName = pathItems[index]
            }
            if (currentNode.isJsonObject) {
                val objectNode: JsonObject = currentNode as JsonObject
                objectNode.remove(nodeName)
            }
        }
    }
}

private fun JsonElement.get(nodeName: String?): JsonElement? {
    if (isJsonObject) {
        return asJsonObject.get(nodeName)
    }
    return null
}
