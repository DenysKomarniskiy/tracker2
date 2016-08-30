
package com.scc.softdev.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tTransition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tTransition">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tStatefulObject">
 *       &lt;sequence>
 *         &lt;element name="NextEntityStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PrevEntityStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RequiredActivity" type="{http://services.softdev.scc.com}tRequiredActivity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="TrEntityType" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DefComment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsActive" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="NextAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="NextProcess" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="PostActMode" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="PrevProcess" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://services.softdev.scc.com}transitionType" minOccurs="0"/>
 *         &lt;element name="FieldsState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserFieldState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FieldStates" type="{http://services.softdev.scc.com}fieldState" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="UserFieldStates" type="{http://services.softdev.scc.com}userFieldState" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tTransition", propOrder = {
    "nextEntityStatus",
    "prevEntityStatus",
    "description",
    "requiredActivity",
    "trEntityType",
    "defComment",
    "isActive",
    "nextAction",
    "nextProcess",
    "postActMode",
    "prevProcess",
    "type",
    "fieldsState",
    "userFieldState",
    "fieldStates",
    "userFieldStates"
})
public class TTransition
    extends TStatefulObject
{

    @XmlElement(name = "NextEntityStatus")
    protected String nextEntityStatus;
    @XmlElement(name = "PrevEntityStatus")
    protected String prevEntityStatus;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "RequiredActivity", nillable = true)
    protected List<TRequiredActivity> requiredActivity;
    @XmlElement(name = "TrEntityType")
    protected Long trEntityType;
    @XmlElement(name = "DefComment")
    protected String defComment;
    @XmlElement(name = "IsActive")
    protected Boolean isActive;
    @XmlElement(name = "NextAction")
    protected String nextAction;
    @XmlElement(name = "NextProcess")
    protected Long nextProcess;
    @XmlElement(name = "PostActMode")
    protected Long postActMode;
    @XmlElement(name = "PrevProcess")
    protected Long prevProcess;
    @XmlElement(name = "Type")
    @XmlSchemaType(name = "string")
    protected TransitionType type;
    @XmlElement(name = "FieldsState")
    protected String fieldsState;
    @XmlElement(name = "UserFieldState")
    protected String userFieldState;
    @XmlElement(name = "FieldStates", nillable = true)
    protected List<FieldState> fieldStates;
    @XmlElement(name = "UserFieldStates", nillable = true)
    protected List<UserFieldState> userFieldStates;

    /**
     * Gets the value of the nextEntityStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextEntityStatus() {
        return nextEntityStatus;
    }

    /**
     * Sets the value of the nextEntityStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextEntityStatus(String value) {
        this.nextEntityStatus = value;
    }

    /**
     * Gets the value of the prevEntityStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrevEntityStatus() {
        return prevEntityStatus;
    }

    /**
     * Sets the value of the prevEntityStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrevEntityStatus(String value) {
        this.prevEntityStatus = value;
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
     * Gets the value of the requiredActivity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requiredActivity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequiredActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TRequiredActivity }
     * 
     * 
     */
    public List<TRequiredActivity> getRequiredActivity() {
        if (requiredActivity == null) {
            requiredActivity = new ArrayList<TRequiredActivity>();
        }
        return this.requiredActivity;
    }

    /**
     * Gets the value of the trEntityType property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTrEntityType() {
        return trEntityType;
    }

    /**
     * Sets the value of the trEntityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTrEntityType(Long value) {
        this.trEntityType = value;
    }

    /**
     * Gets the value of the defComment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefComment() {
        return defComment;
    }

    /**
     * Sets the value of the defComment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefComment(String value) {
        this.defComment = value;
    }

    /**
     * Gets the value of the isActive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsActive(Boolean value) {
        this.isActive = value;
    }

    /**
     * Gets the value of the nextAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNextAction() {
        return nextAction;
    }

    /**
     * Sets the value of the nextAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNextAction(String value) {
        this.nextAction = value;
    }

    /**
     * Gets the value of the nextProcess property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getNextProcess() {
        return nextProcess;
    }

    /**
     * Sets the value of the nextProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setNextProcess(Long value) {
        this.nextProcess = value;
    }

    /**
     * Gets the value of the postActMode property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPostActMode() {
        return postActMode;
    }

    /**
     * Sets the value of the postActMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPostActMode(Long value) {
        this.postActMode = value;
    }

    /**
     * Gets the value of the prevProcess property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPrevProcess() {
        return prevProcess;
    }

    /**
     * Sets the value of the prevProcess property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPrevProcess(Long value) {
        this.prevProcess = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link TransitionType }
     *     
     */
    public TransitionType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransitionType }
     *     
     */
    public void setType(TransitionType value) {
        this.type = value;
    }

    /**
     * Gets the value of the fieldsState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldsState() {
        return fieldsState;
    }

    /**
     * Sets the value of the fieldsState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldsState(String value) {
        this.fieldsState = value;
    }

    /**
     * Gets the value of the userFieldState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserFieldState() {
        return userFieldState;
    }

    /**
     * Sets the value of the userFieldState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserFieldState(String value) {
        this.userFieldState = value;
    }

    /**
     * Gets the value of the fieldStates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fieldStates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFieldStates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FieldState }
     * 
     * 
     */
    public List<FieldState> getFieldStates() {
        if (fieldStates == null) {
            fieldStates = new ArrayList<FieldState>();
        }
        return this.fieldStates;
    }

    /**
     * Gets the value of the userFieldStates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userFieldStates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserFieldStates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserFieldState }
     * 
     * 
     */
    public List<UserFieldState> getUserFieldStates() {
        if (userFieldStates == null) {
            userFieldStates = new ArrayList<UserFieldState>();
        }
        return this.userFieldStates;
    }

}
