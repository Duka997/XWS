//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.02 at 11:25:01 PM CEST 
//


package xml.team7.voziloservice.generated;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="voziloRequest" type="{https://ftn.uns.ac.rs/vozilo}TVozilo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "voziloRequest"
})
@XmlRootElement(name = "PostVoziloRequest")
public class PostVoziloRequest {

    @XmlElement(required = true)
    protected TVozilo voziloRequest;

    /**
     * Gets the value of the voziloRequest property.
     *
     * @return
     *     possible object is
     *     {@link TVozilo }
     *
     */
    public TVozilo getVoziloRequest() {
        return voziloRequest;
    }

    /**
     * Sets the value of the voziloRequest property.
     *
     * @param value
     *     allowed object is
     *     {@link TVozilo }
     *     
     */
    public void setVoziloRequest(TVozilo value) {
        this.voziloRequest = value;
    }

}
