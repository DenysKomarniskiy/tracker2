
package com.scc.softdev.services;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tRequiredActivity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tRequiredActivity">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tObject">
 *       &lt;sequence>
 *         &lt;element name="RequiredStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RequiredQuantity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Activity" type="{http://services.softdev.scc.com}tActivity" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tRequiredActivity", propOrder = {
    "requiredStatus",
    "requiredQuantity",
    "activity"
})
public class TRequiredActivity
    extends TObject
{

    @XmlElement(name = "RequiredStatus")
    protected String requiredStatus;
    @XmlElement(name = "RequiredQuantity")
    protected String requiredQuantity;
    @XmlElement(name = "Activity", nillable = true)
    protected List<TActivity> activity;

    /**
     * Gets the value of the requiredStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequiredStatus() {
        return requiredStatus;
    }

    /**
     * Sets the value of the requiredStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequiredStatus(String value) {
        this.requiredStatus = value;
    }

    /**
     * Gets the value of the requiredQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequiredQuantity() {
        return requiredQuantity;
    }

    /**
     * Sets the value of the requiredQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequiredQuantity(String value) {
        this.requiredQuantity = value;
    }

    /**
     * Gets the value of the activity property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the activity property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActivity().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TActivity }
     * 
     * 
     */
    public List<TActivity> getActivity() {
        if (activity == null) {
            activity = new ArrayList<TActivity>();
        }
        return this.activity;
    }

}
