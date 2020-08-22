package tacos.data.repository

import tacos.model.Taco

interface TacoRepository {

    fun save(taco: Taco): Taco

}