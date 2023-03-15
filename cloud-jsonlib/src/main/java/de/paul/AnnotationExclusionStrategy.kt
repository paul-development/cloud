package de.paul

import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 *
 * author Paul.
 */
class AnnotationExclusionStrategy(vararg annotationClasses: Class<out Annotation>) : ExclusionStrategy {
    private val annotationsToExclude = annotationClasses.toList()

    override fun shouldSkipClass(clazz: Class<*>): Boolean {
        return false
    }

    override fun shouldSkipField(f: FieldAttributes): Boolean {
        return f.annotations.any { annotationsToExclude.contains(it.annotationClass.java) }
    }
}