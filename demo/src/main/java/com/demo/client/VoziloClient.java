package com.demo.client;

import com.demo.generated.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class VoziloClient extends WebServiceGatewaySupport {

    public PostCommentResponse postNewComment(TComment tComment) {
        PostCommentRequest request = new PostCommentRequest();
        request.setCommentRequest(tComment);

        PostCommentResponse response = (PostCommentResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8082/vozilo-soap/vozilo", request,
                        new SoapActionCallback("https://ftn.uns.ac.rs/vozilo/PostCommentRequest"));

        return response;
    }

    public GetCommentResponse getComments(Long id) {
        GetCommentRequest request = new GetCommentRequest();
        request.setCommentRequest(id);

        GetCommentResponse response = (GetCommentResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8082/vozilo-soap/vozilo", request,
                        new SoapActionCallback("https://ftn.uns.ac.rs/vozilo/GetCommentRequest"));

        return response;
    }
}
