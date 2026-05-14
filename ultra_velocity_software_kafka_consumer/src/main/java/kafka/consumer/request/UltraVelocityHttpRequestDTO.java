package kafka.consumer.request;

import kafka.consumer.dtos.UltraVelocityGeneralUniversalDTO;
import io.netty.util.internal.StringUtil;

import java.util.Date;

public class UltraVelocityHttpRequestDTO extends UltraVelocityCommonBaseRequest {

    private String requestDataString;

    private Class<?> requestEntityClazz;

    private  UltraVelocityGeneralUniversalDTO requestDto =
            new UltraVelocityGeneralUniversalDTO();

    public UltraVelocityHttpRequestDTO() {
        super();
        requestDto = new UltraVelocityGeneralUniversalDTO();
        this.initializeRequest(requestDto);
    }

    public void initializeRequest(UltraVelocityGeneralUniversalDTO httpRequestDto) {
        requestDto.initializeDateDTO(new Date(), false, false, false);
        requestDto.initializeStringDTO(StringUtil.EMPTY_STRING, false, false, false);
        requestDto.initializeObjectDTO(StringUtil.EMPTY_STRING, false, false, false);
        requestDto.initializeLongDTO(0L, false, false, false);
        requestDto.initializeDoubleDTO(0.0d, false, false, false);
    }


}
