package com.moyo.managedbean;
/**
 * Query all the Survey data from database
 * you can parsing batchName to reach the survey in particular batch
 */

import com.moyo.beans.BatchEntity;
import com.moyo.beans.SurveyEntity;
import com.moyo.dao.BatchDAO;
import com.moyo.dao.SurveyDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class SurveyManagedBean {
    private long naireId;
    private String naireName;
    private String description;
    private Timestamp createTime;
    private Long batchId;
    private String batchName;

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

    /**
     * To get all the Surveys. You can get the Surveys in a particular batch by using batchId
     * @return List<SurveyEntity>
     */
    public List<SurveyEntity> allSurveyList(){
        List list;
        SurveyDAO surveyDAO=new SurveyDAO();
        list=surveyDAO.findAll();
        return list;
    }
    public List<SurveyEntity> allSurveyList(Long batchId){
        List list;
        SurveyDAO surveyDAO=new SurveyDAO();
        list= surveyDAO.findByNaireName(batchId);
        return list;
    }

    /**
     * Get all the Survey in particular batch
     * @return List<SurveyEntity>
     */
    public List<SurveyEntity> getAllSurveyList(){
        BatchDAO batchDAO=new BatchDAO();
        BatchEntity batchEntity= (BatchEntity) batchDAO.findByBatchName(batchName).get(0);
        List list=allSurveyList(batchEntity.getBatchId());
        return list;
    }
}
