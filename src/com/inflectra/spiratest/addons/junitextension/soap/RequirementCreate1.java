
package com.inflectra.spiratest.addons.junitextension.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="remoteRequirement" type="{http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects}RemoteRequirement" minOccurs="0"/>
 *         &lt;element name="indentPosition" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "remoteRequirement",
    "indentPosition"
})
@XmlRootElement(name = "Requirement_Create1")
public class RequirementCreate1 {

    @XmlElementRef(name = "remoteRequirement", namespace = "http://www.inflectra.com/SpiraTest/Services/v3.0/", type = JAXBElement.class)
    protected JAXBElement<RemoteRequirement> remoteRequirement;
    protected Integer indentPosition;

    /**
     * Gets the value of the remoteRequirement property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RemoteRequirement }{@code >}
     *     
     */
    public JAXBElement<RemoteRequirement> getRemoteRequirement() {
        return remoteRequirement;
    }

    /**
     * Sets the value of the remoteRequirement property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RemoteRequirement }{@code >}
     *     
     */
    public void setRemoteRequirement(JAXBElement<RemoteRequirement> value) {
        this.remoteRequirement = ((JAXBElement<RemoteRequirement> ) value);
    }

    /**
     * Gets the value of the indentPosition property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIndentPosition() {
        return indentPosition;
    }

    /**
     * Sets the value of the indentPosition property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIndentPosition(Integer value) {
        this.indentPosition = value;
    }

}
