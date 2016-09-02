
package com.scc.softdev.services.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import com.scc.softdev.services.TCriteriaArray;
import com.scc.softdev.services.TEntityLinks;
import com.scc.softdev.services.TEntityTrackingArray;
import com.scc.softdev.services.TEntityTypeArray;
import com.scc.softdev.services.TProjectArray;
import net.java.dev.jaxb.array.LongArray;
import net.java.dev.jaxb.array.StringArray;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SoftDev_Entity", targetNamespace = "http://services.softdev.scc.com")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    com.scc.softdev.services.ObjectFactory.class,
    net.java.dev.jaxb.array.ObjectFactory.class
})
public interface SoftDevEntity {


    /**
     * 
     * @param tmsTask
     * @param entityType
     * @param entityId
     * @param linkComment
     * @return
     *     returns long
     * @throws SDException
     */
    @WebMethod(operationName = "AddTmsLink")
    @WebResult(partName = "return")
    public long addTmsLink(
        @WebParam(name = "entityType", partName = "entityType")
        long entityType,
        @WebParam(name = "entityId", partName = "entityId")
        long entityId,
        @WebParam(name = "tmsTask", partName = "tmsTask")
        String tmsTask,
        @WebParam(name = "linkComment", partName = "linkComment")
        String linkComment)
        throws SDException
    ;

    /**
     * 
     * @param linkId
     * @return
     *     returns boolean
     * @throws SDException
     */
    @WebMethod(operationName = "DeleteLink")
    @WebResult(partName = "return")
    public boolean deleteLink(
        @WebParam(name = "linkId", partName = "linkId")
        long linkId)
        throws SDException
    ;

    /**
     * 
     * @param comment
     * @param entityUFI
     * @param username
     * @return
     *     returns long
     * @throws SDException
     */
    @WebMethod(operationName = "AddCommentToEntity")
    @WebResult(partName = "return")
    public long addCommentToEntity(
        @WebParam(name = "username", partName = "username")
        String username,
        @WebParam(name = "entityUFI", partName = "entityUFI")
        String entityUFI,
        @WebParam(name = "comment", partName = "comment")
        String comment)
        throws SDException
    ;

    /**
     * 
     * @param stEntityId
     * @param entityType
     * @param stProject
     * @param stEntity
     * @param entityId
     * @param linkComment
     * @return
     *     returns long
     * @throws SDException
     */
    @WebMethod(operationName = "AddStLink")
    @WebResult(partName = "return")
    public long addStLink(
        @WebParam(name = "entityType", partName = "entityType")
        long entityType,
        @WebParam(name = "entityId", partName = "entityId")
        long entityId,
        @WebParam(name = "stProject", partName = "stProject")
        String stProject,
        @WebParam(name = "stEntity", partName = "stEntity")
        String stEntity,
        @WebParam(name = "stEntityId", partName = "stEntityId")
        long stEntityId,
        @WebParam(name = "linkComment", partName = "linkComment")
        String linkComment)
        throws SDException
    ;

    /**
     * 
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     */
    @WebMethod(operationName = "GetStProjects")
    @WebResult(partName = "return")
    public StringArray getStProjects();

    /**
     * 
     * @param entityUFI
     * @return
     *     returns com.scc.softdev.services.TEntityTrackingArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetTracking")
    @WebResult(partName = "return")
    public TEntityTrackingArray getTracking(
        @WebParam(name = "entityUFI", partName = "entityUFI")
        String entityUFI)
        throws SDException
    ;

    /**
     * 
     * @param onlyActive
     * @param versionPattern
     * @param onlyLinkedWithHF
     * @return
     *     returns net.java.dev.jaxb.array.LongArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetPjIDByPattern")
    @WebResult(partName = "return")
    public LongArray getPjIDByPattern(
        @WebParam(name = "versionPattern", partName = "versionPattern")
        String versionPattern,
        @WebParam(name = "onlyLinkedWithHF", partName = "onlyLinkedWithHF")
        boolean onlyLinkedWithHF,
        @WebParam(name = "onlyActive", partName = "onlyActive")
        boolean onlyActive)
        throws SDException
    ;

    /**
     * 
     * @param includeSystemLinks
     * @param entityType
     * @param entityId
     * @return
     *     returns com.scc.softdev.services.TEntityLinks
     * @throws SDException
     */
    @WebMethod(operationName = "GetEntityLinks")
    @WebResult(partName = "return")
    public TEntityLinks getEntityLinks(
        @WebParam(name = "entityType", partName = "entityType")
        long entityType,
        @WebParam(name = "entityId", partName = "entityId")
        long entityId,
        @WebParam(name = "includeSystemLinks", partName = "includeSystemLinks")
        boolean includeSystemLinks)
        throws SDException
    ;

