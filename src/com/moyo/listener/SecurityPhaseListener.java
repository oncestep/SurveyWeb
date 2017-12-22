package com.moyo.listener;

import com.moyo.beans.UserDetailEntity;

import javax.faces.application.Application;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpSession;

public class SecurityPhaseListener implements PhaseListener {
    @Override
    public void afterPhase(PhaseEvent phaseEvent) {

    }

    @Override
    public void beforePhase(PhaseEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extContext =facesContext.getExternalContext();
        HttpSession session =(HttpSession)extContext.getSession(true);

        UserDetailEntity user = (UserDetailEntity) session.getAttribute("user");

        UIViewRoot uiv = new UIViewRoot();
        String viewId = facesContext.getViewRoot().getViewId();
        viewId = viewId.substring(1,viewId.length()-6);
        if(viewId != "userLogin" && viewId != "userRegister" && (user == null)){
            uiv.setViewId("/userLogin.xhtml");
            facesContext.setViewRoot(uiv);
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return null;
    }
}
