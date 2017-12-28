package com.moyo.validator;

import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class DobValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

        Date dob = (Date) value;
        Date nowDay = new Date();
        if (dob.compareTo(nowDay) > 0) {

            FacesMessage message = new FacesMessage();
            message.setSummary("Invalid Date");
            message.setDetail("The Date Entered is later than Today's Date");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }

}