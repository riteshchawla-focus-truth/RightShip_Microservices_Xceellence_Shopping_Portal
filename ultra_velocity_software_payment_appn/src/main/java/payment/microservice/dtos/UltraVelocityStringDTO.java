package payment.microservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UltraVelocityStringDTO extends UltraVelocityBasicCommonDTO {

    private String stringData;

    private Set<String> stringDataSet;

    private List<String> stringDataList;

    private Map<String, String> stringDataMap;
}
