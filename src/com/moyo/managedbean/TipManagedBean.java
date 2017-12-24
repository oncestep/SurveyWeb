package com.moyo.managedbean;

import com.moyo.beans.TipEntity;
import com.moyo.dao.TipDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class TipManagedBean {
    private long tipId;
    private Long managerId;
    private Long batchId;
    private String tips;

    public long getTipId() {
        return tipId;
    }

    public void setTipId(long tipId) {
        this.tipId = tipId;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    private HttpSession getHttpSession(){
        FacesContext facesContext=FacesContext.getCurrentInstance();
        HttpSession session= (HttpSession) facesContext.getExternalContext().getSession(true);
        return session;
    }
    private TipEntity getEntity(){
        TipEntity tipEntity=new TipEntity();
        HttpSession session=getHttpSession();

        Long managerId= (Long) session.getAttribute("managerId");
        Long batchId= (Long) session.getAttribute("batchId");

        tipEntity.setTips(tips);
        tipEntity.setManagerId(managerId);
        tipEntity.setBatchId(batchId);

        return tipEntity;
    }
    public void addTip(){
        try{
            TipDAO tipDAO=new TipDAO();
            tipDAO.save(getEntity());
        }catch (Exception e){
            throw e;
        }
    }
}
