package tacos.model

// TODO clean-up empty strings
data class Order(val name: String = "",
                 val street: String = "",
                 val city: String = "",
                 val state: String = "",
                 val zip: String = "",
                 val ccNumber: String = "",
                 val ccExpiration: String = "",
                 val ccCVV: String = "")