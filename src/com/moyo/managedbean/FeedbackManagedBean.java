package com.moyo.managedbean;


import com.moyo.beans.FeedbackEntity;
import com.moyo.dao.FeedbackDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.moyo.beans.BatchEntity;
import com.moyo.beans.SurveyEntity;
import com.moyo.dao.BatchDAO;
import com.moyo.dao.SurveyDAO;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


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

    private HttpSession getHttpSession(){
        FacesContext facesContext=FacesContext.getCurrentInstance();
        HttpSession session=(HttpSession) facesContext.getExternalContext().getSession(true);
        return session;
    }
    public List<FeedbackEntity> getAllFeedbacks(){
        FeedbackDAO feedbackDAO=new FeedbackDAO();
        BatchDAO batchDAO=new BatchDAO();
        SurveyDAO surveyDAO=new SurveyDAO();

        HttpSession session=getHttpSession();

        Long managerId= (Long) session.getAttribute("managerId");
        List<BatchEntity> batchEntityList=batchDAO.findByManagerId(managerId);
        List<Long> batchIdList=new ArrayList<>();
        List<SurveyEntity> surveyEntityList;
        List<Long> surveyIdList=new ArrayList<>();
        List<FeedbackEntity> list = new ArrayList<>();
        List<FeedbackEntity> listTemp;

        for(BatchEntity item:batchEntityList){
            batchIdList.add(item.getBatchId());
        }
        for(Long item:batchIdList){
            surveyEntityList=surveyDAO.findByBatchId(item);
            for(SurveyEntity surveyItem:surveyEntityList){
                surveyIdList.add(surveyItem.getNaireId());
            }
        }
        for(Long item:surveyIdList){
            listTemp=feedbackDAO.findByNaireId(item);
            for (FeedbackEntity feedbackItem: listTemp) {
                list.add(feedbackItem);
            }
        }

        return list;
    }

}
