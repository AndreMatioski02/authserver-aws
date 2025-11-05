package br.pucpr.authserver.integration.messaging

import br.pucpr.authserver.users.PhoneLoginUser
import br.pucpr.authserver.users.User
import com.amazonaws.services.sns.AmazonSNS
import com.amazonaws.services.sns.model.PublishRequest
import com.amazonaws.services.sns.model.SetSMSAttributesRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller

@Controller
class MessageClient(private val sns: AmazonSNS) {

    fun sendSms(user: User, text: String, important: Boolean = false) {
        if (user.phone.isBlank()) return
        try {
            if (important) {
                val attributes = SetSMSAttributesRequest().apply {
                    attributes = mapOf("DefaultSMSType" to "Transactional")
                }
                sns.setSMSAttributes(attributes)
            }

            sns.publish(
                PublishRequest().apply {
                    phoneNumber= user.phone
                    message=text
                }
            )
            log.info("Sms sent to ${user.phone}: $text")
        } catch (error: Exception) {
            log.error("Error sending sms to ${user.phone}: $text", error)
        }
    }

    fun sendSmsToLogin(user: PhoneLoginUser, text: String, important: Boolean = false) {
        if (user.phone.isBlank()) return
        try {
            if (important) {
                val attributes = SetSMSAttributesRequest().apply {
                    attributes = mapOf("DefaultSMSType" to "Transactional")
                }
                sns.setSMSAttributes(attributes)
            }

            sns.publish(
                PublishRequest().apply {
                    phoneNumber= user.phone
                    message=text
                }
            )

            log.info("Sms sent to ${user.phone}: $text")
        } catch (error: Exception) {
            log.error("Error sending sms to ${user.phone}: $text", error)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(MessageClient::class.java)
    }
}
