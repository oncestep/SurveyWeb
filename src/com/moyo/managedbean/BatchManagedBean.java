package com.moyo.managedbean;

import com.moyo.beans.BatchEntity;
import com.moyo.dao.BatchDAO;
import com.moyo.dao.ParticipationDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Timestamp;

import com.moyo.beans.ParticipationEntity;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class BatchManagedBean {
    private long batchId;
    private String batchName;
    private String description;
    private Timestamp createTime;
    private Long managerId;

    private List<BatchEntity> batchList=new ArrayList<>();
    private List<BatchEntity> batchAllList;


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

    public void setBatchList(List<BatchEntity> batchList) {
        this.batchList = batchList;
    }

    public List<BatchEntity> getBatchAllList() {
        BatchDAO batDAO = new BatchDAO();
        batchAllList = batDAO.findAll();
        return batchAllList;
    }

    public void setBatchAllList(List<BatchEntity> batchAllList) {
        this.batchAllList = batchAllList;
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


    /*  查询当前批次  */
    public List<BatchEntity> getBatchList() {
        BatchDAO batchDAO=new BatchDAO();
        HttpSession session=getHttpSession();
        Long managerId= (Long) session.getAttribute("managerId");
        batchList = batchDAO.findByManagerId(managerId);
        return batchList;
    }

    /**
     * Get HttpSession
     * @return HttpSession
     */
    private HttpSession getHttpSession(){
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(true);
        return session;
    }

    /**
     * Get an BatchEntity Object
     * @return BatchEntity Object
     */
    private BatchEntity getEntity(){
        java.util.Date nowTime=new java.util.Date();
        Timestamp timestamp=new Timestamp(nowTime.getTime());
        HttpSession session=getHttpSession();
        Long managerId= (Long) session.getAttribute("managerId");
        BatchEntity batchEntity=new BatchEntity();
        batchEntity.setBatchName(batchName);
        batchEntity.setCreateTime(timestamp);
        batchEntity.setManagerId(managerId);
        batchEntity.setDescription(description);
        return batchEntity;
    }

    /**
     * Add Batch to database
     */
    public void addBatch(){
        try {
            BatchDAO batchDAO = new BatchDAO();
            batchDAO.save(getEntity());
        }catch (Exception e){
            throw e;
        }
    }
    /**
     * delete Batch from database
     */
    public void deleteBatch(){
        try {
            BatchDAO batchDAO = new BatchDAO();
            ParticipationDAO participationDAO=new ParticipationDAO();

            Long batchId= (Long) batchDAO.findByBatchName(batchName).get(0);

            BatchEntity batchEntity = new BatchEntity();
            ParticipationEntity participationEntity=new ParticipationEntity();

            batchEntity.setBatchId(batchId);
            participationEntity.setBatchId(batchId);

            batchDAO.delete(batchEntity);
            participationDAO.delete(participationEntity);
        }catch (Exception e){
            throw e;
        }
    }

}
