
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
 *         &lt;element name="remoteTask" type="{http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects}RemoteTask" minOccurs="0"/>
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
    "remoteTask"
})
@XmlRootElement(name = "Task_Create")
public class TaskCreate {

    @XmlElementRef(name = "remoteTask", namespace = "http://www.inflectra.com/SpiraTest/Services/v3.0/", type = JAXBElement.class)
    protected JAXBElement<RemoteTask> remoteTask;

    /**
     * Gets the value of the remoteTask property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RemoteTask }{@code >}
     *     
     */
    public JAXBElement<RemoteTask> getRemoteTask() {
        return remoteTask;
    }

    /**
     * Sets the value of the remoteTask property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RemoteTask }{@code >}
     *     
     */
    public void setRemoteTask(JAXBElement<RemoteTask> value) {
        this.remoteTask = ((JAXBElement<RemoteTask> ) value);
    }

}
