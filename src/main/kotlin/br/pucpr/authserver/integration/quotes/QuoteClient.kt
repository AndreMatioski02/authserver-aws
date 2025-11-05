package br.pucpr.authserver.integration.quotes

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate

@Component
class QuoteClient {
    fun randomQuote(): QuoteResponse? = try {
        RestTemplate()
            .getForObject("https://api.chucknorris.io/jokes/random",
                QuoteResponse::class.java)
    } catch (error: RestClientException) {
        log.error("Problems accessing the quote server", error)
        null
    }

    companion object {
        private val log = LoggerFactory.getLogger(QuoteClient::class.java)
    }
}
