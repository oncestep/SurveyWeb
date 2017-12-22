package com.moyo.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
}
