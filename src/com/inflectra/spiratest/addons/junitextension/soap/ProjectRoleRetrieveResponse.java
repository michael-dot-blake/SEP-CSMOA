
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
 *         &lt;element name="ProjectRole_RetrieveResult" type="{http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects}ArrayOfRemoteProjectRole" minOccurs="0"/>
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
    "projectRoleRetrieveResult"
})
@XmlRootElement(name = "ProjectRole_RetrieveResponse")
public class ProjectRoleRetrieveResponse {

    @XmlElementRef(name = "ProjectRole_RetrieveResult", namespace = "http://www.inflectra.com/SpiraTest/Services/v3.0/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfRemoteProjectRole> projectRoleRetrieveResult;

    /**
     * Gets the value of the projectRoleRetrieveResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRemoteProjectRole }{@code >}
     *     
     */
    public JAXBElement<ArrayOfRemoteProjectRole> getProjectRoleRetrieveResult() {
        return projectRoleRetrieveResult;
    }

    /**
     * Sets the value of the projectRoleRetrieveResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRemoteProjectRole }{@code >}
     *     
     */
    public void setProjectRoleRetrieveResult(JAXBElement<ArrayOfRemoteProjectRole> value) {
        this.projectRoleRetrieveResult = ((JAXBElement<ArrayOfRemoteProjectRole> ) value);
    }

}
