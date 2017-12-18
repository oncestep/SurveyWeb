package com.moyo.Beans;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Batch", schema = "dbo", catalog = "SurveyWeb")
public class BatchEntity {
    private long batchId;
    private String batchName;
    private String description;
    private Timestamp createTime;
    private Long managerId;

    @Id
    @Column(name = "batchId", nullable = false)
    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }

    @Basic
    @Column(name = "batchName", nullable = true, length = 50)
    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
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
    @Column(name = "managerId", nullable = true)
    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BatchEntity that = (BatchEntity) o;

        if (batchId != that.batchId) return false;
        if (batchName != null ? !batchName.equals(that.batchName) : that.batchName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (managerId != null ? !managerId.equals(that.managerId) : that.managerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (batchId ^ (batchId >>> 32));
        result = 31 * result + (batchName != null ? batchName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (managerId != null ? managerId.hashCode() : 0);
        return result;
    }
}
