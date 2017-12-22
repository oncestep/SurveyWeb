package com.moyo.managedbean;

import com.moyo.dao.OptionDAO;

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

    public void updateHits(String optId) {
        OptionDAO optDAO = new OptionDAO();

        optDAO.addHits(optId);
    }
}
