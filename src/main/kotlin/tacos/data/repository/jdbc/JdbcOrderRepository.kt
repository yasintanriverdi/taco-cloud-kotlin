package tacos.data.repository.jdbc

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import tacos.data.repository.OrderRepository
import tacos.model.Order
import tacos.model.Taco
import java.util.Date

@Repository
class JdbcOrderRepository @Autowired constructor(private val jdbc: JdbcTemplate) : OrderRepository {

    private val orderInserter by lazy {
        SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id")
    }

    private val orderTacoInserter by lazy {
        SimpleJdbcInsert(jdbc)
                .withTableName("Taco_Order_Tacos")
    }

    private val objectMapper by lazy { ObjectMapper() }

    override fun save(order: Order): Order {
        order.placedAt = Date()
        val orderId = saveOrderDetails(order)
        order.id = orderId
        order.tacos.forEach { taco ->
            saveTacoToOrder(taco, orderId)
        }
        return order
    }

    private fun saveOrderDetails(order: Order): Long {
        val values: MutableMap<String, Any> = (objectMapper.convertValue(order, Map::class.java) as Map<out String, Any>).toMutableMap()
        values["placedAt"] = order.placedAt
        return orderInserter.executeAndReturnKey(values.toMap()).toLong()
    }

    private fun saveTacoToOrder(taco: Taco, orderId: Long) {
        val values = mutableMapOf<String, Any>()
        values["tacoOrder"] = orderId
        values["taco"] = taco.id
        orderTacoInserter.execute(values)
    }
}