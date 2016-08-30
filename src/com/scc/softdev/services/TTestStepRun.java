
package com.scc.softdev.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tTestStepRun complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tTestStepRun">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tTestStep">
 *       &lt;sequence>
 *         &lt;element name="RunId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ActualResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tTestStepRun", propOrder = {
    "runId",
    "status",
    "actualResult"
})
public class TTestStepRun
    extends TTestStep
{

    @XmlElement(name = "RunId")
    protected Long runId;
    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "ActualResult")
    protected String actualResult;

    /**
     * Gets the value of the runId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRunId() {
        return runId;
    }

    /**
     * Sets the value of the runId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRunId(Long value) {
        this.runId = value;
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
     * Gets the value of the actualResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActualResult() {
        return actualResult;
    }

    /**
     * Sets the value of the actualResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActualResult(String value) {
        this.actualResult = value;
    }

}
