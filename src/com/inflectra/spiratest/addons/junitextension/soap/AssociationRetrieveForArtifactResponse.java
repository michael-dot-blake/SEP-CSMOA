
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
 *         &lt;element name="Association_RetrieveForArtifactResult" type="{http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects}ArrayOfRemoteAssociation" minOccurs="0"/>
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
    "associationRetrieveForArtifactResult"
})
@XmlRootElement(name = "Association_RetrieveForArtifactResponse")
public class AssociationRetrieveForArtifactResponse {

    @XmlElementRef(name = "Association_RetrieveForArtifactResult", namespace = "http://www.inflectra.com/SpiraTest/Services/v3.0/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfRemoteAssociation> associationRetrieveForArtifactResult;

    /**
     * Gets the value of the associationRetrieveForArtifactResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRemoteAssociation }{@code >}
     *     
     */
    public JAXBElement<ArrayOfRemoteAssociation> getAssociationRetrieveForArtifactResult() {
        return associationRetrieveForArtifactResult;
    }

    /**
     * Sets the value of the associationRetrieveForArtifactResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRemoteAssociation }{@code >}
     *     
     */
    public void setAssociationRetrieveForArtifactResult(JAXBElement<ArrayOfRemoteAssociation> value) {
        this.associationRetrieveForArtifactResult = ((JAXBElement<ArrayOfRemoteAssociation> ) value);
    }

}
