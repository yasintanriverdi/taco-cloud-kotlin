package tacos.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class User(private var userName: String = "",
                private var userPassword: String = "",
                private var fullname: String = "",
                private var street: String = "",
                private var city: String = "",
                private var state: String = "",
                private var zip: String = "",
                private var phoneNumber: String = "") : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0L

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf<GrantedAuthority>(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun isEnabled() = true

    override fun getUsername(): String = userName

    override fun isCredentialsNonExpired() = true

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun getPassword(): String = userPassword

    companion object {
        const val serialVersionUID: Long = 1L
    }

}