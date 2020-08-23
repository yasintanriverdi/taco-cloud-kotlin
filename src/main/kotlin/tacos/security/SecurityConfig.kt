package tacos.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.StandardPasswordEncoder
import javax.sql.DataSource


@Configuration
@EnableWebSecurity
class SecurityConfig @Autowired constructor(
        private val userDetailsService: UserRepositoryUserDetailsService,
        private val dataSource: DataSource) : WebSecurityConfigurerAdapter() {

    @Bean
    fun encoder(): PasswordEncoder? {
        return StandardPasswordEncoder("53cr3t")
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/design", "/orders")
                .hasRole("USER")
                .antMatchers("/", "/**").permitAll()

//                .antMatchers("/design", "/orders")
//                .access("hasRole('ROLE_USER')")
//                .antMatchers("/", "/**").access("permitAll")

                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/design", true)
//                .loginProcessingUrl("/authenticate")
//                .usernameParameter("user")
//                .passwordParameter("pwd")

                .and()
                .logout()
                .logoutSuccessUrl("/")
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder())

//        auth.ldapAuthentication()
//
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}")
//                .contextSource()
//                .root("dc=tacocloud, dc=com")
//                .ldif("classpath:users.ldif")
////                .url("ldap://tacocloud.com:389/dc=tacocloud, dc=com")
//                .and()
//                .passwordCompare()
//                .passwordEncoder(BCryptPasswordEncoder())
//                .passwordAttribute("passcode")

//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select username, password, enabled from Users " +
//                                "where username=?")
//                .authoritiesByUsernameQuery(
//                        "select username, authority from UserAuthorities " +
//                                "where username=?")
//                .passwordEncoder(StandardPasswordEncoder("53cr3t"))
    }

    // In memory user store example
//    override fun configure(auth: AuthenticationManagerBuilder) {
//        super.configure(auth)
//        auth.inMemoryAuthentication()
//                .withUser("buzz")
//                .password("infinity")
//                .authorities("ROLE_USER")
//                .and()
//                .withUser("woody")
//                .password("bullseye")
//                .authorities("ROLE_USER")
//    }
}