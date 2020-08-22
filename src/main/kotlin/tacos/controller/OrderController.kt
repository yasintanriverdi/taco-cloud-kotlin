package tacos.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import tacos.data.repository.OrderRepository
import tacos.model.Order
import javax.validation.Valid

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
class OrderController @Autowired constructor(private val orderRepository: OrderRepository) {

    @GetMapping("/current")
    fun orderForm(model: Model) = "orderForm"

    @PostMapping
    fun processOrder(@Valid  design: Order,
                     errors: Errors,
                     sessionStatus: SessionStatus): String {
        if (errors.hasErrors()) {
            return "orderForm"
        }

        orderRepository.save(design)
        sessionStatus.setComplete()
        return "redirect:/"
    }

}