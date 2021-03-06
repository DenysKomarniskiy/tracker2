
package com.scc.softdev.services.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import com.scc.softdev.services.TClientArray;
import com.scc.softdev.services.TComponentEntityArray;
import com.scc.softdev.services.TEntityTracking;
import com.scc.softdev.services.TEntityTrackingArray;
import com.scc.softdev.services.TIssue;
import com.scc.softdev.services.TIssueArray;
import com.scc.softdev.services.TNewActivityArray;
import com.scc.softdev.services.TPostActivityArray;
import com.scc.softdev.services.TTransitionArray;
import com.scc.softdev.services.TTransitionReq;
import net.java.dev.jaxb.array.LongArray;
import net.java.dev.jaxb.array.StringArray;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SoftDev_Issue", targetNamespace = "http://services.softdev.scc.com")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    com.scc.softdev.services.ObjectFactory.class,
    net.java.dev.jaxb.array.ObjectFactory.class
})
public interface SoftDevIssue {


    /**
     * 
     * @param issueID
     * @param mitigationComm
     * @return
     *     returns boolean
     * @throws SDException
     */
    @WebMethod(operationName = "UpdateMitigationComment")
    @WebResult(partName = "return")
    public boolean updateMitigationComment(
        @WebParam(name = "issueID", partName = "issueID")
        long issueID,
        @WebParam(name = "MitigationComm", partName = "MitigationComm")
        String mitigationComm)
        throws SDException
    ;

    /**
     * 
     * @param activityID
     * @return
     *     returns com.scc.softdev.services.TIssueArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetByRelatedActivity")
    @WebResult(partName = "return")
    public TIssueArray getByRelatedActivity(
        @WebParam(name = "activityID", partName = "activityID")
        long activityID)
        throws SDException
    ;

    /**
     * 
     * @param arrayIssueID
     * @return
     *     returns com.scc.softdev.services.TIssueArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetIssues")
    @WebResult(partName = "return")
    public TIssueArray getIssues(
        @WebParam(name = "arrayIssueID", partName = "arrayIssueID")
        LongArray arrayIssueID)
        throws SDException
    ;

    /**
     * 
     * @return
     *     returns long
     * @throws SDException
     */
    @WebMethod(operationName = "GetNextSolutionId")
    @WebResult(partName = "return")
    public long getNextSolutionId()
        throws SDException
    ;

    /**
     * 
     * @param productID
     * @param fieldNumber
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetUserFieldItemsByProduct")
    @WebResult(partName = "return")
    public StringArray getUserFieldItemsByProduct(
        @WebParam(name = "productID", partName = "productID")
        long productID,
        @WebParam(name = "fieldNumber", partName = "fieldNumber")
        long fieldNumber)
        throws SDException
    ;

    /**
     * 
     * @param productID
     * @return
     *     returns com.scc.softdev.services.TTransitionReq
     * @throws SDException
     */
    @WebMethod(operationName = "GetRegisterReq")
    @WebResult(partName = "return")
    public TTransitionReq getRegisterReq(
        @WebParam(name = "productID", partName = "productID")
        long productID)
        throws SDException
    ;

    /**
     * 
     * @param issueID
     * @return
     *     returns com.scc.softdev.services.TEntityTrackingArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetTracking")
    @WebResult(partName = "return")
    public TEntityTrackingArray getTracking(
        @WebParam(name = "issueID", partName = "issueID")
        long issueID)
        throws SDException
    ;

    /**
     * 
     * @param issueID
     * @param detailedProbDesc
     * @return
     *     returns boolean
     * @throws SDException
     */
    @WebMethod(operationName = "UpdateDetailedProblemDescription")
    @WebResult(partName = "return")
    public boolean updateDetailedProblemDescription(
        @WebParam(name = "issueID", partName = "issueID")
        long issueID,
        @WebParam(name = "DetailedProbDesc", partName = "DetailedProbDesc")
        String detailedProbDesc)
        throws SDException
    ;

    /**
     * 
     * @param issue
     * @param comment
     * @param arrayNewActivity
     * @return
     *     returns long
     * @throws SDException
     */
    @WebMethod(operationName = "Register")
    @WebResult(partName = "return")
    public long register(
        @WebParam(name = "issue", partName = "issue")
        TIssue issue,
        @WebParam(name = "comment", partName = "comment")
        String comment,
        @WebParam(name = "arrayNewActivity", partName = "arrayNewActivity")
        TNewActivityArray arrayNewActivity)
        throws SDException
    ;

    /**
     * 
     * @param issue
     * @return
     *     returns com.scc.softdev.services.TPostActivityArray
     * @throws SDException
     */
    @WebMethod(operationName = "RegisterTestCall")
    @WebResult(partName = "return")
    public TPostActivityArray registerTestCall(
        @WebParam(name = "issue", partName = "issue")
        TIssue issue)
        throws SDException
    ;

    /**
     * 
     * @param arrayIssueUFI
     * @return
     *     returns com.scc.softdev.services.TIssueArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetIssuesByUFI")
    @WebResult(partName = "return")
    public TIssueArray getIssuesByUFI(
        @WebParam(name = "arrayIssueUFI", partName = "arrayIssueUFI")
        StringArray arrayIssueUFI)
        throws SDException
    ;

    /**
     * 
     * @return
     *     returns com.scc.softdev.services.TClientArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetClient")
    @WebResult(partName = "return")
    public TClientArray getClient()
        throws SDException
    ;

    /**
     * 
     * @param arrayComponentEntity
     * @return
     *     returns com.scc.softdev.services.TIssueArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetRelatedIssue")
    @WebResult(partName = "return")
    public TIssueArray getRelatedIssue(
        @WebParam(name = "arrayComponentEntity", partName = "arrayComponentEntity")
        TComponentEntityArray arrayComponentEntity)
        throws SDException
    ;

    /**
     * 
     * @param issueID
     * @return
     *     returns com.scc.softdev.services.TEntityTracking
     * @throws SDException
     */
    @WebMethod(operationName = "GetLastTracking")
    @WebResult(partName = "return")
    public TEntityTracking getLastTracking(
        @WebParam(name = "issueID", partName = "issueID")
        long issueID)
        throws SDException
    ;

