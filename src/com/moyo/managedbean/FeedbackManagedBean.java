package com.moyo.managedbean;

import com.moyo.beans.BatchEntity;
import com.moyo.beans.FeedbackEntity;
import com.moyo.beans.FeedbackItem;
import com.moyo.beans.SurveyEntity;
import com.moyo.dao.BatchDAO;
import com.moyo.dao.FeedbackDAO;
import com.moyo.dao.SurveyDAO;
import com.moyo.dao.UserDetailDAO;
import com.sun.xml.internal.ws.resources.HttpserverMessages;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
    private List<FeedbackItem> feedbackItems=new ArrayList<>();

    public List<FeedbackItem> getFeedbackItems() {
        String questionnaireName = null;
        String content=null;
        String userName=null;
        UserDetailDAO userDetailDAO=new UserDetailDAO();
        SurveyDAO surveyDAO=new SurveyDAO();

        FeedbackItem feedbackItem=new FeedbackItem();
        List<FeedbackEntity> feedbcakList;
        feedbcakList=getAllFeedbacks();
        for(FeedbackEntity item:feedbcakList){
            questionnaireName=surveyDAO.findById(item.getNaireId()).getNaireName();
            content=item.getFeedbacks();
            userName=userDetailDAO.findById(item.getUserId()).getNickname();
        }

        feedbackItem.setContent(content);
        feedbackItem.setNaireName(questionnaireName);
        feedbackItem.setUserName(userName);

        feedbackItems.add(feedbackItem);
        return feedbackItems;
    }

    public void setFeedbackItems(List<FeedbackItem> feedbackItems) {
        this.feedbackItems = feedbackItems;
    }

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

    private HttpSession getHttpSession(){
        FacesContext facesContext=FacesContext.getCurrentInstance();
        HttpSession session=(HttpSession) facesContext.getExternalContext().getSession(true);
        return session;
    }
    /**
     *
     * @return 所有当前登录的管理员管理的反馈信息
     */
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
