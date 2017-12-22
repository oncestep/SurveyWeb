package com.moyo.managedbean;

import com.moyo.beans.ParticipationEntity;
import com.moyo.beans.QuestionEntity;
import com.moyo.beans.SurveyEntity;
import com.moyo.dao.OptionDAO;
import com.moyo.dao.ParticipationDAO;
import com.moyo.dao.QuestionDAO;
import com.moyo.dao.SurveyDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.sql.Timestamp;
import java.util.List;

@ManagedBean
@RequestScoped
//@SessionScoped
public class SurveyManagedBean {
    private long naireId;
    private String naireName;
    private String description;
    private Timestamp createTime;
    private Long batchId;

    /*  所有问卷信息 */
    private List<SurveyEntity> surList;

    /*  选中问卷所有问题    */
    private List<QuestionEntity> queList;

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

    /*  输出当前用户所需填写问卷    */
    public void showAll(String userId) {
        SurveyDAO surDAO = new SurveyDAO();
        ParticipationDAO parDAO = new ParticipationDAO();
        List<ParticipationEntity> parList = parDAO.findByUserId(userId);

        long batchId = 0;
        List<SurveyEntity> batchSurveyList = null;
        for (ParticipationEntity par : parList) {
            batchId = par.getBatchId();
            batchSurveyList = surDAO.findByBatchId(batchId);
            if (batchSurveyList.isEmpty() == false) {
                surList.addAll(batchSurveyList);
            }
        }
    }

    /*  填写Id对应问卷  */
    public void showQuestion(String surId) {
        SurveyDAO surDAO = new SurveyDAO();
        QuestionDAO queDAO = new QuestionDAO();
        OptionDAO optDAO = new OptionDAO();

        SurveyEntity survey = surDAO.findById(surId);
        naireId = survey.getNaireId();
        naireName = survey.getNaireName();
        description = survey.getDescription();
        createTime = survey.getCreateTime();
        batchId = survey.getBatchId();

        queList = queDAO.findByNaireId(surId);
    }
}
