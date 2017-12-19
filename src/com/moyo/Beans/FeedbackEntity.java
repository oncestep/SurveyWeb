package com.moyo.Beans;

import javax.persistence.*;

@Entity
@Table(name = "feedback", schema = "dbo", catalog = "SurveyWeb")
public class FeedbackEntity {
    private long feedbackId;
    private Long userId;
    private Long naireId;
    private String feedbacks;

    @Id
    @Column(name = "feedbackId", nullable = false)
    public long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
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
    @Column(name = "feedbacks", nullable = true, length = 200)
    public String getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(String feedbacks) {
        this.feedbacks = feedbacks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeedbackEntity that = (FeedbackEntity) o;

        if (feedbackId != that.feedbackId) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (naireId != null ? !naireId.equals(that.naireId) : that.naireId != null) return false;
        if (feedbacks != null ? !feedbacks.equals(that.feedbacks) : that.feedbacks != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (feedbackId ^ (feedbackId >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (naireId != null ? naireId.hashCode() : 0);
        result = 31 * result + (feedbacks != null ? feedbacks.hashCode() : 0);
        return result;
    }
}
