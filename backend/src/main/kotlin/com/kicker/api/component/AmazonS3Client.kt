package com.kicker.api.component

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.PutObjectRequest
import com.kicker.api.config.property.AwsProperties
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
import javax.annotation.PostConstruct
import javax.imageio.ImageIO


@Component
class AmazonS3Client(
        private val awsProperties: AwsProperties,
        private val iconsSizeProperties: IconsSizeProperties
) {

    private lateinit var s3client: AmazonS3


    @PostConstruct
    private fun initializeAmazon() {
        val credentials = BasicAWSCredentials(awsProperties.accessKey!!, awsProperties.secretKey!!)

        this.s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(AWSStaticCredentialsProvider(credentials))
                .withRegion(awsProperties.region!!)
                .build()
    }

    fun upload(icon: MultipartFile): String {
        val file: File
        try {
            file = convertMultiPartToFile(icon)
            s3client.putObject(PutObjectRequest(awsProperties.bucketName, file.name, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead))
            Files.delete(Paths.get(file.name))
        } catch (e: Exception) {
            throw IllegalStateException(e.message, e)
        }

        return "${awsProperties.endpointUrl}/${awsProperties.bucketName}/${file.name}"
    }

    fun delete(fileUrl: String) {
        s3client.deleteObject(DeleteObjectRequest(awsProperties.bucketName, FilenameUtils.getName(fileUrl)))
    }

    private fun convertMultiPartToFile(icon: MultipartFile): File {
        val file = File(generateFileName(icon.originalFilename!!))

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