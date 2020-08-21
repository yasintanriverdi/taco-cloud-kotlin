package tacos.model

import javax.validation.constraints.Size

data class Taco(@field:Size(min = 5, message = "Name must be at least 5 character long")
                val name: String = "",
                @field:Size(min = 1, message = "You must choose at least 1 ingredient")
                val ingredients: List<String> = emptyList())