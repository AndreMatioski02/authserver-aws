package br.pucpr.authserver.security

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.sns.AmazonSNSAsync
import com.amazonaws.services.sns.AmazonSNSAsyncClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsConfig(
    @Value("\${aws.accessKeyId}") private val accessKey: String,
    @Value("\${aws.secretAccessKey}") private val secretKey: String,
    @Value("\${aws.region}") private val region: String
) {
    @Bean
    fun snsClient(): AmazonSNSAsync =
        AmazonSNSAsyncClientBuilder.standard()
            .withRegion(region)
            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(accessKey, secretKey)))
            .build()
}