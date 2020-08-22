package tacos.data.converter


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import tacos.data.repository.IngredientRepository
import tacos.model.Ingredient

@Component
class IngredientByIdConverter @Autowired constructor(private val ingredientRepo: IngredientRepository) : Converter<String?, Ingredient?> {

    override fun convert(source: String): Ingredient? {
        return ingredientRepo.findOne(source)
    }

}