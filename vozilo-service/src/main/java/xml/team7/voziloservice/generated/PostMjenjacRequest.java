//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.03 at 08:16:17 PM CEST 
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
 *         &lt;element name="mjenjacRequest" type="{https://ftn.uns.ac.rs/vozilo}TTipMjenjaca"/&gt;
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
    "mjenjacRequest"
})
@XmlRootElement(name = "PostMjenjacRequest")
public class PostMjenjacRequest {

    @XmlElement(required = true)
    protected TTipMjenjaca mjenjacRequest;

    /**
     * Gets the value of the mjenjacRequest property.
     *
     * @return
     *     possible object is
     *     {@link TTipMjenjaca }
     *
     */
    public TTipMjenjaca getMjenjacRequest() {
        return mjenjacRequest;
    }

    /**
     * Sets the value of the mjenjacRequest property.
     *
     * @param value
     *     allowed object is
     *     {@link TTipMjenjaca }
     *     
     */
    public void setMjenjacRequest(TTipMjenjaca value) {
        this.mjenjacRequest = value;
    }

}
