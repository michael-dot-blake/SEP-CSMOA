
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
 *         &lt;element name="Incident_AddTypeResult" type="{http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects}RemoteIncidentType" minOccurs="0"/>
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
    "incidentAddTypeResult"
})
@XmlRootElement(name = "Incident_AddTypeResponse")
public class IncidentAddTypeResponse {

    @XmlElementRef(name = "Incident_AddTypeResult", namespace = "http://www.inflectra.com/SpiraTest/Services/v3.0/", type = JAXBElement.class)
    protected JAXBElement<RemoteIncidentType> incidentAddTypeResult;

    /**
     * Gets the value of the incidentAddTypeResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RemoteIncidentType }{@code >}
     *     
     */
    public JAXBElement<RemoteIncidentType> getIncidentAddTypeResult() {
        return incidentAddTypeResult;
    }

    /**
     * Sets the value of the incidentAddTypeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RemoteIncidentType }{@code >}
     *     
     */
    public void setIncidentAddTypeResult(JAXBElement<RemoteIncidentType> value) {
        this.incidentAddTypeResult = ((JAXBElement<RemoteIncidentType> ) value);
    }

}
