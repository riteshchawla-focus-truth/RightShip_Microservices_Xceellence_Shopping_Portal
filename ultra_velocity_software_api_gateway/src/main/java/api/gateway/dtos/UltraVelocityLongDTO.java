package api.gateway.dtos;

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
public class UltraVelocityLongDTO extends UltraVelocityBasicCommonDTO {

    private Long longData;

    private Set<Long> longDataSet;

    private List<Long> longDataList;

    private Map<String, Long> longDataMap;
}
