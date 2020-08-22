package tacos.data.repository.jdbc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import tacos.data.repository.IngredientRepository
import tacos.model.Ingredient
import tacos.model.enums.IngredientType

@Repository
class JdbcIngredientRepository @Autowired constructor(val jdbc: JdbcTemplate) : IngredientRepository {
    override fun findAll(): Iterable<Ingredient> {
        return jdbc.query("select id, name, type from Ingredient", ingredientMapper)
    }

    override fun findOne(id: String): Ingredient? {
        return jdbc.queryForObject("select id, name, type from Ingredient where id=?", ingredientMapper, id)
    }

    override fun save(ingredient: Ingredient): Ingredient {
        jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)",
                ingredient.id, ingredient.name, ingredient.type.toString())
        return ingredient
    }

    private val ingredientMapper by lazy {
        RowMapper { resultSet, rowNum ->
            Ingredient(id = resultSet.getString("id"),
                    name = resultSet.getString("name"),
                    type = IngredientType.valueOf(resultSet.getString("type")))
        }
    }

}