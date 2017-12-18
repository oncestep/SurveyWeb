package com.moyo.Beans;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Participation", schema = "dbo", catalog = "SurveyWeb")
public class ParticipationEntity {
    private long partId;
    private Long batchId;
    private Long userId;
    private Timestamp partTime;

    @Id
    @Column(name = "partId", nullable = false)
    public long getPartId() {
        return partId;
    }

    public void setPartId(long partId) {
        this.partId = partId;
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
    @Column(name = "userId", nullable = true)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "partTime", nullable = true)
    public Timestamp getPartTime() {
        return partTime;
    }

    public void setPartTime(Timestamp partTime) {
        this.partTime = partTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParticipationEntity that = (ParticipationEntity) o;

        if (partId != that.partId) return false;
        if (batchId != null ? !batchId.equals(that.batchId) : that.batchId != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (partTime != null ? !partTime.equals(that.partTime) : that.partTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (partId ^ (partId >>> 32));
        result = 31 * result + (batchId != null ? batchId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (partTime != null ? partTime.hashCode() : 0);
        return result;
    }
}
