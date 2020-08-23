package tacos.model

import org.hibernate.validator.constraints.CreditCardNumber
import java.io.Serializable
import java.util.Date
import javax.persistence.*
import javax.validation.constraints.Pattern
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank

//@Entity
//@Table(name = "Taco_Order")
data class Order(
        @field:NotBlank(message = "Name is required")
        var deliveryName: String = "",
        @field:NotBlank(message = "Street is required")
        var deliveryStreet: String = "",
        @field:NotBlank(message = "City is required")
        var deliveryCity: String = "",
        @field:NotBlank(message = "State is required")
        var deliveryState: String = "",
        @field:NotBlank(message = "Zip code is required")
        var deliveryZip: String = "",
        @field:CreditCardNumber(message = "Not a valid credit card number")
        var ccNumber: String = "",
        @field:Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
                message = "Must be formatted MM/YY")
        var ccExpiration: String = "",
        @field:Digits(integer = 3, fraction = 0, message = "Invalid CVV")
        var ccCVV: String = "") : Serializable {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0L

    lateinit var placedAt: Date

//    @ManyToMany(targetEntity = Taco::class)
    val tacos : MutableList<Taco> = mutableListOf()

    fun addDesign(taco: Taco) {
        tacos.add(taco)
    }

    @PrePersist
    fun placedAt() {
        placedAt = Date()
    }
}