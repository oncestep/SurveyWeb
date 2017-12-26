package com.moyo.managedbean;

import com.moyo.beans.FeedbackEntity;
import com.moyo.dao.FeedbackDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class FeedbackManagedBean {
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

    /**
     * 用户针对某一问卷反馈信息
     *
     */
    public String sendFeedback(long userId){
        FeedbackDAO feeDAO = new FeedbackDAO();
        FeedbackEntity feedback = new FeedbackEntity();

        feedback.setUserId(userId);
        feedback.setNaireId(naireId);
        feedback.setFeedbacks(feedbacks);
        feeDAO.save(feedback);

        return "/user/index.xhtml";
    }
}
