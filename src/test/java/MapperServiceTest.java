import com.brasil.transparente.api.dto.DisplayableElementDTO;
import com.brasil.transparente.api.util.MapperService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MapperServiceTest {

    @Test
    void testMappingToDisplayableElementoDto() {
        Long id = 42L;
        String name = "Ministério da Defesa";
        double totalValueSpent = 500000;
        double percentageOfTotal = 12.34;
        int levelOfElement = 3;

        MapperService mapper = new MapperService();
        DisplayableElementDTO dto = mapper.mapToDisplayableElementDto(id, name, totalValueSpent, percentageOfTotal, levelOfElement);

        assertNotNull(dto);
        assertEquals(id, dto.getId());
        assertEquals(name, dto.getName());
        assertEquals(totalValueSpent, dto.getTotalValueSpent());
        assertEquals(percentageOfTotal, dto.getPercentageOfTotal());
        assertEquals(levelOfElement, dto.getLevel());
    }

}
