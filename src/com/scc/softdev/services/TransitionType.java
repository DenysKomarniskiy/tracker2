
package com.scc.softdev.services;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for transitionType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="transitionType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="None"/>
 *     &lt;enumeration value="Start"/>
 *     &lt;enumeration value="Regular"/>
 *     &lt;enumeration value="Stop"/>
 *     &lt;enumeration value="UpdateCR"/>
 *     &lt;enumeration value="SplitCR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "transitionType")
@XmlEnum
public enum TransitionType {

    @XmlEnumValue("None")
    NONE("None"),
    @XmlEnumValue("Start")
    START("Start"),
    @XmlEnumValue("Regular")
    REGULAR("Regular"),
    @XmlEnumValue("Stop")
    STOP("Stop"),
    @XmlEnumValue("UpdateCR")
    UPDATE_CR("UpdateCR"),
    @XmlEnumValue("SplitCR")
    SPLIT_CR("SplitCR");
    private final String value;

    TransitionType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TransitionType fromValue(String v) {
        for (TransitionType c: TransitionType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
