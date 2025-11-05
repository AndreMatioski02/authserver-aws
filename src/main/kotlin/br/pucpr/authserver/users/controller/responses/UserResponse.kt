package br.pucpr.authserver.users.controller.responses

import br.pucpr.authserver.users.PhoneLoginUser
import br.pucpr.authserver.users.User
import java.time.LocalDateTime

data class UserResponse(
    val id: Long,
    val email: String,
    val name: String,
    val avatar: String,
    val quote: String,
) {
    constructor(user: User, avatarUrl: String) :
            this(
                id=user.id!!,
                email=user.email,
                name=user.name,
                quote=user.quote,
                avatar=avatarUrl
            )
}


data class PhoneLoginUserResponse(
    val id: Long,
    val phone: String,
    val uuid: String?,
    val active: Boolean,
    val confirmationCode: String?,
    val confirmationExpiresAt: LocalDateTime?
) {
    constructor(user: PhoneLoginUser) :
            this(
                id=user.id!!,
                phone=user.phone,
                uuid=user.uuid,
                active=user.active,
                confirmationCode=user.confirmationCode,
                confirmationExpiresAt=user.confirmationExpiresAt
            )
}