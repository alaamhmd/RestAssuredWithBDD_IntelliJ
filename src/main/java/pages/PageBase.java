package pages;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PageBase {


    protected String path;


    public void setBaseURL(String url) {

        RestAssured.baseURI = url;

    }
    public String setPath(String path) {

        return this.path = path;

    }

    public Response getPageResponse(String path ,String agent , String systemInfo) {

        return  given().contentType(ContentType.XML).header(agent, systemInfo)
                .get(path)
                .then().extract().response();

    }
    public List<String> getSpecificAttributeValue(String attributeName, ResponseBodyExtractionOptions response) {

        return  response.xmlPath().getList(attributeName);



    }

    }



