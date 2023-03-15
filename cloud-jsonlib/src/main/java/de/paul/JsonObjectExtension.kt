package de.paul

import com.google.gson.JsonElement
import com.google.gson.JsonNull
import com.google.gson.JsonObject

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 *
 * author Paul.
 */
fun JsonObject.getOrNull(property: String): JsonElement? {
    val jsonElement = get(property)
    return if (jsonElement is JsonNull) null else jsonElement
}