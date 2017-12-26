package com.moyo.beans;

import java.util.ArrayList;
import java.util.List;

public class InitQuestion {
    private QuestionEntity questionEntity;
    private OptionEntity optionEntity;
    private List<OptionEntity> optionEntityList = new ArrayList<>();

    public List<OptionEntity> getOptionEntityList() {
        return optionEntityList;
    }

    public void setOptionEntityList(List<OptionEntity> optionEntityList) {
        this.optionEntityList = optionEntityList;
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }

    public OptionEntity getOptionEntity() {
        return optionEntity;
    }

    public void setOptionEntity(OptionEntity optionEntity) {
        this.optionEntity = optionEntity;
    }
}
