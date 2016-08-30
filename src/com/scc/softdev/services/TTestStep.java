
package com.scc.softdev.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tTestStep complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tTestStep">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tObject">
 *       &lt;sequence>
 *         &lt;element name="StepId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="StepOrder" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExpectedResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserField" type="{http://services.softdev.scc.com}userField" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tTestStep", propOrder = {
    "stepId",
    "stepOrder",
    "name",
    "description",
    "expectedResult",
    "userField"
})
@XmlSeeAlso({
    TTestStepRun.class
})
public class TTestStep
    extends TObject
{

    @XmlElement(name = "StepId")
    protected Long stepId;
    @XmlElement(name = "StepOrder")
    protected Long stepOrder;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "ExpectedResult")
    protected String expectedResult;
    @XmlElement(name = "UserField", nillable = true)
    protected List<UserField> userField;

    /**
     * Gets the value of the stepId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStepId() {
        return stepId;
    }

    /**
     * Sets the value of the stepId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStepId(Long value) {
        this.stepId = value;
    }

    /**
     * Gets the value of the stepOrder property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStepOrder() {
        return stepOrder;
    }

    /**
     * Sets the value of the stepOrder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStepOrder(Long value) {
        this.stepOrder = value;
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
     * Gets the value of the expectedResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExpectedResult() {
        return expectedResult;
    }

    /**
     * Sets the value of the expectedResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpectedResult(String value) {
        this.expectedResult = value;
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

}
