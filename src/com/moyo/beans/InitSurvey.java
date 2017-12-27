package com.moyo.beans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class InitSurvey {
    InitQuestion initQuestion;
    List<InitQuestion> initQuestions = new ArrayList<>();
    private String naireName;
    private String description;
    private Timestamp createTime;
    private Long batchId;

    public List<InitQuestion> getInitQuestions() {
        return initQuestions;
    }

    public void setInitQuestions(List<InitQuestion> initQuestions) {
        this.initQuestions = initQuestions;
    }

    public InitQuestion getInitQuestion() {
        return initQuestion;
    }

    public void setInitQuestion(InitQuestion initQuestion) {
        this.initQuestion = initQuestion;
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

}
