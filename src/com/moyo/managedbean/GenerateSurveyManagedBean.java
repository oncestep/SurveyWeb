package com.moyo.managedbean;

import com.moyo.beans.*;
import com.moyo.dao.BatchDAO;
import com.moyo.dao.OptionDAO;
import com.moyo.dao.QuestionDAO;
import com.moyo.dao.SurveyDAO;
import org.jboss.weld.context.http.Http;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/*
* 将一个问题和他的选项封装在一个initQuestion中做临时存储，再将所有question封装在一个initSurvey中临时存储并上传数据库，
* 然后再获取到Survey的id依次拆开initSurvey
* 获取其中所有的initQuestion附上问卷的id并上传数据库，再拆开每一个initQuestion附上问题的id上传数据库
* */
@ManagedBean
@SessionScoped
public class GenerateSurveyManagedBean implements ActionListener {
    //  问题内容
    private String questionContent;
    //  选项内容
    private String optionContent;
    //  问题类型
    private Integer type;
    //  问卷名
    private String naireName;
    //  问卷描述
    private String description;
    //  选项列表 暂存
    private List<OptionEntity> options = new ArrayList<>();
    //  未使用
    private List<QuestionEntity> questions = new ArrayList<>();
    //  问题列表
    private List<InitQuestion> initQuestions = new ArrayList<>();

    //  当前管理员所有Batch列表 暂存
    private List batchIds = new ArrayList<>();
    //  当前管理员所有Batch列表
    private List<BatchEntity> batchEntityList = new ArrayList<>();
    //  生成问卷所属Batch
    private Long batId;

    //  Get方法 得到当前管理员管理所有的Batch列表
    public List<BatchEntity> getBatchEntityList() {
        BatchDAO batchDAO = new BatchDAO();
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
        long batchId = (long) session.getAttribute("managerId");
        batchIds = batchDAO.findByManagerId(batchId);
        List<BatchEntity> batchList = new ArrayList<>();
        for (Object bId : batchIds) {
            BatchEntity batchEntity = (BatchEntity) bId;
            batchList.add(batchEntity);
        }
        batchEntityList = batchList;
        return batchEntityList;
    }

    public void setBatchEntityList(List<BatchEntity> batchEntityList) {
        this.batchEntityList = batchEntityList;
    }

    public List<Long> getBatchIds() {

        return batchIds;
    }

    public void setBatchIds(List<Long> batchIds) {
        this.batchIds = batchIds;
    }

    public List<InitQuestion> getInitQuestions() {
        return initQuestions;
    }

    public void setInitQuestions(List<InitQuestion> initQuestions) {
        this.initQuestions = initQuestions;
    }

    public List<OptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<OptionEntity> options) {
        this.options = options;
    }

    public List<QuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionEntity> questions) {
        this.questions = questions;
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

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(String optionContent) {
        this.optionContent = optionContent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getBatId() {
        return batId;
    }

    public void setBatId(Long batId) {
        this.batId = batId;
    }

    /*  新增选项  */
    public List<OptionEntity> showOptions() {
        OptionEntity optionEntity = new OptionEntity();
        optionEntity.setContent(optionContent);
        options.add(optionEntity);
        return options;
    }

    /*  删除暂存选项  */
    @Override
    public void processAction(ActionEvent actionEvent) throws AbortProcessingException {
        int index = (int) actionEvent.getComponent().getAttributes().get("optionIndex");
        options.remove(index);
    }

    /*  删除暂存问题  */
    public void removeFromQuestion(ActionEvent actionEvent) {
        int index = (int) actionEvent.getComponent().getAttributes().get("questionIndex");
        initQuestions.remove(index);
    }

    /*  将选项组和问题对象封装到InitQuestion对象中，将InitQuestion加入到InitQuestions列表中  */
    public void addToQuestion() {
        InitQuestion initQuestion = new InitQuestion();
        List<OptionEntity> options = new ArrayList<>();
        options.addAll(this.options);
        initQuestion.setOptionEntityList(options);
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setContent(questionContent);
        questionEntity.setType(type);
        initQuestion.setQuestionEntity(questionEntity);
        initQuestions.add(initQuestion);
        this.options.clear();
    }

    /*  将问卷信息打包在拆包存入数据库  */
    public String addToSurvey() {
        java.util.Date nowTime = new java.util.Date();
        Timestamp timestamp = new Timestamp(nowTime.getTime());
        long batchId = batId;
        SurveyEntity surveyEntity = new SurveyEntity();

        SurveyDAO surveyDAO = new SurveyDAO();
        QuestionDAO questionDAO = new QuestionDAO();
        OptionDAO optionDAO = new OptionDAO();

        InitSurvey initSurvey = new InitSurvey();
        initSurvey.setNaireName(naireName);
        initSurvey.setInitQuestions(initQuestions);
        initSurvey.setDescription(description);
        initSurvey.setCreateTime(timestamp);
        initSurvey.setBatchId(batchId);

        surveyEntity.setBatchId(initSurvey.getBatchId());
        surveyEntity.setCreateTime(initSurvey.getCreateTime());
        surveyEntity.setDescription(initSurvey.getDescription());
        surveyEntity.setNaireName(initSurvey.getNaireName());

        surveyDAO.save(surveyEntity);

        for (InitQuestion initq : initSurvey.getInitQuestions()) {
            QuestionEntity questionItem = initq.getQuestionEntity();
            questionItem.setNaireId(surveyEntity.getNaireId());
            questionDAO.save(questionItem);
            for (OptionEntity optionItem : initq.getOptionEntityList()) {
                optionItem.setQuestionId(questionItem.getQuestionId());
                optionItem.setHits(Long.parseLong("0"));
                optionDAO.save(optionItem);
            }
        }

        //清空缓存
        questionContent = null;
        optionContent = null;
        type = null;
        naireName = null;
        description = null;
        options.clear();
        initQuestions.clear();

        //控制跳转
        return "ManagerIndex.xhtml?faces-redirect=true";
    }
}
