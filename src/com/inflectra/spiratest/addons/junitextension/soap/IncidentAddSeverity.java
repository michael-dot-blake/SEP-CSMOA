
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
 *         &lt;element name="remoteIncidentSeverity" type="{http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects}RemoteIncidentSeverity" minOccurs="0"/>
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
    "remoteIncidentSeverity"
})
@XmlRootElement(name = "Incident_AddSeverity")
public class IncidentAddSeverity {

    @XmlElementRef(name = "remoteIncidentSeverity", namespace = "http://www.inflectra.com/SpiraTest/Services/v3.0/", type = JAXBElement.class)
    protected JAXBElement<RemoteIncidentSeverity> remoteIncidentSeverity;

    /**
     * Gets the value of the remoteIncidentSeverity property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RemoteIncidentSeverity }{@code >}
     *     
     */
    public JAXBElement<RemoteIncidentSeverity> getRemoteIncidentSeverity() {
        return remoteIncidentSeverity;
    }

    /**
     * Sets the value of the remoteIncidentSeverity property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RemoteIncidentSeverity }{@code >}
     *     
     */
    public void setRemoteIncidentSeverity(JAXBElement<RemoteIncidentSeverity> value) {
        this.remoteIncidentSeverity = ((JAXBElement<RemoteIncidentSeverity> ) value);
    }

}
