package tacos.model

import tacos.model.enums.IngredientType
import javax.persistence.Entity
import javax.persistence.Id

//@Entity
data class Ingredient(var id: String = "",
                      var name: String = "",
                      var type: IngredientType = IngredientType.CHEESE /*TODO - update this usage*/)
