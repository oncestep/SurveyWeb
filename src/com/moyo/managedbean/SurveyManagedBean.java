package com.moyo.managedbean;

import com.moyo.beans.*;
import com.moyo.dao.*;

/**
 * Query all the Survey data from database
 * you can parsing batchName to reach the survey in particular batch
 */

import com.moyo.beans.SurveyEntity;
import com.moyo.dao.SurveyDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;



@SessionScoped
public class SurveyManagedBean implements ActionListener {
    private long naireId;
    private String naireName;
    private String description;
    private Timestamp createTime;
    private Long batchId;
    private String batchName;

    /*  所有问卷列表 */
    private List<SurveyEntity> surList;

    /*  指定问卷所有问题列表    */
    private List<QuestionEntity> queList;

    /*  指定问卷所有单选提交答案列表    */
    private List<OptionEntity> optOneList;

    /*  指定问卷所有多选提交答案列表    */
    private List<List<OptionEntity>> optMulList;

    private List<SurveyEntity> surveyList=new ArrayList<>();


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

    public List<OptionEntity> getOptOneList() {
        return optOneList;
    }

    public void setOptOneList(List<OptionEntity> optOneList) {
        this.optOneList = optOneList;
    }

    public List<List<OptionEntity>> getOptMulList() {
        return optMulList;
    }

    public void setOptMulList(List<List<OptionEntity>> optMulList) {
        this.optMulList = optMulList;
    }

    /*  输出当前用户所需填写问卷    */
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

    /*
        提交问卷
        更新选项被选次数
        插入Answer表
        清空单选，多选答案列表
    */
    public String answerSurvey(long userId) {
        OptionDAO optDAO = new OptionDAO();

        /*  更新选项被选次数  */
        for (OptionEntity opt : optOneList
                ) {
            if (opt != null) {
                optDAO.addHits(opt.getOptionId());
            }
        }
        for (List<OptionEntity> optList : optMulList
                ) {
            if (optList != null) {
                for (OptionEntity opt : optList
                        ) {
                    if (opt != null) {
                        optDAO.addHits(opt.getOptionId());
                    }
                }
            }
        }

        /*  插入Answer  */
        AnswerDAO answerDAO = new AnswerDAO();
        AnswerEntity answer = new AnswerEntity();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        answer.setUserId(userId);
        answer.setNaireId(naireId);
        answer.setAnswerTime(time);
        try {
            answerDAO.save(answer);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*  清空单选多选答案列表  */
        optOneList.clear();
        optMulList.clear();

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

    public void deleteSurvey(ActionEvent action){
        SurveyDAO surveyDAO=new SurveyDAO();
        Long surveyId= (Long) action.getComponent().getAttributes().get("surveyId");
        SurveyEntity surveyEntity=new SurveyEntity();
        surveyEntity.setNaireId(surveyId);
        surveyDAO.delete(surveyEntity);
    }
    @Override
    public void processAction(ActionEvent actionEvent) throws AbortProcessingException {
        Long batchId= (Long) actionEvent.getComponent().getAttributes().get("batchId");
<<<<<<< HEAD
        surveyList=allSurveyList(batchId);
=======
        if(surveyList.isEmpty() == false){
            surveyList.clear();
        }
        surveyList.addAll(allSurveyList(batchId));
>>>>>>> backup
    }
}
