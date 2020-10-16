
package com.inflectra.spiratest.addons.junitextension.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;comments xmlns:msc="http://schemas.microsoft.com/ws/2005/12/wsdl/contract" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" xmlns:soap12="http://schemas.xmlsoap.org/wsdl/soap12/" xmlns:soapenc="http://schemas.xmlsoap.org/soap/encoding/" xmlns:tns="http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects" xmlns:wsa="http://schemas.xmlsoap.org/ws/2004/08/addressing" xmlns:wsa10="http://www.w3.org/2005/08/addressing" xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata" xmlns:wsap="http://schemas.xmlsoap.org/ws/2004/08/addressing/policy" xmlns:wsaw="http://www.w3.org/2006/05/addressing/wsdl" xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/" xmlns:wsp="http://schemas.xmlsoap.org/ws/2004/09/policy" xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" xmlns:wsx="http://schemas.xmlsoap.org/ws/2004/09/mex" xmlns:xsd="http://www.w3.org/2001/XMLSchema"&gt;&lt;summary&gt;
 *             Represents a single project custom property configuration entry
 *             &lt;/summary&gt;&lt;/comments&gt;
 * </pre>
 * 
 * 
 * <p>Java class for RemoteCustomProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteCustomProperty">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Alias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ArtifactTypeId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CustomList" type="{http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects}RemoteCustomList" minOccurs="0"/>
 *         &lt;element name="CustomPropertyId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CustomPropertyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CustomPropertyTypeId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="CustomPropertyTypeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProjectId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteCustomProperty", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", propOrder = {
    "alias",
    "artifactTypeId",
    "customList",
    "customPropertyId",
    "customPropertyName",
    "customPropertyTypeId",
    "customPropertyTypeName",
    "projectId"
})
public class RemoteCustomProperty {

    @XmlElementRef(name = "Alias", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<String> alias;
    @XmlElement(name = "ArtifactTypeId")
    protected Integer artifactTypeId;
    @XmlElementRef(name = "CustomList", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<RemoteCustomList> customList;
    @XmlElement(name = "CustomPropertyId")
    protected Integer customPropertyId;
    @XmlElementRef(name = "CustomPropertyName", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<String> customPropertyName;
    @XmlElementRef(name = "CustomPropertyTypeId", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<Integer> customPropertyTypeId;
    @XmlElementRef(name = "CustomPropertyTypeName", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<String> customPropertyTypeName;
    @XmlElement(name = "ProjectId")
    protected Integer projectId;

    /**
     * Gets the value of the alias property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAlias() {
        return alias;
    }

    /**
     * Sets the value of the alias property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAlias(JAXBElement<String> value) {
        this.alias = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the artifactTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getArtifactTypeId() {
        return artifactTypeId;
    }

    /**
     * Sets the value of the artifactTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setArtifactTypeId(Integer value) {
        this.artifactTypeId = value;
    }

    /**
     * Gets the value of the customList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RemoteCustomList }{@code >}
     *     
     */
    public JAXBElement<RemoteCustomList> getCustomList() {
        return customList;
    }

    /**
     * Sets the value of the customList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RemoteCustomList }{@code >}
     *     
     */
    public void setCustomList(JAXBElement<RemoteCustomList> value) {
        this.customList = ((JAXBElement<RemoteCustomList> ) value);
    }

    /**
     * Gets the value of the customPropertyId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCustomPropertyId() {
        return customPropertyId;
    }

    /**
     * Sets the value of the customPropertyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCustomPropertyId(Integer value) {
        this.customPropertyId = value;
    }

    /**
     * Gets the value of the customPropertyName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCustomPropertyName() {
        return customPropertyName;
    }

    /**
     * Sets the value of the customPropertyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCustomPropertyName(JAXBElement<String> value) {
        this.customPropertyName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the customPropertyTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getCustomPropertyTypeId() {
        return customPropertyTypeId;
    }

    /**
     * Sets the value of the customPropertyTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setCustomPropertyTypeId(JAXBElement<Integer> value) {
        this.customPropertyTypeId = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the customPropertyTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCustomPropertyTypeName() {
        return customPropertyTypeName;
    }

    /**
     * Sets the value of the customPropertyTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCustomPropertyTypeName(JAXBElement<String> value) {
        this.customPropertyTypeName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the projectId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getProjectId() {
        return projectId;
    }

    /**
     * Sets the value of the projectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setProjectId(Integer value) {
        this.projectId = value;
    }

}
