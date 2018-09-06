
package com.demo.bean;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Datum {

    private String addres;
    private String custna;
    private String custno;
    private String custst;
    private String idtfno;
    private String idtftp;
    private String teleno;
    private String addrcd;
    private String birthd;
    private String brchno;
    private String corpno;
    private String emails;
    private String opendt;
    private String sextyp;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getCustna() {
        return custna;
    }

    public void setCustna(String custna) {
        this.custna = custna;
    }

    public String getCustno() {
        return custno;
    }

    public void setCustno(String custno) {
        this.custno = custno;
    }

    public String getCustst() {
        return custst;
    }

    public void setCustst(String custst) {
        this.custst = custst;
    }

    public String getIdtfno() {
        return idtfno;
    }

    public void setIdtfno(String idtfno) {
        this.idtfno = idtfno;
    }

    public String getIdtftp() {
        return idtftp;
    }

    public void setIdtftp(String idtftp) {
        this.idtftp = idtftp;
    }

    public String getTeleno() {
        return teleno;
    }

    public void setTeleno(String teleno) {
        this.teleno = teleno;
    }

    public String getAddrcd() {
        return addrcd;
    }

    public void setAddrcd(String addrcd) {
        this.addrcd = addrcd;
    }

    public String getBirthd() {
        return birthd;
    }

    public void setBirthd(String birthd) {
        this.birthd = birthd;
    }

    public String getBrchno() {
        return brchno;
    }

    public void setBrchno(String brchno) {
        this.brchno = brchno;
    }

    public String getCorpno() {
        return corpno;
    }

    public void setCorpno(String corpno) {
        this.corpno = corpno;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getOpendt() {
        return opendt;
    }

    public void setOpendt(String opendt) {
        this.opendt = opendt;
    }

    public String getSextyp() {
        return sextyp;
    }

    public void setSextyp(String sextyp) {
        this.sextyp = sextyp;
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
        return new HashCodeBuilder().append(addres).append(custna).append(custno).append(custst).append(idtfno).append(idtftp).append(teleno).append(addrcd).append(birthd).append(brchno).append(corpno).append(emails).append(opendt).append(sextyp).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Datum) == false) {
            return false;
        }
        Datum rhs = ((Datum) other);
        return new EqualsBuilder().append(addres, rhs.addres).append(custna, rhs.custna).append(custno, rhs.custno).append(custst, rhs.custst).append(idtfno, rhs.idtfno).append(idtftp, rhs.idtftp).append(teleno, rhs.teleno).append(addrcd, rhs.addrcd).append(birthd, rhs.birthd).append(brchno, rhs.brchno).append(corpno, rhs.corpno).append(emails, rhs.emails).append(opendt, rhs.opendt).append(sextyp, rhs.sextyp).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
