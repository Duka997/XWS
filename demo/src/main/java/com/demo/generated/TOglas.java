//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.09.03 at 03:40:47 PM CEST 
//


package com.demo.generated;

import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TOglas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TOglas"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="dostupan" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="od" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="doo" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="mestoPreuzimanja" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="user" type="{https://ftn.uns.ac.rs/vozilo}TUser"/&gt;
 *         &lt;element name="cjenovnikID" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TOglas", propOrder = {
    "id",
    "dostupan",
    "od",
    "doo",
    "mestoPreuzimanja",
    "user",
    "cjenovnikID"
})
public class TOglas {

    protected long id;
    protected boolean dostupan;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar od;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar doo;
    @XmlElement(required = true)
    protected String mestoPreuzimanja;
    @XmlElement(required = true)
    protected TUser user;
    protected long cjenovnikID;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the dostupan property.
     * 
     */
    public boolean isDostupan() {
        return dostupan;
    }

    /**
     * Sets the value of the dostupan property.
     * 
     */
    public void setDostupan(boolean value) {
        this.dostupan = value;
    }

    /**
     * Gets the value of the od property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOd() {
        return od;
    }

    /**
     * Sets the value of the od property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOd(XMLGregorianCalendar value) {
        this.od = value;
    }

    /**
     * Gets the value of the doo property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDoo() {
        return doo;
    }

    /**
     * Sets the value of the doo property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDoo(XMLGregorianCalendar value) {
        this.doo = value;
    }

    /**
     * Gets the value of the mestoPreuzimanja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMestoPreuzimanja() {
        return mestoPreuzimanja;
    }

    /**
     * Sets the value of the mestoPreuzimanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMestoPreuzimanja(String value) {
        this.mestoPreuzimanja = value;
    }

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link TUser }
     *     
     */
    public TUser getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link TUser }
     *     
     */
    public void setUser(TUser value) {
        this.user = value;
    }

    /**
     * Gets the value of the cjenovnikID property.
     * 
     */
    public long getCjenovnikID() {
        return cjenovnikID;
    }

    /**
     * Sets the value of the cjenovnikID property.
     * 
     */
    public void setCjenovnikID(long value) {
        this.cjenovnikID = value;
    }

}
