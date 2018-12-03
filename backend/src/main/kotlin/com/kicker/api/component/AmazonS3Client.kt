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
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.awt.image.BufferedImage
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileOutputStream
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
        val fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1)
        s3client.deleteObject(DeleteObjectRequest(awsProperties.bucketName, fileName))
    }

    private fun convertMultiPartToFile(icon: MultipartFile): File {
        val filename = icon.originalFilename!!
        val extension = filename.substring(filename.lastIndexOf(".") + 1)
        val iconName = "${UUID.randomUUID()}.$extension"

        val bis = ByteArrayInputStream(icon.bytes)
        var bufferedImage = ImageIO.read(bis)

        bufferedImage = squeeze(bufferedImage)

        val file = File(iconName)
        val fos = FileOutputStream(file)
        ImageIO.write(bufferedImage, extension, fos)
        fos.close()

        return file
    }

    private fun squeeze(bufferedImage: BufferedImage): BufferedImage = Thumbnails.of(bufferedImage)
            .size(iconsSizeProperties.width!!, iconsSizeProperties.height!!).asBufferedImage()

}