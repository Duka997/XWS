package xml.team7.voziloservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import xml.team7.voziloservice.generated.*;
import xml.team7.voziloservice.service.*;

@Endpoint
public class VoziloEndpoint {
    private static final String NAMESPACE_URI = "https://ftn.uns.ac.rs/vozilo";

    @Autowired
    private KomentarService komentarService;

    @Autowired
    private KlasaAutomobilaService klasaAutomobilaService;

    @Autowired
    private MarkaAutomobilaService markaAutomobilaService;

    @Autowired
    private TipGorivaService tipGorivaService;

    @Autowired
    private TipMjenjacaService tipMjenjacaService;

    @Autowired
    private VoziloService voziloService;

    @Autowired
    private OcjenaService ocjenaService;

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

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PostKlasaAutomobilaRequest")
    @ResponsePayload
    public PostKlasaAutomobilaResponse posKlasa(@RequestPayload PostKlasaAutomobilaRequest request) {
        return this.klasaAutomobilaService.postKlasaSoap(request.getKlasaRequest());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PostMarkaRequest")
    @ResponsePayload
    public PostMarkaResponse postMarkaAutomobila(@RequestPayload PostMarkaRequest request) {
        return this.markaAutomobilaService.postMarkaSoap(request.getMarkaRequest());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PostGorivoRequest")
    @ResponsePayload
    public PostGorivoResponse postGorivo(@RequestPayload PostGorivoRequest request) {
        return this.tipGorivaService.postGorivoSoap(request.getGorivoRequest());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PostMjenjacRequest")
    @ResponsePayload
    public PostMjenjacResponse postMjenjac(@RequestPayload PostMjenjacRequest request) {
        return this.tipMjenjacaService.postMjenjacSoap(request.getMjenjacRequest());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetStatisticsRequest")
    @ResponsePayload
    public GetStatisticsResponse getStatisticsInfo(@RequestPayload GetStatisticsRequest request) {
        return this.voziloService.getUserCars(request.getUserId());
    }

  /*  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PostVoziloRequest")
    @ResponsePayload
    public PostVoziloResponse postVozilo(@RequestPayload PostVoziloRequest request) {
        return this.voziloService.postVoziloSoap(request.getVoziloRequest());
    }*/

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetGradeRequest")
    @ResponsePayload
    public GetGradeResponse getGrades(@RequestPayload GetGradeRequest request) {
        return this.ocjenaService.getOcjeneSoap(request.getGradeRequest());
    }
}
