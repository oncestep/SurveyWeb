package com.moyo.Beans;

import javax.persistence.*;

@Entity
@Table(name = "Question", schema = "dbo", catalog = "SurveyWeb")
public class QuestionEntity {
    private long questionId;
    private Integer type;
    private Long order;
    private String content;
    private Long naireId;

    @Id
    @Column(name = "questionId", nullable = false)
    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "order", nullable = true)
    public Long getOrder() {
        return order;
    }

    public void setOrder(Long order) {
        this.order = order;
    }

    @Basic
    @Column(name = "content", nullable = true, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "naireId", nullable = true)
    public Long getNaireId() {
        return naireId;
    }

    public void setNaireId(Long naireId) {
        this.naireId = naireId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        QuestionEntity that = (QuestionEntity) o;

        if (questionId != that.questionId) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (naireId != null ? !naireId.equals(that.naireId) : that.naireId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (questionId ^ (questionId >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (naireId != null ? naireId.hashCode() : 0);
        return result;
    }
}
