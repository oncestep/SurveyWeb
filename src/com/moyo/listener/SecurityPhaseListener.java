package com.moyo.listener;

import com.moyo.beans.UserDetailEntity;

import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
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
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext extContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) extContext.getSession(true);

        Object type = session.getAttribute("type");

        String viewId = facesContext.getViewRoot().getViewId();
        NavigationHandler navHandler = facesContext.getApplication().getNavigationHandler();
        boolean permit = ((viewId.lastIndexOf("login") != -1)
                || (viewId.lastIndexOf("Login") != -1)
                || (viewId.lastIndexOf("register") != -1)
                || (viewId.lastIndexOf("Rigister") != -1)
                || (viewId.lastIndexOf("Main") != -1)
        ) ? true : false;

        //  用户管理员登录界面统一情况
        if(!permit) {
            if (type == null) {
                navHandler.handleNavigation(facesContext, null, "rejected");
            } else if ((int) type == 0) {
                if (viewId.lastIndexOf("user") == -1) {
                    navHandler.handleNavigation(facesContext, null, "rejected");
                }
            } else {
                if (viewId.lastIndexOf("user") != -1) {
                    navHandler.handleNavigation(facesContext, null, "rejected");
                }
            }
        }

//        if (!permit) {
//            if (userId == null) {
//                if (managerId == null) {
//                    navHandler.handleNavigation(facesContext, null, "rejected");
//                } else {
//                    if (type == 0) {
//                        navHandler.handleNavigation(facesContext, null, "rejected");
//                    }
//                }
//            } else {
//                if (managerId == null) {
//                    if (type == 1) {
//                        navHandler.handleNavigation(facesContext, null, "userIndex");
//                    }
//                } else {
//                    if (type == 1) {
//                        navHandler.handleNavigation(facesContext, null, "userIndex");
//                    } else {
//                        navHandler.handleNavigation(facesContext, null, "managerIndex");
//                    }
//
//                }
//            }
//        }

    }

    @Override
    public void beforePhase(PhaseEvent event) {
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
