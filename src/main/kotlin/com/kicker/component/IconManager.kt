package com.kicker.component

import com.kicker.config.property.StaticFoldersProperties
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import javax.annotation.PostConstruct

/**
 * @author Yauheni Efimenko
 */

@Component
class IconManager(
        foldersProperties: StaticFoldersProperties
) {

    companion object {
        private val log = LoggerFactory.getLogger(IconManager::class.java)
    }

    private val iconsDirectory = Paths.get("${foldersProperties.images!!}/icons")


    @PostConstruct
    private fun init() {
        if (!Files.exists(iconsDirectory)) {
            Files.createDirectories(iconsDirectory)
        }
    }

    fun save(iconName: String, bytes: ByteArray) {
        try {
            val iconPath = Paths.get("$iconsDirectory/$iconName")
            Files.write(iconPath, bytes)
        } catch (e: IOException) {
            throw IllegalStateException(e.message, e)
        }
    }

    fun remove(iconName: String) {
        try {
            val iconPath = Paths.get("$iconsDirectory/$iconName")
            Files.delete(iconPath)
        } catch (e: IOException) {
            throw IllegalStateException(e.message, e)
        }
    }

}