package payment.microservice.response;

import java.net.http.HttpResponse;

public class UltraVelocityGeneralCommonResponse {

    private String responseBodyString;

    private String responseDataString;

    private Class<?> responseEntityClazz;

    private HttpResponse<UltraVelocityGeneralResponseDTO> httpResponse;

    private int responseCode;

    private String responseMessage;

}
