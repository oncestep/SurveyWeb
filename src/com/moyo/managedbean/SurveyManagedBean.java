package com.moyo.managedbean;

import com.moyo.beans.*;
import com.moyo.dao.*;

/**
 * Query all the Survey data from database
 * you can parsing batchName to reach the survey in particular batch
 */

import com.moyo.beans.SurveyEntity;
import com.moyo.dao.SurveyDAO;
import com.moyo.listener.MultiplySelectListener;
import com.moyo.listener.SingleSelectListener;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


@ManagedBean
@SessionScoped
public class SurveyManagedBean implements ActionListener {
    private long naireId;
    private String naireName;
    private String description;
    private Timestamp createTime;
    private Long batchId;
    private String batchName;

    /*  当前用户所有活跃的问卷列表 */
    private List<SurveyEntity> surList;

    /*  当前用户可以反馈的问卷列表(回答过)  */
    private List<SurveyEntity> feedBackSurvey;

    /*  指定问卷所有问题列表    */
    private List<QuestionEntity> queList;

    /*  管理员指定批次下的所有问卷  */
    private List<SurveyEntity> surveyList = new ArrayList<>();

    /*  获取当前用户可以反馈的问卷列表(Get方法)  */
    public List<SurveyEntity> getFeedBackSurvey() {
        AnswerDAO ansDAO = new AnswerDAO();
        SurveyDAO surDAO = new SurveyDAO();
        List<SurveyEntity> tempList = new ArrayList<SurveyEntity>();
        SurveyEntity tempSurvey = new SurveyEntity();

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(true);
        long userId = (long) session.getAttribute("userId");

        List<AnswerEntity> answerList = ansDAO.findByUserId(userId);
        for (AnswerEntity answer : answerList) {
            tempSurvey = surDAO.findById(answer.getNaireId());
            if (tempSurvey != null) {
                tempList.add(tempSurvey);
            }
        }
        feedBackSurvey = tempList;

        return feedBackSurvey;
    }

    public void setFeedBackSurvey(List<SurveyEntity> feedBackSurvey) {
        this.feedBackSurvey = feedBackSurvey;
    }

    public List<SurveyEntity> getSurveyList() {
        return surveyList;
    }

    public void setSurveyList(List<SurveyEntity> surveyList) {
        this.surveyList = surveyList;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public long getNaireId() {
        return naireId;
    }

    public void setNaireId(long naireId) {
        this.naireId = naireId;
    }

    public String getNaireName() {
        return naireName;
    }

    public void setNaireName(String naireName) {
        this.naireName = naireName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public List<SurveyEntity> getSurList() {
        return surList;
    }

    public void setSurList(List<SurveyEntity> surList) {
        this.surList = surList;
    }

    public List<QuestionEntity> getQueList() {
        return queList;
    }

    public void setQueList(List<QuestionEntity> queList) {
        this.queList = queList;
    }


    /*  输出当前用户所需填写问卷  */
    public void showAllSurvey(long userId) {
        SurveyDAO surDAO = new SurveyDAO();
        ParticipationDAO parDAO = new ParticipationDAO();
        List<ParticipationEntity> parList = parDAO.findByUserId(userId);

        long batchId = 0;
        List<SurveyEntity> batchSurveyList = new ArrayList<>();
        surList = new ArrayList<>();
        for (ParticipationEntity par : parList) {
            batchId = par.getBatchId();
            batchSurveyList = surDAO.findAvailable(batchId, userId);
            if (batchSurveyList.isEmpty() == false) {
                surList.addAll(batchSurveyList);
            }
        }
    }

    /*  填写Id对应问卷  */
    public String showAllQuestion(long surId) {
        SurveyDAO surDAO = new SurveyDAO();
        QuestionDAO queDAO = new QuestionDAO();

        SurveyEntity survey = surDAO.findById(surId);
        naireId = survey.getNaireId();
        naireName = survey.getNaireName();
        description = survey.getDescription();
        createTime = survey.getCreateTime();
        batchId = survey.getBatchId();

        queList = queDAO.findByNaireId(surId);

        return "/user/survey.xhtml";
    }

    /**
     * 提交问卷
     * 更新选项被选次数
     * 插入Answer表
     * 刷新当前用户所有问卷列表
     *
     * @return
     */
    public String answerSurvey(long userId, long naireId) {
        List<Long> singleSelectList = SingleSelectListener.singleSelectList;
        List<Long[]> multiplySelectList = MultiplySelectListener.multiplySelectList;
        OptionDAO optDAO = new OptionDAO();
        OptionEntity option = new OptionEntity();

        /*  更新单选Hits  */
        for (long optId : singleSelectList
                ) {
            option = optDAO.findById(optId);
            option.setHits(option.getHits() + 1);
            optDAO.merge(option);
        }
        /*  更新多选Hits  */
        for (Long[] optIdList : multiplySelectList
                ) {
            for (long optId : optIdList
                    ) {
                option = optDAO.findById(optId);
                option.setHits(option.getHits() + 1);
                optDAO.merge(option);
            }
        }
        SingleSelectListener.singleSelectList.clear();
        MultiplySelectListener.multiplySelectList.clear();

        /*  插入Answer表  */
        AnswerDAO ansDAO = new AnswerDAO();
        AnswerEntity answer = new AnswerEntity();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        answer.setUserId(userId);
        answer.setNaireId(naireId);
        answer.setAnswerTime(time);
        ansDAO.save(answer);

        /*  刷新当前用户所有问卷列表  */
        surList.clear();
        showAllSurvey(userId);

        return "/user/index.xhtml";
    }

    /**
     * To get all the Surveys. You can get the Surveys in a particular batch by using batchId
     *
     * @return List<SurveyEntity>
     */
    public List<SurveyEntity> allSurveyList() {
        List list;
        SurveyDAO surveyDAO = new SurveyDAO();
        list = surveyDAO.findAll();
        return list;
    }

    public List<SurveyEntity> allSurveyList(Long batchId) {
        List list;
        SurveyDAO surveyDAO = new SurveyDAO();
        list = surveyDAO.findByBatchId(batchId);
        return list;
    }

    public void deleteSurvey(ActionEvent action) {
        SurveyDAO surveyDAO = new SurveyDAO();
        Long surveyId = (Long) action.getComponent().getAttributes().get("surveyId");

        //找到问卷所属批次Id
        SurveyEntity survey = surveyDAO.findById(surveyId);
        long batchId = survey.getBatchId();

        //删除问卷
        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setNaireId(surveyId);
        surveyDAO.delete(surveyEntity);

        //刷新管理者指定批次下所有问卷
        if (surveyList.isEmpty() == false) {
            surveyList.clear();
        }
        surveyList = allSurveyList(batchId);
    }

    @Override
    public void processAction(ActionEvent actionEvent) throws AbortProcessingException {
        Long batchId = (Long) actionEvent.getComponent().getAttributes().get("batchId");

        if (surveyList.isEmpty() == false) {
            surveyList.clear();
        }
        surveyList = allSurveyList(batchId);
    }
}