    /**
     * 
     * @param cmHfId
     * @param linkComment
     * @param projectName
     * @return
     *     returns net.java.dev.jaxb.array.LongArray
     * @throws SDException
     */
    @WebMethod(operationName = "AddCmHfLink")
    @WebResult(partName = "return")
    public LongArray addCmHfLink(
        @WebParam(name = "projectName", partName = "projectName")
        String projectName,
        @WebParam(name = "cmHfId", partName = "cmHfId")
        String cmHfId,
        @WebParam(name = "linkComment", partName = "linkComment")
        String linkComment)
        throws SDException
    ;

    /**
     * 
     * @param onlyActive
     * @param versionPattern
     * @param onlyLinkedWithHF
     * @return
     *     returns net.java.dev.jaxb.array.StringArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetPjNameByPattern")
    @WebResult(partName = "return")
    public StringArray getPjNameByPattern(
        @WebParam(name = "versionPattern", partName = "versionPattern")
        String versionPattern,
        @WebParam(name = "onlyLinkedWithHF", partName = "onlyLinkedWithHF")
        boolean onlyLinkedWithHF,
        @WebParam(name = "onlyActive", partName = "onlyActive")
        boolean onlyActive)
        throws SDException
    ;

    /**
     * 
     * @param criteria
     * @return
     *     returns com.scc.softdev.services.TProjectArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetPjByCriteria")
    @WebResult(partName = "return")
    public TProjectArray getPjByCriteria(
        @WebParam(name = "criteria", partName = "criteria")
        TCriteriaArray criteria)
        throws SDException
    ;

    /**
     * 
     * @return
     *     returns com.scc.softdev.services.TEntityTypeArray
     * @throws SDException
     */
    @WebMethod(operationName = "GetEntityTypes")
    @WebResult(partName = "return")
    public TEntityTypeArray getEntityTypes()
        throws SDException
    ;

    /**
     * 
     * @param targetEntityId
     * @param sourceEntityType
     * @param targetEntityType
     * @param sourceEntityId
     * @param linkType
     * @param linkComment
     * @return
     *     returns long
     * @throws SDException
     */
    @WebMethod(operationName = "AddEntityLink")
    @WebResult(partName = "return")
    public long addEntityLink(
        @WebParam(name = "sourceEntityType", partName = "sourceEntityType")
        long sourceEntityType,
        @WebParam(name = "sourceEntityId", partName = "sourceEntityId")
        long sourceEntityId,
        @WebParam(name = "targetEntityType", partName = "targetEntityType")
        long targetEntityType,
        @WebParam(name = "targetEntityId", partName = "targetEntityId")
        long targetEntityId,
        @WebParam(name = "linkComment", partName = "linkComment")
        String linkComment,
        @WebParam(name = "linkType", partName = "linkType")
        long linkType)
        throws SDException
    ;

    /**
     * 
     * @return
     *     returns com.scc.softdev.services.TEntityTypeArray
     */
    @WebMethod(operationName = "GetProcessingEntityTypes")
    @WebResult(partName = "return")
    public TEntityTypeArray getProcessingEntityTypes();

    /**
     * 
     * @return
     *     returns com.scc.softdev.services.TEntityTypeArray
     */
    @WebMethod(operationName = "GetComparableEntityTypes")
    @WebResult(partName = "return")
    public TEntityTypeArray getComparableEntityTypes();

    /**
     * 
     * @return
     *     returns com.scc.softdev.services.TEntityTypeArray
     */
    @WebMethod(operationName = "GetCrossEntityTypes")
    @WebResult(partName = "return")
    public TEntityTypeArray getCrossEntityTypes();

}
