echo %cd%
cd saraswati_platform_right_digits_api_gateway
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_app_config_server
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_application_testing
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_authorization_server
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_backend_appn
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_cart_appn
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_client_management
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_common_general_codes
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_files_documents
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_functional_programs_appn
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_kafka_consumer
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_kafka_producer
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_order_appn
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_payment_appn
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_product_appn
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_product_appn
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_rest_client_appn
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_service_registry_appn
echo %cd%
start mvnw clean package spring-boot:run
cd ..\saraswati_platform_right_digits_user_appn
echo %cd%
start mvnw clean package spring-boot:run