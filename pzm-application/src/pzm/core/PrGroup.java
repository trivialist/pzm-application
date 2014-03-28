package pzm.core;
/*
 * PrGroup.java
 *
 * Created on 13. Dezember 2005, 13:05
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

/**
 *
 * @author hertel
 */
public class PrGroup {
    
    private String prGroup;
    private int prGroupID;
    /** Creates a new instance of PrGroup */
    public PrGroup(String prGroup) {
        this.prGroup = prGroup;
    }
    
    
    
    
    
    
    
    
    public String getPrGroup() {
        return prGroup;
    }
    
    public int getPrGroupID() {
        return prGroupID;
    }
    
    public void setPrGroup(String prGroup) {
        this.prGroup = prGroup;
    }
    
    public void setPrGroupID(int prGroupID) {
        this.getPrGroupID();
    }
}
