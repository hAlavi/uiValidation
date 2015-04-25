package com.seroal.android.uivalidation.impl;

import android.widget.TextView;

import com.seroal.android.uivalidation.base.ValidationPair;
import com.seroal.android.uivalidation.base.ValidityState;

/**
 * Extending StringValidation Class for implementing validation for two controls
 * and checking the confirmation data
 * @see com.seroal.android.uivalidation.impl.StringValidation
 *
 * @author RouhAlavi
 * @version 0.1.0
 */
public class DoubleValidation extends StringValidation {
    ValidationPair item;
    ValidationPair itemConfirm;
    private static final String FIELD_NAME="getText";
    private static final String COMPARE_ERROR="confirmation is not correct";

    public DoubleValidation(TextView txt, TextView txtConfirm){
        item = new ValidationPair(txt, FIELD_NAME, "");
        itemConfirm = new ValidationPair(txtConfirm, FIELD_NAME, "");
        this.addElement(item);
        this.addElement(itemConfirm);
    }

    public void setId(String id){
        item.setId(id);
        itemConfirm.setId(id);
    }
    public void setTrim(boolean isTrimmable){
        item.setTrimmed(isTrimmable);
        itemConfirm.setTrimmed(isTrimmable);
    }

    public void setCaseSen(boolean isCaseSen){
        item.setCaseSensitive(isCaseSen);
        itemConfirm.setCaseSensitive(isCaseSen);
    }

    @Override
    public ValidityState getValidity(boolean showState) {
        ValidityState vs = super.getValidity(item);
        String txt1,txt2;
        txt1= ((TextView) (item.getElement())).getText().toString();
        txt2= ((TextView) (itemConfirm.getElement())).getText().toString();
        if (!item.isCaseSensitive()) {
            txt1 = txt1.toLowerCase();
            txt2 = txt2.toLowerCase();
        }
        if (item.isTrimmed()) {
            txt1 = txt1.trim();
            txt2 = txt2.trim();
        }

        if (vs == ValidityState.VALID)
            if (!txt1.equals(txt2)) {
                vs = ValidityState.ERROR;
                vs.setMessage(item.getId()+" "+COMPARE_ERROR);

            } else vs.setMessage("<font color=#BBFF7A><strong>"+item.getId()+" is OK!</strong></font>");
        return vs;
    }
}
