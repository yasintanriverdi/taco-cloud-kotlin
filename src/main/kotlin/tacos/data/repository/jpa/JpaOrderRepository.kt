package tacos.data.repository.jpa

import org.springframework.data.repository.CrudRepository
import tacos.model.Order

//interface JpaOrderRepository : CrudRepository<Order, Long> {

//    fun findByDeliveryZip(deliveryZip: String): List<Order>
//
//    // read, get ,find
//    fun readOrdersByDeliveryZipAndPlacedAtBetween(deliveryZip: String,
//                                                  startDate: Date, endDate: Date)
//
//    fun countOrdersByDeliveryZipAndPlacedAtBetween(deliveryZip: String,
//                                                   startDate: Date, endDate: Date): Int
//
//    fun findDeliveryToAndDeliveryCityAllIgnoresCase(deliveryTo: String,
//                                                    deliveryCity: String): List<Order>
//
//    fun findByDeliveryCityOrderByDeliveryTo(city: String): List<Order>

//    @Query("Order o where o.deliveryCity='Istanbul'")
//    fun readOrdersDeliveredInIstanbul() : List<Order>
//}