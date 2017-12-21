package com.moyo.beans;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Survey", schema = "dbo", catalog = "SurveyWeb")
public class SurveyEntity {
    private long naireId;
    private String naireName;
    private String description;
    private Timestamp createTime;
    private Long batchId;

    @Id
    @Column(name = "naireId", nullable = false)
    public long getNaireId() {
        return naireId;
    }

    public void setNaireId(long naireId) {
        this.naireId = naireId;
    }

    @Basic
    @Column(name = "naireName", nullable = true, length = -1)
    public String getNaireName() {
        return naireName;
    }

    public void setNaireName(String naireName) {
        this.naireName = naireName;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "createTime", nullable = true)
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "batchId", nullable = true)
    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SurveyEntity that = (SurveyEntity) o;

        if (naireId != that.naireId) return false;
        if (naireName != null ? !naireName.equals(that.naireName) : that.naireName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (batchId != null ? !batchId.equals(that.batchId) : that.batchId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (naireId ^ (naireId >>> 32));
        result = 31 * result + (naireName != null ? naireName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (batchId != null ? batchId.hashCode() : 0);
        return result;
    }
}
