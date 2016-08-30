
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
 * <p>Java class for tTestRun complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tTestRun">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tObject">
 *       &lt;sequence>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExecutionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Tester" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Host" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Duration" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="TestSetId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="TestCaseId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="UserField" type="{http://services.softdev.scc.com}userField" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="LinkedIssue" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tTestRun", propOrder = {
    "name",
    "status",
    "executionDate",
    "tester",
    "host",
    "path",
    "duration",
    "testSetId",
    "testCaseId",
    "userField",
    "linkedIssue"
})
public class TTestRun
    extends TObject
{

    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "ExecutionDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar executionDate;
    @XmlElement(name = "Tester")
    protected String tester;
    @XmlElement(name = "Host")
    protected String host;
    @XmlElement(name = "Path")
    protected String path;
    @XmlElement(name = "Duration")
    protected Long duration;
    @XmlElement(name = "TestSetId")
    protected Long testSetId;
    @XmlElement(name = "TestCaseId")
    protected Long testCaseId;
    @XmlElement(name = "UserField", nillable = true)
    protected List<UserField> userField;
    @XmlElement(name = "LinkedIssue", nillable = true)
    protected List<Long> linkedIssue;

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
     * Gets the value of the executionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExecutionDate() {
        return executionDate;
    }

    /**
     * Sets the value of the executionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExecutionDate(XMLGregorianCalendar value) {
        this.executionDate = value;
    }

    /**
     * Gets the value of the tester property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTester() {
        return tester;
    }

    /**
     * Sets the value of the tester property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTester(String value) {
        this.tester = value;
    }

    /**
     * Gets the value of the host property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the value of the host property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPath(String value) {
        this.path = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDuration(Long value) {
        this.duration = value;
    }

    /**
     * Gets the value of the testSetId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTestSetId() {
        return testSetId;
    }

    /**
     * Sets the value of the testSetId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTestSetId(Long value) {
        this.testSetId = value;
    }

    /**
     * Gets the value of the testCaseId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTestCaseId() {
        return testCaseId;
    }

    /**
     * Sets the value of the testCaseId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTestCaseId(Long value) {
        this.testCaseId = value;
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
     * Gets the value of the linkedIssue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the linkedIssue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkedIssue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getLinkedIssue() {
        if (linkedIssue == null) {
            linkedIssue = new ArrayList<Long>();
        }
        return this.linkedIssue;
    }

}