    /**
     * 
     * @param issue
     * @param transitionID
     * @return
     *     returns com.scc.softdev.services.TPostActivityArray
     * @throws SDException
     */
    @WebMethod(operationName = "PerformTransitionTestCall")
    @WebResult(partName = "return")
    public TPostActivityArray performTransitionTestCall(
        @WebParam(name = "issue", partName = "issue")
        TIssue issue,
        @WebParam(name = "transitionID", partName = "transitionID")
        long transitionID)
        throws SDException
    ;

    /**
     * 
     * @param issueID
     * @param comment
     * @return
     *     returns long
     * @throws SDException
     */
    @WebMethod(operationName = "AddComment")
    @WebResult(partName = "return")
    public long addComment(
        @WebParam(name = "issueID", partName = "issueID")
        long issueID,
        @WebParam(name = "comment", partName = "comment")
        String comment)
        throws SDException
    ;

    /**
     * 
     * @param issue
     * @param closeAllReqAct
     * @param transitionID
     * @param comment
     * @param arrayNewActivity
     * @return
     *     returns boolean
     * @throws SDException
     */
    @WebMethod(operationName = "PerformTransition")
    @WebResult(partName = "return")
    public boolean performTransition(
        @WebParam(name = "issue", partName = "issue")
        TIssue issue,
        @WebParam(name = "transitionID", partName = "transitionID")
        long transitionID,
        @WebParam(name = "comment", partName = "comment")
        String comment,
        @WebParam(name = "arrayNewActivity", partName = "arrayNewActivity")
        TNewActivityArray arrayNewActivity,
        @WebParam(name = "closeAllReqAct", partName = "closeAllReqAct")
        boolean closeAllReqAct)
        throws SDException
    ;

    /**
     * 
     * @param projectID
     * @return
     *     returns com.scc.softdev.services.TIssueArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetIssuesByProjectID")
    @WebResult(partName = "return")
    public TIssueArray getIssuesByProjectID(
        @WebParam(name = "projectID", partName = "projectID")
        long projectID)
        throws SDException
    ;

    /**
     * 
     * @param issueID
     * @param domainExpComm
     * @return
     *     returns boolean
     * @throws SDException
     */
    @WebMethod(operationName = "UpdateDomainExpertComment")
    @WebResult(partName = "return")
    public boolean updateDomainExpertComment(
        @WebParam(name = "issueID", partName = "issueID")
        long issueID,
        @WebParam(name = "DomainExpComm", partName = "DomainExpComm")
        String domainExpComm)
        throws SDException
    ;

    /**
     * 
     * @param fieldName
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetFieldItems")
    @WebResult(partName = "return")
    public StringArray getFieldItems(
        @WebParam(name = "fieldName", partName = "fieldName")
        String fieldName)
        throws SDException
    ;

    /**
     * 
     * @param issueID
     * @param transitionID
     * @return
     *     returns com.scc.softdev.services.TTransitionReq
     * @throws SDException
     */
    @WebMethod(operationName = "GetTransitionReq")
    @WebResult(partName = "return")
    public TTransitionReq getTransitionReq(
        @WebParam(name = "issueID", partName = "issueID")
        long issueID,
        @WebParam(name = "transitionID", partName = "transitionID")
        long transitionID)
        throws SDException
    ;

    /**
     * 
     * @param itemName
     * @return
     *     returns java.lang.String
     * @throws SDException
     */
    @WebMethod(operationName = "DecodeUserFieldItem")
    @WebResult(partName = "return")
    public String decodeUserFieldItem(
        @WebParam(name = "ItemName", partName = "ItemName")
        String itemName)
        throws SDException
    ;

    /**
     * 
     * @param issueID
     * @param fieldNumber
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetUserFieldItems")
    @WebResult(partName = "return")
    public StringArray getUserFieldItems(
        @WebParam(name = "issueID", partName = "issueID")
        long issueID,
        @WebParam(name = "fieldNumber", partName = "fieldNumber")
        long fieldNumber)
        throws SDException
    ;

    /**
     * 
     * @param issueID
     * @return
     *     returns com.scc.softdev.services.TTransitionArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetAvailbleTransition")
    @WebResult(partName = "return")
    public TTransitionArray getAvailbleTransition(
        @WebParam(name = "issueID", partName = "issueID")
        long issueID)
        throws SDException
    ;

    /**
     * 
     * @param issueID
     * @param detailedProbDesc
     * @param mitigationComm
     * @param domainExpComm
     * @return
     *     returns boolean
     * @throws SDException
     */
    @WebMethod(operationName = "UpdateDetailedProblemDescriptions")
    @WebResult(partName = "return")
    public boolean updateDetailedProblemDescriptions(
        @WebParam(name = "issueID", partName = "issueID")
        long issueID,
        @WebParam(name = "DetailedProbDesc", partName = "DetailedProbDesc")
        String detailedProbDesc,
        @WebParam(name = "DomainExpComm", partName = "DomainExpComm")
        String domainExpComm,
        @WebParam(name = "MitigationComm", partName = "MitigationComm")
        String mitigationComm)
        throws SDException
    ;

}
