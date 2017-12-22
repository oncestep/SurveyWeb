package com.moyo.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class FeedbakcManagedBean {
    private long feedbackId;
    private Long userId;
    private Long naireId;
    private String feedbacks;

    public long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNaireId() {
        return naireId;
    }

    public void setNaireId(Long naireId) {
        this.naireId = naireId;
    }

    public String getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(String feedbacks) {
        this.feedbacks = feedbacks;
    }
}
