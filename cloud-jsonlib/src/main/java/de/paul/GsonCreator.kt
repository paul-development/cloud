package de.paul

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type
import kotlin.reflect.KClass

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 *
 * author Paul.
 */
private val gsonBuilder = GsonBuilder()
    .registerTypeAdapter(JsonLib::class.java, JsonLibSerializer())
    .setPrettyPrinting()
    .serializeNulls()

class GsonCreator {
    private val exclusionStrategies = mutableListOf<Class<out Annotation>>()

    fun excludeAnnotations(vararg annotationClasses: KClass<out Annotation>): GsonCreator {
        annotationClasses.forEach { exclusionStrategies.add(it.java) }
        return this
    }

    fun excludeAnnotations(vararg annotationClasses: Class<out Annotation>): GsonCreator {
        exclusionStrategies.addAll(annotationClasses)
        return this
    }

    fun registerTypeAdapter(type: Type, typeAdapter: Any) {
        gsonBuilder.registerTypeAdapter(type, typeAdapter)
    }

    fun create(): Gson {
        val exclusionStrategy = AnnotationExclusionStrategy(*exclusionStrategies.toTypedArray())
        gsonBuilder.setExclusionStrategies(exclusionStrategy)
        return gsonBuilder.create()
    }
}
