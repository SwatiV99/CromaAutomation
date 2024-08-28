package stepDefinition;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static Constants.cromaConstants.*;
import static io.restassured.RestAssured.given;

public class RestClient {
    private static final String ContentType = "Content-Type";
    private static final String ContentTypeValue = "application/xml";
    private static final String HOST = "Host";
    private static final String HOST_VALUE = "localhost:7001";

    private RequestSpecification givenBaseRequest() {
        return given()
                .queryParam(ContentType, ContentTypeValue)
                .queryParam(HOST, HOST_VALUE)
                .queryParam(SERVICE_NAME, "")
                .queryParam(USERID, ADMIN)
                .queryParam(PASSWORD, PASSWORD_VALUE);
    }

    public Response postRequest(String interopApiName, String interopApiData) {
        return givenBaseRequest()
                .queryParam(INTEROPAPINAME, interopApiName)
                .queryParam(FLOW, FLOW_PARAMTER_VALUE)
                .queryParam(INTEROPAPIDATA, interopApiData)
                .when()
                .post(ResourceURLCroma)
                .then()
                .assertThat().statusCode(200)
                .extract().response();
    }
}

