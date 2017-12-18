package com.moyo.Beans;

import javax.persistence.*;

@Entity
@Table(name = "Tip", schema = "dbo", catalog = "SurveyWeb")
public class TipEntity {
    private long tipId;
    private Long managerId;
    private Long batchId;
    private String tips;

    @Id
    @Column(name = "tipId", nullable = false)
    public long getTipId() {
        return tipId;
    }

    public void setTipId(long tipId) {
        this.tipId = tipId;
    }

    @Basic
    @Column(name = "managerId", nullable = true)
    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    @Basic
    @Column(name = "batchId", nullable = true)
    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    @Basic
    @Column(name = "tips", nullable = true, length = 200)
    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TipEntity tipEntity = (TipEntity) o;

        if (tipId != tipEntity.tipId) return false;
        if (managerId != null ? !managerId.equals(tipEntity.managerId) : tipEntity.managerId != null) return false;
        if (batchId != null ? !batchId.equals(tipEntity.batchId) : tipEntity.batchId != null) return false;
        if (tips != null ? !tips.equals(tipEntity.tips) : tipEntity.tips != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (tipId ^ (tipId >>> 32));
        result = 31 * result + (managerId != null ? managerId.hashCode() : 0);
        result = 31 * result + (batchId != null ? batchId.hashCode() : 0);
        result = 31 * result + (tips != null ? tips.hashCode() : 0);
        return result;
    }
}
