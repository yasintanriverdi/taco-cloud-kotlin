package tacos.model

import java.util.Date
import javax.persistence.*
import javax.validation.constraints.Size

//@Entity
data class Taco(var id: Long = 0L,
                @field:Size(min = 5, message = "Name must be at least 5 character long")
                var name: String = ""
) {

//    @ManyToMany(targetEntity = Ingredient::class)
    @field:Size(min = 1, message = "You must choose at least 1 ingredient")
    var ingredients: List<Ingredient> = emptyList()

    lateinit var createdAt: Date

//    @PrePersist
//    fun createdAt() {
//        createdAt = Date()
//    }
}