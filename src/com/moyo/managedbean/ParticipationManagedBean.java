package com.moyo.managedbean;

import com.moyo.beans.BatchEntity;
import com.moyo.beans.ParticipationEntity;
import com.moyo.dao.ParticipationDAO;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.Timestamp;
import java.util.List;

/**
 * Add or delete user from Participation
 * you should pass the value of BatchName and UserName
 */
import com.moyo.beans.UserDetailEntity;
import com.moyo.dao.BatchDAO;
import com.moyo.dao.UserDetailDAO;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;



@ManagedBean
@SessionScoped
public class ParticipationManagedBean {
    private long partId;
    private Long batchId;
    private Long userId;
    private Timestamp partTime;

    private String batchName;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

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

    private HttpSession getHttpSession(){
        FacesContext facesContext=FacesContext.getCurrentInstance();
        HttpSession session= (HttpSession) facesContext.getExternalContext().getSession(true);
        return session;
    }
    private ParticipationEntity getEntity(){
        ParticipationEntity participationEntity=new ParticipationEntity();

        java.util.Date nowTime=new java.util.Date();
        Timestamp timestamp=new Timestamp(nowTime.getTime());

        UserDetailDAO userDetailDAO=new UserDetailDAO();
        UserDetailEntity userDetailEntity= (UserDetailEntity) userDetailDAO.findByUsername(userName).get(0);

        BatchDAO batchDAO=new BatchDAO();
        BatchEntity batchEntity= (BatchEntity) batchDAO.findByBatchName(batchName).get(0);

        participationEntity.setBatchId(userDetailEntity.getUserId());
        participationEntity.setBatchId(batchEntity.getBatchId());
        participationEntity.setPartTime(timestamp);

        return participationEntity;
    }
    public void addPart(){
        try {
            ParticipationDAO participationDAO = new ParticipationDAO();
            participationDAO.save(getEntity());
        }catch (Exception e){
            throw e;
        }
    }
    public void deletePart(){
        try{
            ParticipationEntity participationEntity=new ParticipationEntity();

            ParticipationDAO participationDAO=new ParticipationDAO();
            UserDetailDAO userDetailDAO=new UserDetailDAO();
            BatchDAO batchDAO=new BatchDAO();

            UserDetailEntity userDetailEntity= (UserDetailEntity) userDetailDAO.findByUsername(userName).get(0);
            BatchEntity batchEntity= (BatchEntity) batchDAO.findByBatchName(batchName).get(0);

            participationEntity.setBatchId(batchEntity.getBatchId());
            participationEntity.setUserId(userDetailEntity.getUserId());

            participationDAO.delete(participationEntity);
        }catch (Exception e){
            throw e;
        }
    }
}
