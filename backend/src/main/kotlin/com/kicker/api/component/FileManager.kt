package com.kicker.api.component

import com.kicker.api.config.property.FrontProperties
import com.kicker.api.config.property.IconsSizeProperties
import net.coobird.thumbnailator.Thumbnails
import org.apache.commons.io.FilenameUtils
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import javax.imageio.ImageIO


@Component
class FileManager(
        private val iconsSizeProperties: IconsSizeProperties,
        private val frontProperties: FrontProperties
) {

    companion object {
        private const val ICONS_PATH = "data/images/icons/"
    }


    fun upload(icon: MultipartFile): String = try {
        val fileName = convertMultiPartToFile(icon).name
        "${frontProperties.url}$fileName"
    } catch (e: Exception) {
        throw IllegalStateException(e.message, e)
    }

    fun delete(fileUrl: String) {
        try {
            val path = Paths.get(ICONS_PATH + fileUrl)
            if (Files.exists(path)) {
                Files.delete(path)
            }
        } catch (e: Exception) {
            throw IllegalStateException(e.message, e)
        }
    }

    private fun convertMultiPartToFile(icon: MultipartFile): File {
        val fileName = generateFileName(icon.originalFilename!!)
        val file = File(ICONS_PATH + fileName)

        squeeze(icon, file)

        return file
    }

    private fun generateFileName(originalFilename: String): String {
        val extension = FilenameUtils.getExtension(originalFilename)
        return "${UUID.randomUUID()}${FilenameUtils.EXTENSION_SEPARATOR}$extension"
    }

    private fun squeeze(icon: MultipartFile, file: File) {
        val bis = ByteArrayInputStream(icon.bytes)
        val bufferedImage = ImageIO.read(bis)

        Thumbnails.of(bufferedImage)
                .size(iconsSizeProperties.width!!, iconsSizeProperties.height!!)
                .toFile(file)
    }

}