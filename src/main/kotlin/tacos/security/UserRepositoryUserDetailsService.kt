package tacos.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import tacos.data.repository.jpa.JpaUserRepository

@Service
class UserRepositoryUserDetailsService @Autowired constructor(
        private val userRepository: JpaUserRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUserName(username)
        if (user != null) {
            return user
        } else {
            throw UsernameNotFoundException("User '$username' not found")
        }
    }
}