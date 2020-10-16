
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
 *         &lt;element name="remoteUser" type="{http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects}RemoteUser" minOccurs="0"/>
 *         &lt;element name="projectRoleId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    "remoteUser",
    "projectRoleId"
})
@XmlRootElement(name = "User_Create")
public class UserCreate {

    @XmlElementRef(name = "remoteUser", namespace = "http://www.inflectra.com/SpiraTest/Services/v3.0/", type = JAXBElement.class)
    protected JAXBElement<RemoteUser> remoteUser;
    protected Integer projectRoleId;

    /**
     * Gets the value of the remoteUser property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RemoteUser }{@code >}
     *     
     */
    public JAXBElement<RemoteUser> getRemoteUser() {
        return remoteUser;
    }

    /**
     * Sets the value of the remoteUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RemoteUser }{@code >}
     *     
     */
    public void setRemoteUser(JAXBElement<RemoteUser> value) {
        this.remoteUser = ((JAXBElement<RemoteUser> ) value);
    }

    /**
     * Gets the value of the projectRoleId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProjectRoleId() {
        return projectRoleId;
    }

    /**
     * Sets the value of the projectRoleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProjectRoleId(Integer value) {
        this.projectRoleId = value;
    }

}
