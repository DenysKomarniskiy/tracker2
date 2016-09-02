
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
 * <p>Java class for tIssue complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tIssue">
 *   &lt;complexContent>
 *     &lt;extension base="{http://services.softdev.scc.com}tObject">
 *       &lt;sequence>
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsActive" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RegisteredByID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="RegisteredOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Priority" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CCPLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CMTarget" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IsMultimodule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EvalChecklist" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HazardAnalysis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RootAnalysis" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Frequency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Attachment" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Link" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LocalConstr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="EvaluationDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="HleReqTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="HleDesTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="HleCodTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="HleTcdTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="HleTstTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="IssueOldID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="UFI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ProductID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="IssueType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Severity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Risk" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DetectionDevLineID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="DetectionVersionID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="Summary" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IssueSource" type="{http://services.softdev.scc.com}tIssueSource" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="ClientEnv" type="{http://services.softdev.scc.com}tClientEnv" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="IssueSolution" type="{http://services.softdev.scc.com}tIssueSolution" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="DevlineEval" type="{http://services.softdev.scc.com}tIssueDevlineEval" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "tIssue", propOrder = {
    "status",
    "isActive",
    "registeredByID",
    "registeredOn",
    "priority",
    "ccpLevel",
    "cmTarget",
    "isMultimodule",
    "evalChecklist",
    "hazardAnalysis",
    "rootAnalysis",
    "frequency",
    "attachment",
    "link",
    "localConstr",
    "evaluationDescription",
    "hleReqTime",
    "hleDesTime",
    "hleCodTime",
    "hleTcdTime",
    "hleTstTime",
    "issueOldID",
    "ufi",
    "productID",
    "issueType",
    "severity",
    "risk",
    "detectionDevLineID",
    "detectionVersionID",
    "summary",
    "description",
    "issueSource",
    "clientEnv",
    "issueSolution",
    "devlineEval",
    "userField"
})
public class TIssue
    extends TObject
{

    @XmlElement(name = "Status")
    protected String status;
    @XmlElement(name = "IsActive")
    protected String isActive;
    @XmlElement(name = "RegisteredByID")
    protected Long registeredByID;
    @XmlElement(name = "RegisteredOn")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar registeredOn;
    @XmlElement(name = "Priority")
    protected String priority;
    @XmlElement(name = "CCPLevel")
    protected String ccpLevel;
    @XmlElement(name = "CMTarget")
    protected String cmTarget;
    @XmlElement(name = "IsMultimodule")
    protected String isMultimodule;
    @XmlElement(name = "EvalChecklist")
    protected String evalChecklist;
    @XmlElement(name = "HazardAnalysis")
    protected String hazardAnalysis;
    @XmlElement(name = "RootAnalysis")
    protected String rootAnalysis;
    @XmlElement(name = "Frequency")
    protected String frequency;
    @XmlElement(name = "Attachment")
    protected String attachment;
    @XmlElement(name = "Link")
    protected String link;
    @XmlElement(name = "LocalConstr")
    protected String localConstr;
    @XmlElement(name = "EvaluationDescription")
    protected String evaluationDescription;
    @XmlElement(name = "HleReqTime")
    protected Long hleReqTime;
    @XmlElement(name = "HleDesTime")
    protected Long hleDesTime;
    @XmlElement(name = "HleCodTime")
    protected Long hleCodTime;
    @XmlElement(name = "HleTcdTime")
    protected Long hleTcdTime;
    @XmlElement(name = "HleTstTime")
    protected Long hleTstTime;
    @XmlElement(name = "IssueOldID")
    protected Long issueOldID;
    @XmlElement(name = "UFI")
    protected String ufi;
    @XmlElement(name = "ProductID")
    protected Long productID;
    @XmlElement(name = "IssueType")
    protected String issueType;
    @XmlElement(name = "Severity")
    protected String severity;
    @XmlElement(name = "Risk")
    protected String risk;
    @XmlElement(name = "DetectionDevLineID")
    protected Long detectionDevLineID;
    @XmlElement(name = "DetectionVersionID")
    protected Long detectionVersionID;
    @XmlElement(name = "Summary")
    protected String summary;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "IssueSource", nillable = true)
    protected List<TIssueSource> issueSource;
    @XmlElement(name = "ClientEnv", nillable = true)
    protected List<TClientEnv> clientEnv;
    @XmlElement(name = "IssueSolution", nillable = true)
    protected List<TIssueSolution> issueSolution;
    @XmlElement(name = "DevlineEval", nillable = true)
    protected List<TIssueDevlineEval> devlineEval;
    @XmlElement(name = "UserField", nillable = true)
    protected List<UserField> userField;

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
     * Gets the value of the isActive property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsActive() {
        return isActive;
    }

    /**
     * Sets the value of the isActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsActive(String value) {
        this.isActive = value;
    }

    /**
     * Gets the value of the registeredByID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRegisteredByID() {
        return registeredByID;
    }

    /**
     * Sets the value of the registeredByID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRegisteredByID(Long value) {
        this.registeredByID = value;
    }

    /**
     * Gets the value of the registeredOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRegisteredOn() {
        return registeredOn;
    }

    /**
     * Sets the value of the registeredOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRegisteredOn(XMLGregorianCalendar value) {
        this.registeredOn = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPriority(String value) {
        this.priority = value;
    }

    /**
     * Gets the value of the ccpLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCCPLevel() {
        return ccpLevel;
    }

    /**
     * Sets the value of the ccpLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCCPLevel(String value) {
        this.ccpLevel = value;
    }

    /**
     * Gets the value of the cmTarget property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCMTarget() {
        return cmTarget;
    }

    /**
     * Sets the value of the cmTarget property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCMTarget(String value) {
        this.cmTarget = value;
    }

    /**
     * Gets the value of the isMultimodule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsMultimodule() {
        return isMultimodule;
    }

    /**
     * Sets the value of the isMultimodule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsMultimodule(String value) {
        this.isMultimodule = value;
    }

    /**
     * Gets the value of the evalChecklist property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvalChecklist() {
        return evalChecklist;
    }

    /**
     * Sets the value of the evalChecklist property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvalChecklist(String value) {
        this.evalChecklist = value;
    }

    /**
     * Gets the value of the hazardAnalysis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHazardAnalysis() {
        return hazardAnalysis;
    }

    /**
     * Sets the value of the hazardAnalysis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHazardAnalysis(String value) {
        this.hazardAnalysis = value;
    }

    /**
     * Gets the value of the rootAnalysis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRootAnalysis() {
        return rootAnalysis;
    }

    /**
     * Sets the value of the rootAnalysis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRootAnalysis(String value) {
        this.rootAnalysis = value;
    }

    /**
     * Gets the value of the frequency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Sets the value of the frequency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFrequency(String value) {
        this.frequency = value;
    }

    /**
     * Gets the value of the attachment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttachment() {
        return attachment;
    }

    /**
     * Sets the value of the attachment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttachment(String value) {
        this.attachment = value;
    }

    /**
     * Gets the value of the link property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLink() {
        return link;
    }

    /**
     * Sets the value of the link property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLink(String value) {
        this.link = value;
    }

    /**
     * Gets the value of the localConstr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalConstr() {
        return localConstr;
    }

    /**
     * Sets the value of the localConstr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalConstr(String value) {
        this.localConstr = value;
    }

    /**
     * Gets the value of the evaluationDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvaluationDescription() {
        return evaluationDescription;
    }

    /**
     * Sets the value of the evaluationDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvaluationDescription(String value) {
        this.evaluationDescription = value;
    }

    /**
     * Gets the value of the hleReqTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHleReqTime() {
        return hleReqTime;
    }

    /**
     * Sets the value of the hleReqTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHleReqTime(Long value) {
        this.hleReqTime = value;
    }

    /**
     * Gets the value of the hleDesTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHleDesTime() {
        return hleDesTime;
    }

    /**
     * Sets the value of the hleDesTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHleDesTime(Long value) {
        this.hleDesTime = value;
    }

    /**
     * Gets the value of the hleCodTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHleCodTime() {
        return hleCodTime;
    }

    /**
     * Sets the value of the hleCodTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHleCodTime(Long value) {
        this.hleCodTime = value;
    }

    /**
     * Gets the value of the hleTcdTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHleTcdTime() {
        return hleTcdTime;
    }

    /**
     * Sets the value of the hleTcdTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHleTcdTime(Long value) {
        this.hleTcdTime = value;
    }

    /**
     * Gets the value of the hleTstTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getHleTstTime() {
        return hleTstTime;
    }

    /**
     * Sets the value of the hleTstTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setHleTstTime(Long value) {
        this.hleTstTime = value;
    }

    /**
     * Gets the value of the issueOldID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIssueOldID() {
        return issueOldID;
    }

    /**
     * Sets the value of the issueOldID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIssueOldID(Long value) {
        this.issueOldID = value;
    }

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
     * Gets the value of the productID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductID() {
        return productID;
    }

    /**
     * Sets the value of the productID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductID(Long value) {
        this.productID = value;
    }

    /**
     * Gets the value of the issueType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIssueType() {
        return issueType;
    }

    /**
     * Sets the value of the issueType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIssueType(String value) {
        this.issueType = value;
    }

    /**
     * Gets the value of the severity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeverity() {
        return severity;
    }

    /**
     * Sets the value of the severity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeverity(String value) {
        this.severity = value;
    }

    /**
     * Gets the value of the risk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRisk() {
        return risk;
    }

    /**
     * Sets the value of the risk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRisk(String value) {
        this.risk = value;
    }

    /**
     * Gets the value of the detectionDevLineID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDetectionDevLineID() {
        return detectionDevLineID;
    }

    /**
     * Sets the value of the detectionDevLineID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDetectionDevLineID(Long value) {
        this.detectionDevLineID = value;
    }

    /**
     * Gets the value of the detectionVersionID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDetectionVersionID() {
        return detectionVersionID;
    }

    /**
     * Sets the value of the detectionVersionID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDetectionVersionID(Long value) {
        this.detectionVersionID = value;
    }

    /**
     * Gets the value of the summary property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets the value of the summary property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSummary(String value) {
        this.summary = value;
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
     * Gets the value of the issueSource property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the issueSource property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIssueSource().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TIssueSource }
     * 
     * 
     */
    public List<TIssueSource> getIssueSource() {
        if (issueSource == null) {
            issueSource = new ArrayList<TIssueSource>();
        }
        return this.issueSource;
    }

    /**
     * Gets the value of the clientEnv property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the clientEnv property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClientEnv().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TClientEnv }
     * 
     * 
     */
    public List<TClientEnv> getClientEnv() {
        if (clientEnv == null) {
            clientEnv = new ArrayList<TClientEnv>();
        }
        return this.clientEnv;
    }

    /**
     * Gets the value of the issueSolution property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the issueSolution property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIssueSolution().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TIssueSolution }
     * 
     * 
     */
    public List<TIssueSolution> getIssueSolution() {
        if (issueSolution == null) {
            issueSolution = new ArrayList<TIssueSolution>();
        }
        return this.issueSolution;
    }

    /**
     * Gets the value of the devlineEval property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the devlineEval property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDevlineEval().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TIssueDevlineEval }
     * 
     * 
     */
    public List<TIssueDevlineEval> getDevlineEval() {
        if (devlineEval == null) {
            devlineEval = new ArrayList<TIssueDevlineEval>();
        }
        return this.devlineEval;
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
