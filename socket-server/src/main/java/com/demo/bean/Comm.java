
package com.demo.bean;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Comm {

    private String corecd;
    private String mesage;
    private String asktyp;
    private String status;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCorecd() {
        return corecd;
    }

    public void setCorecd(String corecd) {
        this.corecd = corecd;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public String getAsktyp() {
        return asktyp;
    }

    public void setAsktyp(String asktyp) {
        this.asktyp = asktyp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(corecd).append(mesage).append(asktyp).append(status).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Comm) == false) {
            return false;
        }
        Comm rhs = ((Comm) other);
        return new EqualsBuilder().append(corecd, rhs.corecd).append(mesage, rhs.mesage).append(asktyp, rhs.asktyp).append(status, rhs.status).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
