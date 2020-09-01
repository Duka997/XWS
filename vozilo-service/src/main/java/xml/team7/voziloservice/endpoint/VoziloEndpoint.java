package xml.team7.voziloservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import xml.team7.voziloservice.generated.GetCommentRequest;
import xml.team7.voziloservice.generated.GetCommentResponse;
import xml.team7.voziloservice.generated.PostCommentRequest;
import xml.team7.voziloservice.generated.PostCommentResponse;
import xml.team7.voziloservice.service.KomentarService;

@Endpoint
public class VoziloEndpoint {
    private static final String NAMESPACE_URI = "https://ftn.uns.ac.rs/vozilo";

    @Autowired
    private KomentarService komentarService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PostCommentRequest")
    @ResponsePayload
    public PostCommentResponse postComment(@RequestPayload PostCommentRequest request) {
        return this.komentarService.postCommentSoap(request.getCommentRequest());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCommentRequest")
    @ResponsePayload
    public GetCommentResponse getComments(@RequestPayload GetCommentRequest request) {
        return this.komentarService.getCommentsSoap(request.getCommentRequest());
    }

}
