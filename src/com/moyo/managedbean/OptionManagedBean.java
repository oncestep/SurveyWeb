package com.moyo.managedbean;

import com.moyo.dao.OptionDAO;
import com.moyo.beans.OptionEntity;
import com.moyo.dao.QuestionDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class OptionManagedBean {
    private long optionId;
    private String content;
    private Long questionId;

    public long getOptionId() {
        return optionId;
    }

    public void setOptionId(long optionId) {
        this.optionId = optionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    private OptionEntity getEntity(){
        OptionEntity optionEntity=new OptionEntity();
        QuestionDAO questionDAO=new QuestionDAO();

        optionEntity.setContent(content);

        return optionEntity;
    }
    public void addOption(){

    }
}
