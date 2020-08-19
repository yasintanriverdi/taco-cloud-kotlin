package tacos.model

import tacos.model.enums.IngredientType

data class Ingredient(val id: String,
                      val name: String,
                      val type: IngredientType)
