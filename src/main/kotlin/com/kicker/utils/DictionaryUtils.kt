package com.kicker.utils

import com.kicker.model.dictionary.Dictionary

/**
 * @author Yauheni Efimenko
 */
object DictionaryUtils {

    fun <T : Enum<*>> valueOf(clazz: Class<out T>, id: Int): T {
        val values = clazz.enumConstants
        return values.firstOrNull { (it as Dictionary).getId() == id }
                ?: throw IllegalStateException("Type ID not found")
    }

}