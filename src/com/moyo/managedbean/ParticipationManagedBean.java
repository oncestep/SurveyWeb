package com.moyo.managedbean;

import com.moyo.beans.BatchEntity;
import com.moyo.beans.ParticipationEntity;
import com.moyo.dao.ParticipationDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ParticipationManagedBean {
    private long partId;
    private Long batchId;
    private Long userId;
    private Timestamp partTime;


    public long getPartId() {
        return partId;
    }

    public void setPartId(long partId) {
        this.partId = partId;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getPartTime() {
        return partTime;
    }

    public void setPartTime(Timestamp partTime) {
        this.partTime = partTime;
    }



    /*  展示当前用户所有可加入批次  */
    public List<BatchEntity> showAvailable(long userId){
        ParticipationDAO parDAO = new ParticipationDAO();
        return parDAO.findByUserId(userId);
    }

    /*  用户参与批次  */
    public String participateBatch(long userId) {
        ParticipationDAO parDAO = new ParticipationDAO();
        ParticipationEntity participation = new ParticipationEntity();
        Timestamp time = new Timestamp(System.currentTimeMillis());

        participation.setUserId(userId);
        participation.setBatchId(batchId);
        participation.setPartTime(time);
        parDAO.save(participation);

        return "user/index.xhtml";
    }

}
