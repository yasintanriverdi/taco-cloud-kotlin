package tacos.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import tacos.data.repository.IngredientRepository
import tacos.data.repository.TacoRepository
import tacos.model.Ingredient
import tacos.model.Order
import tacos.model.Taco
import tacos.model.enums.IngredientType
import javax.validation.Valid

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
class DesignTacoController @Autowired constructor(private val ingredientRepository: IngredientRepository,
                                                  private val tacoRepository: TacoRepository) {

    @ModelAttribute(name = "order")
    fun order() = Order()

    @ModelAttribute(name = "taco")
    fun taco() = Taco()

    @GetMapping
    fun showDesignForm(model: Model): String {
        val ingredients = mutableListOf<Ingredient>()
        ingredients.addAll(ingredientRepository.findAll())

        IngredientType.values().forEach { type ->
            val ingredientsByType = ingredients.filter { ingredient ->
                ingredient.type == type
            }.toList()
            model.addAttribute(type.toString().toLowerCase(), ingredientsByType)
        }

        return "design"
    }

    @PostMapping
    fun processDesign(@Valid design: Taco,
                      errors: Errors,
                      @ModelAttribute order: Order): String {
        if (errors.hasErrors()) {
            return "design"
        }

        val savedTaco = tacoRepository.save(design)
        order.addDesign(savedTaco)

        return "redirect:/orders/current"
    }

}