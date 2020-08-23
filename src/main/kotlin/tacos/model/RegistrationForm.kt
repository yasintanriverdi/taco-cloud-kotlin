package tacos.model

import org.springframework.security.crypto.password.PasswordEncoder

data class RegistrationForm(var username: String,
                            var password: String,
                            var fullname: String,
                            var street: String,
                            var city: String,
                            var state: String,
                            var zip: String,
                            var phone: String
) {

    fun toUser(passwordEncoder: PasswordEncoder) =
            User(username, passwordEncoder.encode(password),
                    fullname, street, city, state, zip, phone)
}