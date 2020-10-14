
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
 *         &lt;element name="remoteIncidentStatus" type="{http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects}RemoteIncidentStatus" minOccurs="0"/>
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
    "remoteIncidentStatus"
})
@XmlRootElement(name = "Incident_AddStatus")
public class IncidentAddStatus {

    @XmlElementRef(name = "remoteIncidentStatus", namespace = "http://www.inflectra.com/SpiraTest/Services/v3.0/", type = JAXBElement.class)
    protected JAXBElement<RemoteIncidentStatus> remoteIncidentStatus;

    /**
     * Gets the value of the remoteIncidentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RemoteIncidentStatus }{@code >}
     *     
     */
    public JAXBElement<RemoteIncidentStatus> getRemoteIncidentStatus() {
        return remoteIncidentStatus;
    }

    /**
     * Sets the value of the remoteIncidentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RemoteIncidentStatus }{@code >}
     *     
     */
    public void setRemoteIncidentStatus(JAXBElement<RemoteIncidentStatus> value) {
        this.remoteIncidentStatus = ((JAXBElement<RemoteIncidentStatus> ) value);
    }

}
