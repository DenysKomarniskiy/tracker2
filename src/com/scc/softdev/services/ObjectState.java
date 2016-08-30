
package com.scc.softdev.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for objectState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="objectState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Unchanged"/>
 *     &lt;enumeration value="New"/>
 *     &lt;enumeration value="Updated"/>
 *     &lt;enumeration value="Removed"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "objectState")
@XmlEnum
public enum ObjectState {

    @XmlEnumValue("Unchanged")
    UNCHANGED("Unchanged"),
    @XmlEnumValue("New")
    NEW("New"),
    @XmlEnumValue("Updated")
    UPDATED("Updated"),
    @XmlEnumValue("Removed")
    REMOVED("Removed");
    private final String value;

    ObjectState(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ObjectState fromValue(String v) {
        for (ObjectState c: ObjectState.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
