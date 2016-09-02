
package com.scc.softdev.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for tIssueDevlineEval complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tIssueDevlineEval">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tObject">
 *       &lt;sequence>
 *         &lt;element name="IsEvaluated" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsAffected" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="IsApproved" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="SolutionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DevlineId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="VersionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="SolutionDueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="SolutionPriority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SolutionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AddToScope" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tIssueDevlineEval", propOrder = {
    "isEvaluated",
    "isAffected",
    "isApproved",
    "solutionId",
    "devlineId",
    "versionId",
    "solutionDueDate",
    "solutionPriority",
    "solutionName",
    "addToScope"
})
public class TIssueDevlineEval
    extends TObject
{

    @XmlElement(name = "IsEvaluated")
    protected Boolean isEvaluated;
    @XmlElement(name = "IsAffected")
    protected Boolean isAffected;
    @XmlElement(name = "IsApproved")
    protected Boolean isApproved;
    @XmlElement(name = "SolutionId")
    protected Long solutionId;
    @XmlElement(name = "DevlineId")
    protected Long devlineId;
    @XmlElement(name = "VersionId")
    protected Long versionId;
    @XmlElement(name = "SolutionDueDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar solutionDueDate;
    @XmlElement(name = "SolutionPriority")
    protected String solutionPriority;
    @XmlElement(name = "SolutionName")
    protected String solutionName;
    @XmlElement(name = "AddToScope")
    protected Boolean addToScope;

    /**
     * Gets the value of the isEvaluated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsEvaluated() {
        return isEvaluated;
    }

    /**
     * Sets the value of the isEvaluated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsEvaluated(Boolean value) {
        this.isEvaluated = value;
    }

    /**
     * Gets the value of the isAffected property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAffected() {
        return isAffected;
    }

    /**
     * Sets the value of the isAffected property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAffected(Boolean value) {
        this.isAffected = value;
    }

    /**
     * Gets the value of the isApproved property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsApproved() {
        return isApproved;
    }

    /**
     * Sets the value of the isApproved property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsApproved(Boolean value) {
        this.isApproved = value;
    }

    /**
     * Gets the value of the solutionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSolutionId() {
        return solutionId;
    }

    /**
     * Sets the value of the solutionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSolutionId(Long value) {
        this.solutionId = value;
    }

    /**
     * Gets the value of the devlineId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDevlineId() {
        return devlineId;
    }

    /**
     * Sets the value of the devlineId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDevlineId(Long value) {
        this.devlineId = value;
    }

    /**
     * Gets the value of the versionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getVersionId() {
        return versionId;
    }

    /**
     * Sets the value of the versionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setVersionId(Long value) {
        this.versionId = value;
    }

    /**
     * Gets the value of the solutionDueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSolutionDueDate() {
        return solutionDueDate;
    }

    /**
     * Sets the value of the solutionDueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSolutionDueDate(XMLGregorianCalendar value) {
        this.solutionDueDate = value;
    }

    /**
     * Gets the value of the solutionPriority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolutionPriority() {
        return solutionPriority;
    }

    /**
     * Sets the value of the solutionPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolutionPriority(String value) {
        this.solutionPriority = value;
    }

    /**
     * Gets the value of the solutionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolutionName() {
        return solutionName;
    }

    /**
     * Sets the value of the solutionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolutionName(String value) {
        this.solutionName = value;
    }

    /**
     * Gets the value of the addToScope property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAddToScope() {
        return addToScope;
    }

    /**
     * Sets the value of the addToScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAddToScope(Boolean value) {
        this.addToScope = value;
    }

}
