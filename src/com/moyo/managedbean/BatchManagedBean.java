package com.moyo.managedbean;

import com.moyo.beans.BatchEntity;
import com.moyo.dao.BatchDAO;
import com.moyo.dao.ParticipationDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Timestamp;
import java.util.List;

@ManagedBean
@SessionScoped
public class BatchManagedBean {
    private long batchId;
    private String batchName;
    private String description;
    private Timestamp createTime;
    private Long managerId;


    public long getBatchId() {
        return batchId;
    }

    public void setBatchId(long batchId) {
        this.batchId = batchId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }


    /*  查询当前用户未加入的所有批次  */
    public List<BatchEntity> showElseBatch(long userId) {

        try {
            BatchDAO batDAO = new BatchDAO();
            List<BatchEntity> batList = batDAO.findElseBatch(userId);
            return batList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
