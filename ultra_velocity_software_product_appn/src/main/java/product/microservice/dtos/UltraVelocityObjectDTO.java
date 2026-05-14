package product.microservice.dtos;

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
public class UltraVelocityObjectDTO extends UltraVelocityBasicCommonDTO {

    private Object objectData;

    private Set<Object> objectDataSet;

    private List<Object> objectDataList;

    private Map<String, Object> objectDataMap;

}
