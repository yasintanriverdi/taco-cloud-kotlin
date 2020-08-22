package tacos.data.repository.jdbc

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreatorFactory
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import tacos.data.repository.TacoRepository
import tacos.model.Ingredient
import tacos.model.Taco
import java.sql.Timestamp
import java.sql.Types
import java.util.Date

@Repository
class JdbcTacoRepository @Autowired constructor(val jdbc: JdbcTemplate) : TacoRepository {

    override fun save(taco: Taco): Taco {
        saveTacoInfo(taco)!!.let {tacoId ->
            taco.id = tacoId
            taco.ingredients.forEach { ingredient ->
                saveIngredientToTaco(ingredient, tacoId)
            }
        }
        return taco
    }

    private fun saveTacoInfo(taco: Taco): Long? {
        taco.createdAt = Date()
        val pscFactory = PreparedStatementCreatorFactory(
                "insert into Taco (name, createdAt) values (?, ?)", Types.VARCHAR, Types.TIMESTAMP
        )
        pscFactory.setReturnGeneratedKeys(true)
        val psc = pscFactory.newPreparedStatementCreator(
        listOf(taco.name, Timestamp(taco.createdAt.time)))

        val keyHolder = GeneratedKeyHolder()
        jdbc.update(psc, keyHolder)

        return keyHolder.key?.toLong()
    }

    private fun saveIngredientToTaco(ingredient: Ingredient, tacoId: Long) {
        jdbc.update("insert into Taco_Ingredients (taco, ingredient) values (?, ?)", tacoId, ingredient.id)
    }
}