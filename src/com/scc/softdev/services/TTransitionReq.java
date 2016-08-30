
package com.scc.softdev.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tTransitionReq complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tTransitionReq">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tObject">
 *       &lt;sequence>
 *         &lt;element name="FieldOrder" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="FieldState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UserFieldState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FieldStates" type="{http://services.softdev.scc.com}fieldState" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="UserFieldStates" type="{http://services.softdev.scc.com}userFieldState" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="PostActivity" type="{http://services.softdev.scc.com}tPostActivity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Projects" type="{http://services.softdev.scc.com}tProject" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tTransitionReq", propOrder = {
    "fieldOrder",
    "fieldState",
    "userFieldState",
    "fieldStates",
    "userFieldStates",
    "postActivity",
    "projects"
})
public class TTransitionReq
    extends TObject
{

    @XmlElement(name = "FieldOrder", nillable = true)
    protected List<String> fieldOrder;
    @XmlElement(name = "FieldState")
    protected String fieldState;
    @XmlElement(name = "UserFieldState")
    protected String userFieldState;
    @XmlElement(name = "FieldStates", nillable = true)
    protected List<FieldState> fieldStates;
    @XmlElement(name = "UserFieldStates", nillable = true)
    protected List<UserFieldState> userFieldStates;
    @XmlElement(name = "PostActivity", nillable = true)
    protected List<TPostActivity> postActivity;
    @XmlElement(name = "Projects", nillable = true)
    protected List<TProject> projects;

    /**
     * Gets the value of the fieldOrder property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fieldOrder property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFieldOrder().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFieldOrder() {
        if (fieldOrder == null) {
            fieldOrder = new ArrayList<String>();
        }
        return this.fieldOrder;
    }

    /**
     * Gets the value of the fieldState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldState() {
        return fieldState;
    }

    /**
     * Sets the value of the fieldState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldState(String value) {
        this.fieldState = value;
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

    /**
     * Gets the value of the postActivity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the postActivity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TPostActivity }
     * 
     * 
     */
    public List<TPostActivity> getPostActivity() {
        if (postActivity == null) {
            postActivity = new ArrayList<TPostActivity>();
        }
        return this.postActivity;
    }

    /**
     * Gets the value of the projects property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the projects property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProjects().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TProject }
     * 
     * 
     */
    public List<TProject> getProjects() {
        if (projects == null) {
            projects = new ArrayList<TProject>();
        }
        return this.projects;
    }

}
