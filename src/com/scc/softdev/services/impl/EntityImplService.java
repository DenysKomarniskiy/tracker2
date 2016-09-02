
package com.scc.softdev.services.impl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EntityImplService", targetNamespace = "http://impl.services.softdev.scc.com/", wsdlLocation = "http://sd01srv.softsystem.pl:8080/SoftDevSrv/services/SoftDev_Entity?wsdl")
public class EntityImplService
    extends Service
{

    private final static URL ENTITYIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException ENTITYIMPLSERVICE_EXCEPTION;
    private final static QName ENTITYIMPLSERVICE_QNAME = new QName("http://impl.services.softdev.scc.com/", "EntityImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://sd01srv.softsystem.pl:8080/SoftDevSrv/services/SoftDev_Entity?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ENTITYIMPLSERVICE_WSDL_LOCATION = url;
        ENTITYIMPLSERVICE_EXCEPTION = e;
    }

    public EntityImplService() {
        super(__getWsdlLocation(), ENTITYIMPLSERVICE_QNAME);
    }

    public EntityImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ENTITYIMPLSERVICE_QNAME, features);
    }

    public EntityImplService(URL wsdlLocation) {
        super(wsdlLocation, ENTITYIMPLSERVICE_QNAME);
    }

    public EntityImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ENTITYIMPLSERVICE_QNAME, features);
    }

    public EntityImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EntityImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SoftDevEntity
     */
    @WebEndpoint(name = "EntityImplPort")
    public SoftDevEntity getEntityImplPort() {
        return super.getPort(new QName("http://impl.services.softdev.scc.com/", "EntityImplPort"), SoftDevEntity.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SoftDevEntity
     */
    @WebEndpoint(name = "EntityImplPort")
    public SoftDevEntity getEntityImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://impl.services.softdev.scc.com/", "EntityImplPort"), SoftDevEntity.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ENTITYIMPLSERVICE_EXCEPTION!= null) {
            throw ENTITYIMPLSERVICE_EXCEPTION;
        }
        return ENTITYIMPLSERVICE_WSDL_LOCATION;
    }

}
