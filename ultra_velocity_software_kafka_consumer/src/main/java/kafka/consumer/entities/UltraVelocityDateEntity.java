package kafka.consumer.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UltraVelocityDateEntity extends UltraVelocityBasicCommonEntity {

    private Date dateData;

    private Set<Date> dateDataSet;

    private List<Date> dateDataList;

    private Map<String, Date> dateDataMap;

}
