
package com.scc.softdev.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tIssueSolution complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tIssueSolution">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tObject">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EstReqTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="EstDesTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="EstCodTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="EstTcdTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="EstTstTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="IsReqReleaseNote" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsReqTranslation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsReqTrainingUpdate" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsTestBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsAutoUT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsApproved" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tIssueSolution", propOrder = {
    "name",
    "type",
    "description",
    "estReqTime",
    "estDesTime",
    "estCodTime",
    "estTcdTime",
    "estTstTime",
    "isReqReleaseNote",
    "isReqTranslation",
    "isReqTrainingUpdate",
    "isTestBy",
    "isAutoUT",
    "isApproved"
})
public class TIssueSolution
    extends TObject
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Type")
    protected String type;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "EstReqTime")
    protected Long estReqTime;
    @XmlElement(name = "EstDesTime")
    protected Long estDesTime;
    @XmlElement(name = "EstCodTime")
    protected Long estCodTime;
    @XmlElement(name = "EstTcdTime")
    protected Long estTcdTime;
    @XmlElement(name = "EstTstTime")
    protected Long estTstTime;
    @XmlElement(name = "IsReqReleaseNote")
    protected Boolean isReqReleaseNote;
    @XmlElement(name = "IsReqTranslation")
    protected Boolean isReqTranslation;
    @XmlElement(name = "IsReqTrainingUpdate")
    protected Boolean isReqTrainingUpdate;
    @XmlElement(name = "IsTestBy")
    protected String isTestBy;
    @XmlElement(name = "IsAutoUT")
    protected String isAutoUT;
    @XmlElement(name = "IsApproved")
    protected String isApproved;

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the estReqTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEstReqTime() {
        return estReqTime;
    }

    /**
     * Sets the value of the estReqTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEstReqTime(Long value) {
        this.estReqTime = value;
    }

    /**
     * Gets the value of the estDesTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEstDesTime() {
        return estDesTime;
    }

    /**
     * Sets the value of the estDesTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEstDesTime(Long value) {
        this.estDesTime = value;
    }

    /**
     * Gets the value of the estCodTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEstCodTime() {
        return estCodTime;
    }

    /**
     * Sets the value of the estCodTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEstCodTime(Long value) {
        this.estCodTime = value;
    }

    /**
     * Gets the value of the estTcdTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEstTcdTime() {
        return estTcdTime;
    }

    /**
     * Sets the value of the estTcdTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEstTcdTime(Long value) {
        this.estTcdTime = value;
    }

    /**
     * Gets the value of the estTstTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEstTstTime() {
        return estTstTime;
    }

    /**
     * Sets the value of the estTstTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEstTstTime(Long value) {
        this.estTstTime = value;
    }

    /**
     * Gets the value of the isReqReleaseNote property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsReqReleaseNote() {
        return isReqReleaseNote;
    }

    /**
     * Sets the value of the isReqReleaseNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsReqReleaseNote(Boolean value) {
        this.isReqReleaseNote = value;
    }

    /**
     * Gets the value of the isReqTranslation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsReqTranslation() {
        return isReqTranslation;
    }

    /**
     * Sets the value of the isReqTranslation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsReqTranslation(Boolean value) {
        this.isReqTranslation = value;
    }

    /**
     * Gets the value of the isReqTrainingUpdate property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsReqTrainingUpdate() {
        return isReqTrainingUpdate;
    }

    /**
     * Sets the value of the isReqTrainingUpdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsReqTrainingUpdate(Boolean value) {
        this.isReqTrainingUpdate = value;
    }

    /**
     * Gets the value of the isTestBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsTestBy() {
        return isTestBy;
    }

    /**
     * Sets the value of the isTestBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsTestBy(String value) {
        this.isTestBy = value;
    }

    /**
     * Gets the value of the isAutoUT property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsAutoUT() {
        return isAutoUT;
    }

    /**
     * Sets the value of the isAutoUT property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsAutoUT(String value) {
        this.isAutoUT = value;
    }

    /**
     * Gets the value of the isApproved property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsApproved() {
        return isApproved;
    }

    /**
     * Sets the value of the isApproved property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsApproved(String value) {
        this.isApproved = value;
    }

}
