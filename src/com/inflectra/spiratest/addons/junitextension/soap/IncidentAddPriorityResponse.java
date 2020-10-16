
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
 *         &lt;element name="Incident_AddPriorityResult" type="{http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects}RemoteIncidentPriority" minOccurs="0"/>
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
    "incidentAddPriorityResult"
})
@XmlRootElement(name = "Incident_AddPriorityResponse")
public class IncidentAddPriorityResponse {

    @XmlElementRef(name = "Incident_AddPriorityResult", namespace = "http://www.inflectra.com/SpiraTest/Services/v3.0/", type = JAXBElement.class)
    protected JAXBElement<RemoteIncidentPriority> incidentAddPriorityResult;

    /**
     * Gets the value of the incidentAddPriorityResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RemoteIncidentPriority }{@code >}
     *     
     */
    public JAXBElement<RemoteIncidentPriority> getIncidentAddPriorityResult() {
        return incidentAddPriorityResult;
    }

    /**
     * Sets the value of the incidentAddPriorityResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RemoteIncidentPriority }{@code >}
     *     
     */
    public void setIncidentAddPriorityResult(JAXBElement<RemoteIncidentPriority> value) {
        this.incidentAddPriorityResult = ((JAXBElement<RemoteIncidentPriority> ) value);
    }

}
