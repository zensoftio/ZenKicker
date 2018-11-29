package com.kicker.api.component

import com.kicker.api.config.property.IconsSizeProperties
import net.coobird.thumbnailator.Thumbnails
import org.springframework.stereotype.Component
import java.io.ByteArrayInputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import javax.annotation.PostConstruct
import javax.imageio.ImageIO


/**
 * @author Yauheni Efimenko
 */

@Component
class IconManager(
        private val iconsSizeProperties: IconsSizeProperties
) {

    private val iconsDirectory = Paths.get("images/icons")


    @PostConstruct
    private fun init() {
        if (!Files.exists(iconsDirectory)) {
            Files.createDirectories(iconsDirectory)
        }
    }

    fun save(iconName: String, bytes: ByteArray) {
        try {
            val iconPath = Paths.get("$iconsDirectory/$iconName")

            val bis = ByteArrayInputStream(bytes)
            val bufferedImage = ImageIO.read(bis)

            // Squeeze
            Thumbnails.of(bufferedImage)
                    .size(iconsSizeProperties.width!!, iconsSizeProperties.height!!)
                    .toFile("$iconPath")
        } catch (e: IOException) {
            throw IllegalStateException(e.message, e)
        }
    }

    fun remove(iconName: String) {
        try {
            val iconPath = Paths.get("$iconsDirectory/$iconName")
            if (Files.exists(iconPath)) {
                Files.delete(iconPath)
            }
        } catch (e: IOException) {
            throw IllegalStateException(e.message, e)
        }
    }

}