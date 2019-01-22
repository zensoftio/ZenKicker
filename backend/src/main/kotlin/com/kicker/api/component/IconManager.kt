package com.kicker.api.component

import com.kicker.api.config.property.IconsSizeProperties
import com.kicker.api.config.property.StaticDataProperties
import net.coobird.thumbnailator.Thumbnails
import org.apache.commons.io.FilenameUtils
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.annotation.PostConstruct
import javax.imageio.ImageIO

@Component
class IconManager(
        private val iconsSizeProperties: IconsSizeProperties,
        private val staticDataProperties: StaticDataProperties
) {

    private val iconsDirectory = Paths.get("data/images/icons")

    @PostConstruct
    private fun init() {
        if (!Files.exists(iconsDirectory)) {
            Files.createDirectories(iconsDirectory)
        }
    }

    fun upload(icon: MultipartFile): String {
        try {
            val fileName = generateFileName(icon.originalFilename!!)
            val iconPath = Paths.get("$iconsDirectory/$fileName")

            squeeze(iconPath.toString(), icon.bytes)

            return "${staticDataProperties.iconsUrl}$fileName"
        } catch (e: Exception) {
            throw IllegalStateException(e.message, e)
        }
    }

    fun delete(fileUrl: String) {
        try {
            val iconName = FilenameUtils.getName(fileUrl)
            val iconPath = Paths.get("$iconsDirectory/$iconName")
            if (Files.exists(iconPath)) {
                Files.delete(iconPath)
            }
        } catch (e: IOException) {
            throw IllegalStateException(e.message, e)
        }
    }

    private fun generateFileName(originalFilename: String): String {
        val extension = FilenameUtils.getExtension(originalFilename)
        return "${UUID.randomUUID()}${FilenameUtils.EXTENSION_SEPARATOR}$extension"
    }

    private fun squeeze(iconName: String, bytes: ByteArray) {
        val bis = ByteArrayInputStream(bytes)
        val bufferedImage = ImageIO.read(bis)

        Thumbnails.of(bufferedImage)
                .size(iconsSizeProperties.width!!, iconsSizeProperties.height!!)
                .toFile(iconName)
    }

}