package br.pucpr.authserver.users.controller.requests

import jakarta.validation.constraints.NotBlank

data class LoginRequest(
    @NotBlank
    val email: String?,

    @NotBlank
    val password: String?
)

data class PhoneLoginRequest(
    @field:NotBlank val phone: String?,
    @field:NotBlank val uuid: String?
)

data class PhoneConfirmRequest(
    @field:NotBlank val phone: String?,
    @field:NotBlank val uuid: String?,
    @field:NotBlank val code: String?,
)

