package br.pucpr.authserver.users


import br.pucpr.authserver.roles.Role
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "TblUser")
class User(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true)
    var email: String = "",
    @Column(unique = true)
    var phone: String = "",
    var password: String = "",
    var name: String = "",
    var avatar: String = AvatarService.DEFAULT_AVATAR,
    var quote: String = "",

    @ManyToMany
    @JoinTable(
        name = "UserRole",
        joinColumns = [JoinColumn(name = "idUser")],
        inverseJoinColumns = [JoinColumn(name = "idRole")]
    )
    val roles: MutableSet<Role> = mutableSetOf()
) {
    @get:JsonIgnore
    @get:Transient
    val isAdmin: Boolean get() = roles.any { it.name == "ADMIN" }
}

@Entity
@Table(name = "users")
data class PhoneLoginUser(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var phone: String = "",
    var uuid: String? = null,
    var active: Boolean = false,
    var confirmationCode: String? = null,
    var confirmationExpiresAt: LocalDateTime? = null,

    // outros campos já existentes
)

