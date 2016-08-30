
package com.scc.softdev.services.impl;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "SDException", targetNamespace = "http://services.softdev.scc.com")
public class SDException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private com.scc.softdev.services.SDException faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public SDException(String message, com.scc.softdev.services.SDException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public SDException(String message, com.scc.softdev.services.SDException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.scc.softdev.services.SDException
     */
    public com.scc.softdev.services.SDException getFaultInfo() {
        return faultInfo;
    }

}
