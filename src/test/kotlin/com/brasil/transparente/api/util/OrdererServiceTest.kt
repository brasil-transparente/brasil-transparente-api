package com.brasil.transparente.api.util

import com.brasil.transparente.api.entity.DespesaSimplificada
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class OrdererServiceTest {

    private val ordererService = OrdererService()

    private fun despesa(name: String, totalValue: Double) = DespesaSimplificada().apply {
        this.name = name
        this.totalValue = totalValue
    }

    @Test
    fun shouldSortListAndMoveOutrosToEnd() {
        val list = mutableListOf<DespesaSimplificada>()
        val despesaSimplificada1 = despesa("Outros", 5000.0)
        val despesaSimplificada2 = despesa("Saúde", 2000.0)
        val despesaSimplificada3 = despesa("Educação", 4000.0)
        list.add(despesaSimplificada1)
        list.add(despesaSimplificada2)
        list.add(despesaSimplificada3)

        ordererService.orderDespesaSimplificadaListBySpending(list)

        assertEquals("Educação", list[0].name)
        assertEquals("Saúde", list[1].name)
        assertEquals("Outros", list[2].name)
    }

    @Test
    fun shouldSortListWhenOutrosIsAbsent() {
        val list = mutableListOf(
            despesa("Educação", 10.0),
            despesa("Saúde", 20.0)
        )

        ordererService.orderDespesaSimplificadaListBySpending(list)

        assertEquals(listOf("Saúde", "Educação"), list.map { it.name })
    }
}