package tacos.data.repository

import tacos.model.Order

interface OrderRepository {

    fun save(order: Order): Order

}