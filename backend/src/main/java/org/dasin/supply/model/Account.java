package org.dasin.supply.model;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    private String addr;
    private String pemPrivateKey;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPemPrivateKey() {
        return pemPrivateKey;
    }

    public void setPemPrivateKey(String pemPrivateKey) {
        this.pemPrivateKey = pemPrivateKey;
    }

    public String getPemPublicKey() {
        return pemPublicKey;
    }

    public void setPemPublicKey(String pemPublicKey) {
        this.pemPublicKey = pemPublicKey;
    }

    private String pemPublicKey;
}
