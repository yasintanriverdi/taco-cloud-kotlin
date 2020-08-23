package tacos.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import tacos.data.repository.jpa.JpaUserRepository
import tacos.model.RegistrationForm

@Controller
@RequestMapping("/register")
class RegistrationController @Autowired constructor(
        private val userRepository: JpaUserRepository,
        private val passwordEncoder: PasswordEncoder
) {

    @GetMapping
    fun registerForm(): String = "registration"

    @PostMapping
    fun processRegistration(form: RegistrationForm): String {
        userRepository.save(form.toUser(passwordEncoder))
        return "redirect:/login"
    }

}