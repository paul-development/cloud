package de.paul

import com.google.gson.*
import java.lang.reflect.Type

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 *
 * author Paul.
 */
class JsonLibSerializer : JsonSerializer<JsonLib>, JsonDeserializer<JsonLib> {

    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): JsonLib {
        return JsonLib.fromJsonElement(json)
    }

    override fun serialize(src: JsonLib, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return src.jsonElement
    }


}
