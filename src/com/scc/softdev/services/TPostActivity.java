
package com.scc.softdev.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tPostActivity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tPostActivity">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tObject">
 *       &lt;sequence>
 *         &lt;element name="ActivityTypeID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="ActivityType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Group" type="{http://services.softdev.scc.com}tGroups" minOccurs="0"/>
 *         &lt;element name="Users" type="{http://services.softdev.scc.com}tUsers" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="EstimatedTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tPostActivity", propOrder = {
    "activityTypeID",
    "activityType",
    "group",
    "users",
    "estimatedTime"
})
public class TPostActivity
    extends TObject
{

    @XmlElement(name = "ActivityTypeID")
    protected Long activityTypeID;
    @XmlElement(name = "ActivityType")
    protected String activityType;
    @XmlElement(name = "Group")
    protected TGroups group;
    @XmlElement(name = "Users", nillable = true)
    protected List<TUsers> users;
    @XmlElement(name = "EstimatedTime")
    protected Long estimatedTime;

    /**
     * Gets the value of the activityTypeID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getActivityTypeID() {
        return activityTypeID;
    }

    /**
     * Sets the value of the activityTypeID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setActivityTypeID(Long value) {
        this.activityTypeID = value;
    }

    /**
     * Gets the value of the activityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityType() {
        return activityType;
    }

    /**
     * Sets the value of the activityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityType(String value) {
        this.activityType = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link TGroups }
     *     
     */
    public TGroups getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link TGroups }
     *     
     */
    public void setGroup(TGroups value) {
        this.group = value;
    }

    /**
     * Gets the value of the users property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the users property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TUsers }
     * 
     * 
     */
    public List<TUsers> getUsers() {
        if (users == null) {
            users = new ArrayList<TUsers>();
        }
        return this.users;
    }

    /**
     * Gets the value of the estimatedTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEstimatedTime() {
        return estimatedTime;
    }

    /**
     * Sets the value of the estimatedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEstimatedTime(Long value) {
        this.estimatedTime = value;
    }

}
