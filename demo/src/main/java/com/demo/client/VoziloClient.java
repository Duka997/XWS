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

    public PostKlasaAutomobilaResponse postNewKlasa(TKlasaAutomobila tKlasa) {
        PostKlasaAutomobilaRequest request = new PostKlasaAutomobilaRequest();
        request.setKlasaRequest(tKlasa);

        PostKlasaAutomobilaResponse response = (PostKlasaAutomobilaResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8082/vozilo-soap/vozilo", request,
                        new SoapActionCallback("https://ftn.uns.ac.rs/vozilo/PostKlasaAutomobilaRequest"));

        return response;
    }

    public PostMarkaResponse postNewMarka(TMarkaAutomobila tMarka) {
        PostMarkaRequest request = new PostMarkaRequest();
        request.setMarkaRequest(tMarka);

        PostMarkaResponse response = (PostMarkaResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8082/vozilo-soap/vozilo", request,
                        new SoapActionCallback("https://ftn.uns.ac.rs/vozilo/PostMarkaRequest"));

        return response;
    }

    public PostMjenjacResponse postNewMjenjac(TTipMjenjaca t) {
        PostMjenjacRequest request = new PostMjenjacRequest();
        request.setMjenjacRequest(t);

        PostMjenjacResponse response = (PostMjenjacResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8082/vozilo-soap/vozilo", request,
                        new SoapActionCallback("https://ftn.uns.ac.rs/vozilo/PostMjenjacRequest"));

        return response;
    }

    public PostGorivoResponse postNewGorivo(TTipGoriva t) {
        PostGorivoRequest request = new PostGorivoRequest();
        request.setGorivoRequest(t);

        PostGorivoResponse response = (PostGorivoResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8082/vozilo-soap/vozilo", request,
                        new SoapActionCallback("https://ftn.uns.ac.rs/vozilo/PostGorivoRequest"));

        return response;
    }

    public GetGradeResponse getGrades(Long id) {
        GetGradeRequest request = new GetGradeRequest();
        request.setGradeRequest(id);


        GetGradeResponse response = (GetGradeResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8082/vozilo-soap/vozilo", request,
                        new SoapActionCallback("https://ftn.uns.ac.rs/vozilo/GetGradeRequest"));

        return response;
    }
    public PostVoziloResponse postNewVozilo(TVozilo tVozilo) {
        PostVoziloRequest request = new PostVoziloRequest();
        request.setVoziloRequest(tVozilo);

        PostVoziloResponse response = (PostVoziloResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8082/vozilo-soap/vozilo", request,
                        new SoapActionCallback("https://ftn.uns.ac.rs/vozilo/PostVoziloRequest"));

        return response;
    }

}
