package io.zensoft.kicker.utils

import io.zensoft.kicker.model.base.Dictionary

object DictionaryUtils {

    @Suppress("UNCHECKED_CAST")
    fun <T : Enum<*>> valueOf(clazz: Class<out T>, id: Int): T =
            clazz.enumConstants.firstOrNull { (it as Dictionary).getId() == id }
                    ?: throw IllegalStateException("Type ID not found")

}