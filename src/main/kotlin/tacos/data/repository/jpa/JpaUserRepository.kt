package tacos.data.repository.jpa

import org.springframework.data.repository.CrudRepository
import tacos.model.User

interface JpaUserRepository : CrudRepository<User, Long> {

    fun findByUserName(userName: String): User?

}