
package com.inflectra.spiratest.addons.junitextension.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RemoteArtifact complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RemoteArtifact">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="List01" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="List02" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="List03" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="List04" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="List05" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="List06" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="List07" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="List08" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="List09" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="List10" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Text01" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text02" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text03" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text04" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text05" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text06" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text07" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text08" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text09" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Text10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RemoteArtifact", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", propOrder = {
    "list01",
    "list02",
    "list03",
    "list04",
    "list05",
    "list06",
    "list07",
    "list08",
    "list09",
    "list10",
    "text01",
    "text02",
    "text03",
    "text04",
    "text05",
    "text06",
    "text07",
    "text08",
    "text09",
    "text10"
})
@XmlSeeAlso({
    RemoteIncident.class,
    RemoteTestSet.class,
    RemoteRelease.class,
    RemoteRequirement.class,
    RemoteTask.class,
    RemoteTestCase.class,
    RemoteTestRun.class,
    RemoteTestStep.class
})
public class RemoteArtifact {

    @XmlElementRef(name = "List01", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<Integer> list01;
    @XmlElementRef(name = "List02", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<Integer> list02;
    @XmlElementRef(name = "List03", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<Integer> list03;
    @XmlElementRef(name = "List04", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<Integer> list04;
    @XmlElementRef(name = "List05", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<Integer> list05;
    @XmlElementRef(name = "List06", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<Integer> list06;
    @XmlElementRef(name = "List07", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<Integer> list07;
    @XmlElementRef(name = "List08", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<Integer> list08;
    @XmlElementRef(name = "List09", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<Integer> list09;
    @XmlElementRef(name = "List10", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<Integer> list10;
    @XmlElementRef(name = "Text01", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<String> text01;
    @XmlElementRef(name = "Text02", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<String> text02;
    @XmlElementRef(name = "Text03", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<String> text03;
    @XmlElementRef(name = "Text04", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<String> text04;
    @XmlElementRef(name = "Text05", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<String> text05;
    @XmlElementRef(name = "Text06", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<String> text06;
    @XmlElementRef(name = "Text07", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<String> text07;
    @XmlElementRef(name = "Text08", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<String> text08;
    @XmlElementRef(name = "Text09", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<String> text09;
    @XmlElementRef(name = "Text10", namespace = "http://schemas.datacontract.org/2004/07/Inflectra.SpiraTest.Web.Services.v3_0.DataObjects", type = JAXBElement.class)
    protected JAXBElement<String> text10;

    /**
     * Gets the value of the list01 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getList01() {
        return list01;
    }

    /**
     * Sets the value of the list01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setList01(JAXBElement<Integer> value) {
        this.list01 = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the list02 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getList02() {
        return list02;
    }

    /**
     * Sets the value of the list02 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setList02(JAXBElement<Integer> value) {
        this.list02 = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the list03 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getList03() {
        return list03;
    }

    /**
     * Sets the value of the list03 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setList03(JAXBElement<Integer> value) {
        this.list03 = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the list04 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getList04() {
        return list04;
    }

    /**
     * Sets the value of the list04 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setList04(JAXBElement<Integer> value) {
        this.list04 = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the list05 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getList05() {
        return list05;
    }

    /**
     * Sets the value of the list05 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setList05(JAXBElement<Integer> value) {
        this.list05 = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the list06 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getList06() {
        return list06;
    }

    /**
     * Sets the value of the list06 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setList06(JAXBElement<Integer> value) {
        this.list06 = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the list07 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getList07() {
        return list07;
    }

    /**
     * Sets the value of the list07 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setList07(JAXBElement<Integer> value) {
        this.list07 = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the list08 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getList08() {
        return list08;
    }

    /**
     * Sets the value of the list08 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setList08(JAXBElement<Integer> value) {
        this.list08 = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the list09 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getList09() {
        return list09;
    }

    /**
     * Sets the value of the list09 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setList09(JAXBElement<Integer> value) {
        this.list09 = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the list10 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getList10() {
        return list10;
    }

    /**
     * Sets the value of the list10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setList10(JAXBElement<Integer> value) {
        this.list10 = ((JAXBElement<Integer> ) value);
    }

    /**
     * Gets the value of the text01 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getText01() {
        return text01;
    }

    /**
     * Sets the value of the text01 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setText01(JAXBElement<String> value) {
        this.text01 = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the text02 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getText02() {
        return text02;
    }

    /**
     * Sets the value of the text02 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setText02(JAXBElement<String> value) {
        this.text02 = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the text03 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getText03() {
        return text03;
    }

    /**
     * Sets the value of the text03 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setText03(JAXBElement<String> value) {
        this.text03 = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the text04 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getText04() {
        return text04;
    }

    /**
     * Sets the value of the text04 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setText04(JAXBElement<String> value) {
        this.text04 = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the text05 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getText05() {
        return text05;
    }

    /**
     * Sets the value of the text05 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setText05(JAXBElement<String> value) {
        this.text05 = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the text06 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getText06() {
        return text06;
    }

    /**
     * Sets the value of the text06 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setText06(JAXBElement<String> value) {
        this.text06 = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the text07 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getText07() {
        return text07;
    }

    /**
     * Sets the value of the text07 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setText07(JAXBElement<String> value) {
        this.text07 = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the text08 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getText08() {
        return text08;
    }

    /**
     * Sets the value of the text08 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setText08(JAXBElement<String> value) {
        this.text08 = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the text09 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getText09() {
        return text09;
    }

    /**
     * Sets the value of the text09 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setText09(JAXBElement<String> value) {
        this.text09 = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the text10 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getText10() {
        return text10;
    }

    /**
     * Sets the value of the text10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setText10(JAXBElement<String> value) {
        this.text10 = ((JAXBElement<String> ) value);
    }

}
