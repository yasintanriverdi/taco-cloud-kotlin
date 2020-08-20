package tacos.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import tacos.model.Ingredient
import tacos.model.Taco
import tacos.model.enums.IngredientType
import java.util.logging.Logger


@Controller
@RequestMapping("/design")
class DesignTacoController {

    @GetMapping
    fun showDesignForm(model: Model): String {
        val ingredients = listOf(
                Ingredient("FLTO", "Flour Tortilla", IngredientType.WRAP),
                Ingredient("COTO", "Corn Tortilla", IngredientType.WRAP),
                Ingredient("GRBF", "Ground Beef", IngredientType.PROTEIN),
                Ingredient("CARN", "Carnitas", IngredientType.PROTEIN),
                Ingredient("TMTO", "Diced Tomatoes", IngredientType.VEGGIES),
                Ingredient("LETC", "Lettuce", IngredientType.VEGGIES),
                Ingredient("CHED", "Cheddar", IngredientType.CHEESE),
                Ingredient("JACK", "Monterrey Jack", IngredientType.CHEESE),
                Ingredient("SLSA", "Salsa", IngredientType.SAUCE),
                Ingredient("SRCR", "Sour Cream", IngredientType.SAUCE))

        IngredientType.values().forEach { type ->
            val ingredientsByType = ingredients.filter { ingredient ->
                ingredient.type == type
            }.toList()
            model.addAttribute(type.toString().toLowerCase(), ingredientsByType)
        }

        model.addAttribute("design", Taco())

        return "design"
    }

    @PostMapping
    fun processDesign(@ModelAttribute("design") design: Taco): String {
        // TODO - save the design
        return "redirect:/orders/current"
    }

}