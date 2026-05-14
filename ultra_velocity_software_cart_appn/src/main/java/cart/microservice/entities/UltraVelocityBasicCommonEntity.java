package cart.microservice.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UltraVelocityBasicCommonEntity {

    private String uniqueUUID;

    private Date createdDate;

    private Date lastModifiedDate;

    private Long id;

}
