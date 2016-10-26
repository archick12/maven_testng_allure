package fixture;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class JiraJSONFixture {


    public String generateJSONForLogin() {
        JSONObject credentials = new JSONObject();
        credentials.put("username", "katherinebilous");
        credentials.put("password", "Polis484)");

        return credentials.toJSONString();
    }
    public String generateJSONForSampleIssue() {
        JSONObject issue = new JSONObject();
        JSONObject fields = new JSONObject();
        JSONObject project = new JSONObject();
        JSONObject issuetype =new JSONObject();
        JSONObject assignee = new JSONObject();
        JSONObject reporter = new JSONObject();

        fields.put("summary", "RESTAssured summary");
        project.put("id", "10315");
        issuetype.put("id","10004");
        assignee.put("name","katherinebilous");
        reporter.put("name","katherinebilous");
        issue.put("username", "katherinebilous");
        issue.put("password", "Polis484)");
        issue.put("fields", fields);
        fields.put("project",project);
        fields.put("issuetype", issuetype);
        fields.put("assignee", assignee);
        fields.put("reporter", reporter);

        return issue.toJSONString();
    }
    public String generateJSONForEditSummary(){

        JSONObject update =new JSONObject();
        JSONObject summaryValue=new JSONObject();
        JSONObject summary=new JSONObject();
        JSONArray internalSummary=new JSONArray();
        summary.put("set", "new edit summary");
        internalSummary.add(summary);
        update.put("summary", internalSummary);
        summaryValue.put("update", update);





         return update.toJSONString();
    }
    public String generateJSONForIssueType(){
        JSONObject credentials =new JSONObject();
        JSONObject fields= new JSONObject();
        JSONObject issuetype = new JSONObject();
        fields.put("issuetype", issuetype);
        issuetype.put("id", "10001");
        credentials.put("fields", fields);
        return fields.toJSONString();

    }
    public String generateJSONForSearchFilter(){
        JSONObject credentials=new JSONObject();
        JSONArray array=new JSONArray();

        credentials.put("jql", " project = QAAutomation2");
        credentials.put("startAt","0");
        credentials.put("maxResults", " 1000");
        array.add("summary");
        array.add( "key");


        System.out.println(credentials.toString());
        return credentials.toJSONString();



    }
    public String generateJSONForAddComment(){
        JSONObject addCommentValue=new JSONObject();
        addCommentValue.put("body", "This is a comment regarding the quality of the response.");
        return addCommentValue.toJSONString();
    }
    public String generateJSONForAssign(){
        JSONObject assignValue =new JSONObject();
        assignValue.put("name", "katherinebilous");
        return  generateJSONForSampleIssue();

    }


}
