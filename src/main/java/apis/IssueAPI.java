package apis;

import com.jayway.restassured.response.Response;
import utils.RequestSender;

public class IssueAPI   {

    RequestSender requestSender;

    public Response secureCreateIssue(String body) {
        requestSender =new RequestSender();

        requestSender
                .secureCreateRequest(body)
                .post(ApiUrls.ISSUE.getUri());
        return requestSender.response;

    }
    public Response createIssue(String body){
        requestSender=new RequestSender();
        requestSender
                .createRequest(body)
                .post(ApiUrls.ISSUE.getUri());
        return requestSender.response;
    }


    public Response deleteIssue(String issueId) {
        requestSender = new RequestSender();
        requestSender
                .voidCreateRequest()
                .delete(ApiUrls.ISSUE.getUri(issueId));
        return requestSender.response;

    }
    public  Response secureDeleteIssue(String issueId){
        requestSender = new RequestSender();
        requestSender
                .secureCreateRequest()
                .delete(ApiUrls.ISSUE.getUri(issueId));
        return requestSender.response;
    }

    public Response secureAddComment(String issueId, String body) {
        requestSender = new RequestSender();
        requestSender
                .secureCreateRequest(body)
                .post(ApiUrls.ISSUE.getUri() + "/" + issueId + "/comment");
        return requestSender.response;

    }
    public Response deleteComment(String issueId){
        requestSender = new RequestSender();
        requestSender
                .voidCreateRequest()
                .delete(ApiUrls.ISSUE.getUri() + "/" + issueId + "/comment");
        return requestSender.response;


    }
    public Response addComment (String issueId, String body){
        requestSender = new RequestSender();
        requestSender
                .createRequest(body)
                .post(ApiUrls.ISSUE.getUri() + "/" + issueId + "/comment");
        return requestSender.response;
    }
    public Response getSecureIssue (String issueId){
        requestSender =new RequestSender();
        requestSender
                .secureCreateRequest()
                .get(ApiUrls.ISSUE.getUri(issueId));
        return requestSender.response;
    }
    public Response editSummarySecure (String issueId, String body){
        requestSender =new RequestSender();
        requestSender
                .secureCreateRequest(body)
                .put(ApiUrls.ISSUE.getUri(issueId));
        return requestSender.response;
    }
    public Response editSummary (String issueId, String body){
        requestSender =new RequestSender();
        requestSender
                .createRequest(body)
                .put(ApiUrls.ISSUE.getUri(issueId));
        return requestSender.response;
    }
    public Response secureGetComment(String issueId, String CommentId){
        requestSender =new RequestSender();
        requestSender
                .secureCreateRequest()
                .get(ApiUrls.ISSUE.getUri()+"/"+issueId+"/comment/"+CommentId);
        return requestSender.response;
    }
    public Response changeIssueType (String issueId, String body){
        requestSender =new RequestSender();
        requestSender
                .secureCreateRequest(body)
                .put(ApiUrls.ISSUE.getUri(issueId));
        return requestSender.response;

    }
    public Response secureSearch (String issueId, String body){
        requestSender =new RequestSender();

        requestSender
                .secureCreateRequest(body)
                .get(ApiUrls.SEARCH.getUri(issueId));
        return requestSender.response;
    }
    public Response search (String issueId, String body){
        requestSender
                .createRequest(body)
                .get(ApiUrls.SEARCH.getUri(issueId));
        return requestSender.response;
    }
    public Response secureAssign (String issueId, String body){
        requestSender =new RequestSender();
        requestSender
                .secureCreateRequest(body)
                .put(ApiUrls.ISSUE.getUri(issueId)+"/assignee");
        return requestSender.response;

    }
    public Response assign (String issueId, String body) {
        requestSender = new RequestSender();
        requestSender
                .createRequest(body)
                .put(ApiUrls.ISSUE.getUri(issueId) + "/assignee");
        return requestSender.response;
    }




}