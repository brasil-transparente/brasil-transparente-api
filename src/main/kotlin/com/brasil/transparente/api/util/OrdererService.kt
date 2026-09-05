package com.brasil.transparente.api.util

import com.brasil.transparente.api.entity.DespesaSimplificada
import org.springframework.stereotype.Service

@Service
class OrdererService {

    companion object {
        private const val OUTROS = "Outros"
    }

    fun orderDespesaSimplificadaListBySpending(despesaSimplificadaList: MutableList<DespesaSimplificada>) {
            despesaSimplificadaList.sortByDescending { it.totalValue }
            val outros = despesaSimplificadaList.firstOrNull { it.name == OUTROS}
            if (outros != null) {
                despesaSimplificadaList.remove(outros)
                despesaSimplificadaList.add(outros)
            }
    }

}