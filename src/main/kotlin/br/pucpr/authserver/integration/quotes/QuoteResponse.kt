package br.pucpr.authserver.integration.quotes

import com.fasterxml.jackson.annotation.JsonProperty

data class QuoteResponse(
    @JsonProperty("value")
    val quote: String,
)
