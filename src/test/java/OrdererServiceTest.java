import com.brasil.transparente.api.entity.DespesaSimplificada;
import com.brasil.transparente.api.util.OrdererService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrdererServiceTest {

    private final OrdererService ordererService = new OrdererService();

    @Test
    void shouldSortListAndMoveOutrosToEnd() {
        List<DespesaSimplificada> list = new ArrayList<>();
        DespesaSimplificada despesaSimplificada1 = DespesaSimplificada.builder()
                .name("Outros")
                .totalValue(5000)
                .build();
        DespesaSimplificada despesaSimplificada2 = DespesaSimplificada.builder()
                .name("Saúde")
                .totalValue(2000)
                .build();
        DespesaSimplificada despesaSimplificada3 = DespesaSimplificada.builder()
                .name("Educação")
                .totalValue(4000)
                .build();
        list.add(despesaSimplificada1);
        list.add(despesaSimplificada2);
        list.add(despesaSimplificada3);

        ordererService.orderDespesaSimplificadaListBySpending(list);

        assertEquals("Educação", list.get(0).getName());
        assertEquals("Saúde", list.get(1).getName());
        assertEquals("Outros", list.get(2).getName());
    }

}
