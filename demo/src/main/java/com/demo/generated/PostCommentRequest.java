//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.01 at 09:10:45 AM CEST 
//


package com.demo.generated;

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
 *         &lt;element name="commentRequest" type="{https://ftn.uns.ac.rs/vozilo}TComment"/&gt;
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
    "commentRequest"
})
@XmlRootElement(name = "PostCommentRequest")
public class PostCommentRequest {

    @XmlElement(required = true)
    protected TComment commentRequest;

    /**
     * Gets the value of the commentRequest property.
     * 
     * @return
     *     possible object is
     *     {@link TComment }
     *     
     */
    public TComment getCommentRequest() {
        return commentRequest;
    }

    /**
     * Sets the value of the commentRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link TComment }
     *     
     */
    public void setCommentRequest(TComment value) {
        this.commentRequest = value;
    }

}
