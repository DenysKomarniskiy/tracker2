
package com.scc.softdev.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for tTestCase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tTestCase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tObject">
 *       &lt;sequence>
 *         &lt;element name="UFI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="IsSystem" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="TestId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Steps" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Responsible" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CreatedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ModifiedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="SubjectId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="OwnerId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="TestRecStream" type="{http://services.softdev.scc.com}tTestRecStream" minOccurs="0"/>
 *         &lt;element name="UserField" type="{http://services.softdev.scc.com}userField" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TestStep" type="{http://services.softdev.scc.com}tTestStep" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TestRun" type="{http://services.softdev.scc.com}tTestRun" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tTestCase", propOrder = {
    "ufi",
    "productId",
    "isSystem",
    "testId",
    "name",
    "status",
    "type",
    "steps",
    "description",
    "responsible",
    "createdOn",
    "modifiedOn",
    "subjectId",
    "ownerId",
    "testRecStream",
    "userField",
    "testStep",
    "testRun"
})
public class TTestCase
    extends TObject
{

    @XmlElement(name = "UFI")
    protected String ufi;
    @XmlElement(name = "ProductId")
    protected Long productId;
    @XmlElement(name = "IsSystem")
    protected Boolean isSystem;
    @XmlElement(name = "TestId")
    protected Long testId;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "Type")
    protected String type;
    @XmlElement(name = "Steps")
    protected Long steps;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "Responsible")
    protected String responsible;
    @XmlElement(name = "CreatedOn")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdOn;
    @XmlElement(name = "ModifiedOn")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar modifiedOn;
    @XmlElement(name = "SubjectId")
    protected Long subjectId;
    @XmlElement(name = "OwnerId")
    protected Long ownerId;
    @XmlElement(name = "TestRecStream")
    protected TTestRecStream testRecStream;
    @XmlElement(name = "UserField", nillable = true)
    protected List<UserField> userField;
    @XmlElement(name = "TestStep", nillable = true)
    protected List<TTestStep> testStep;
    @XmlElement(name = "TestRun", nillable = true)
    protected List<TTestRun> testRun;

    /**
     * Gets the value of the ufi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUFI() {
        return ufi;
    }

    /**
     * Sets the value of the ufi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUFI(String value) {
        this.ufi = value;
    }

    /**
     * Gets the value of the productId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductId(Long value) {
        this.productId = value;
    }

    /**
     * Gets the value of the isSystem property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsSystem() {
        return isSystem;
    }

    /**
     * Sets the value of the isSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsSystem(Boolean value) {
        this.isSystem = value;
    }

    /**
     * Gets the value of the testId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTestId() {
        return testId;
    }

    /**
     * Sets the value of the testId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTestId(Long value) {
        this.testId = value;
    }

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
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
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
     * Gets the value of the steps property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSteps() {
        return steps;
    }

    /**
     * Sets the value of the steps property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSteps(Long value) {
        this.steps = value;
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
     * Gets the value of the responsible property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsible() {
        return responsible;
    }

    /**
     * Sets the value of the responsible property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsible(String value) {
        this.responsible = value;
    }

    /**
     * Gets the value of the createdOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedOn() {
        return createdOn;
    }

    /**
     * Sets the value of the createdOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedOn(XMLGregorianCalendar value) {
        this.createdOn = value;
    }

    /**
     * Gets the value of the modifiedOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getModifiedOn() {
        return modifiedOn;
    }

    /**
     * Sets the value of the modifiedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setModifiedOn(XMLGregorianCalendar value) {
        this.modifiedOn = value;
    }

    /**
     * Gets the value of the subjectId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSubjectId() {
        return subjectId;
    }

    /**
     * Sets the value of the subjectId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubjectId(Long value) {
        this.subjectId = value;
    }

    /**
     * Gets the value of the ownerId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the value of the ownerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOwnerId(Long value) {
        this.ownerId = value;
    }

    /**
     * Gets the value of the testRecStream property.
     * 
     * @return
     *     possible object is
     *     {@link TTestRecStream }
     *     
     */
    public TTestRecStream getTestRecStream() {
        return testRecStream;
    }

    /**
     * Sets the value of the testRecStream property.
     * 
     * @param value
     *     allowed object is
     *     {@link TTestRecStream }
     *     
     */
    public void setTestRecStream(TTestRecStream value) {
        this.testRecStream = value;
    }

    /**
     * Gets the value of the userField property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userField property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserField().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserField }
     * 
     * 
     */
    public List<UserField> getUserField() {
        if (userField == null) {
            userField = new ArrayList<UserField>();
        }
        return this.userField;
    }

    /**
     * Gets the value of the testStep property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the testStep property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTestStep().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TTestStep }
     * 
     * 
     */
    public List<TTestStep> getTestStep() {
        if (testStep == null) {
            testStep = new ArrayList<TTestStep>();
        }
        return this.testStep;
    }

    /**
     * Gets the value of the testRun property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the testRun property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTestRun().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TTestRun }
     * 
     * 
     */
    public List<TTestRun> getTestRun() {
        if (testRun == null) {
            testRun = new ArrayList<TTestRun>();
        }
        return this.testRun;
    }

}
