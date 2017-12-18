package com.moyo.Beans;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Answer", schema = "dbo", catalog = "SurveyWeb")
public class AnswerEntity {
    private long answerId;
    private Long userId;
    private Long naireId;
    private Timestamp answerTime;

    @Id
    @Column(name = "answerId", nullable = false)
    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
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
    @Column(name = "naireId", nullable = true)
    public Long getNaireId() {
        return naireId;
    }

    public void setNaireId(Long naireId) {
        this.naireId = naireId;
    }

    @Basic
    @Column(name = "answerTime", nullable = true)
    public Timestamp getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Timestamp answerTime) {
        this.answerTime = answerTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnswerEntity that = (AnswerEntity) o;

        if (answerId != that.answerId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (naireId != null ? !naireId.equals(that.naireId) : that.naireId != null) return false;
        if (answerTime != null ? !answerTime.equals(that.answerTime) : that.answerTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (answerId ^ (answerId >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (naireId != null ? naireId.hashCode() : 0);
        result = 31 * result + (answerTime != null ? answerTime.hashCode() : 0);
        return result;
    }
}
