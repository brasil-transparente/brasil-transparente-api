package com.brasil.transparente.api.util;

import com.brasil.transparente.api.entity.*;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@NoArgsConstructor
public class OrdererService {

    public void orderDespesaSimplificadaListBySpending(List<DespesaSimplificada> despesaSimplicadaList) {
        despesaSimplicadaList.sort((i1, i2) -> Double.compare(i2.getTotalValue(), i1.getTotalValue()));
        for (int i = 0; i < despesaSimplicadaList.size(); i++) {
            if (Objects.equals(despesaSimplicadaList.get(i).getName(), "Outros")) {
                DespesaSimplificada outros = despesaSimplicadaList.remove(i);
                despesaSimplicadaList.add(outros);
                break;
            }
        }
    }


}
