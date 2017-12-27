package com.moyo.managedbean;

import com.moyo.beans.OptionEntity;
import com.moyo.beans.QuestionEntity;
import com.moyo.dao.OptionDAO;
import com.moyo.dao.QuestionDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean
@RequestScoped
//@SessionScoped
public class QuestionManagedBean {
    private long questionId;
    private Integer type;
    private String content;
    private Long naireId;

    /*  每题选项列表  */
    private List<OptionEntity> optList;



    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getNaireId() {
        return naireId;
    }

    public void setNaireId(Long naireId) {
        this.naireId = naireId;
    }

    public List<OptionEntity> getOptList() {
        return optList;
    }

    public void setOptList(List<OptionEntity> optList) {
        this.optList = optList;
    }

    /*  输出问题对应选项 */
    public List<OptionEntity> showAllOption(long queId) {
        QuestionDAO queDAO = new QuestionDAO();
        OptionDAO optDAO = new OptionDAO();

        QuestionEntity question = queDAO.findById(queId);
        questionId = question.getQuestionId();
        type = question.getType();
        content = question.getContent();
        naireId = question.getNaireId();

        optList = optDAO.findByQuestionId(queId);
        return optList;
    }

    /*  提交更新被选次数  */
    public void updateHits(long optId) {
        OptionDAO optDAO = new OptionDAO();
        optDAO.addHits(optId);
    }
}
