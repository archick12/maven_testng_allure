import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.sessionId;
import static org.testng.AssertJUnit.assertTrue;


@Test(groups = {"Issue"})

public class Test_Test {
    String sessionId = "";
    String IssueKey="";

    @BeforeTest
    public void login() {
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body = "{\n" +
                "    \"username\": \"katherinebilous\",\n" +
                "    \"password\": \"Polis484)\"\n" +
                "} ";
        sessionId = given().
                header("Content-Type", "application/json").
                body(body).
                when().
                post("/rest/auth/1/session").
                then().
                extract().
                path("session.value");
    }

    @Test
    public void createIssue() {RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body = "{\n" +
                "\n" +
                "\t\"fields\": {\n" +
                "\t\t\"project\": {\n" +
                "\t\t\t\"id\": \"10315\"\n" +
                "\t\t},\n" +
                "\t\t\"summary\": \"RESTAssured summary\",\n" +
                "\t\t\"issuetype\": {\n" +
                "\t\t\t\"id\": \"10004\"\n" +
                "\t\t},\n" +
                "\t\t\"assignee\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t},\n" +
                "\t\t\"reporter\": {\n" +
                "\t\t\t\"name\": \"katherinebilous\"\n" +
                "\t\t}\n" +
                "\t}\n" +
                "}\n";
        // создание Issue

        IssueKey = given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                post("/rest/api/2/issue").
                then().
                statusCode(201).
                extract().path("key");
        System.out.println(IssueKey);


    }

    @Test(dependsOnMethods = {"createIssue"})
    public void deleteIssue() {
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                delete("/rest/api/2/issue/"+IssueKey).then().statusCode(204);

    }

    @Test
    public void editSummary() {
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body = ("{  \n" +
                "   \"update\":{  \n" +
                "      \"summary\":[  \n" +
                "         {  \n" +
                "            \"set\":\"new edit summary\"\n" +
                "         }\n" +
                "      ]\n" +
                "   }\n" +
                "}");
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                put("/rest/api/2/issue/qaaut-146").then().statusCode(204);



    }
    @Test
    public void changeIssueType(){
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body ="{\n" +
                " \"fields\": \n" +
                " {\n" +
                "  \"issuetype\": {\"id\": \"10003\"}\n" +
                " }\n" +
                "}";
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                put("/rest/api/2/issue/qaaut-147").then().statusCode(204);


    }
    @Test
    public void searchFilter()
    {
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body =" {\n" +
                "    \"jql\": \"project = QAAUT\",\n" +
                "    \"startAt\": 0,\n" +
                "    \"maxResults\": 15,\n" +
                "    \"fields\": [\n" +
                "        \"summary\",\n" +
                "        \"status\",\n" +
                "        \"assignee\"\n" +
                "    ]\n" +
                "}";
       Response response= given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                post("/rest/api/2/search");

        System.out.println(response.asString());

    }
    @Test
    public void Assign ()
    {
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body =" {\n" +
                "    \"name\": \"a.a.piluck2\"\n" +
                "}";
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                put("/rest/api/2/issue/qaaut-161/assignee").then().statusCode(204);

    }
    @Test
    public void negativeTestLogin(){
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body = "{\n" +
                "    \"username\": \"katherinebilous\",\n" +
                "    \"password\": \"Polis485)\"\n" +
                "} ";
        given().
                header("Content-Type", "application/json").
                body(body).
                when().
                post("/rest/auth/1/session").
                then().statusCode(401);

    }
    @Test
    public void  addComment(){
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        String body ="{\n" +
                "    \"body\": \"This is a comment regarding the quality of the response.\"\n" +
                "}";
        Response response=
        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                body(body).
                when().
                post("rest/api/2/issue/QAAUT-320/comment");
        assertTrue(response.getStatusCode()==201);
        System.out.println(String.valueOf(response.path("id")));




    }
    @Test
    public  void  getComment() {
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";
        Response response =


                given().
                        header("Cookie", "JSESSIONID=" + sessionId).
                        header("Content-Type", "application/json").
                        when().
                        get("/rest/api/2/issue/QAAUT-320/comment/11037");
        assertTrue(response.getStatusCode() == 200);


        System.out.println(response.asString());
    }





    @Test
    public  void deleteComment(){
        RestAssured.baseURI = "http://soft.it-hillel.com.ua:8080";


        given().
                header("Cookie", "JSESSIONID=" + sessionId).
                header("Content-Type", "application/json").
                when().
                delete("/rest/api/2/issue/QAAUT-320/comment/11038").then().statusCode(204);

    }






}
