package com.brasil.transparente.api.util;

import com.brasil.transparente.api.entity.*;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@NoArgsConstructor
public class OrdererService {

    public void orderDespesaSimplificadaListBySpending(List<DespesaSimplicada> despesaSimplicadaList) {
        despesaSimplicadaList.sort((i1, i2) -> Double.compare(i2.getDespesaSimplificadaTotalValue(), i1.getDespesaSimplificadaTotalValue()));
        for (int i = 0; i < despesaSimplicadaList.size(); i++) {
            if (Objects.equals(despesaSimplicadaList.get(i).getDespesaSimplificadaName(), "Outros")) {
                DespesaSimplicada outros = despesaSimplicadaList.remove(i);
                despesaSimplicadaList.add(outros);
                break;
            }
        }
    }


}
