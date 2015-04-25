package com.seroal.android.uivalidation.impl;

import android.widget.TextView;

import com.seroal.android.uivalidation.base.ValidationPair;
import com.seroal.android.uivalidation.base.ValidityState;

import java.util.List;

/**
 * Simple extending of StringValidation which is useful when simple manipulations needed
 * @see com.seroal.android.uivalidation.base.Validator
 *
 * @author RouhAlavi
 * @version 0.1.0
 */
public class SimpleValidation extends StringValidation{
    TextView tvElement;
    private static final String FIELD_NAME="getText";
    private static final String ERROR_STR=" is empty.";
    ValidationPair item;

    public SimpleValidation(TextView tv,String id){
        item = new ValidationPair(tv, FIELD_NAME, id);
        addElement(item);
    }



    @Override
    public ValidityState getValidity(boolean showState) {
        List<ValidationPair> elements = getElements();
        ValidityState vs = getDefaultState();
        //
        for(ValidationPair vp: elements)
            if (vp.getFieldData()!=null)
                if (vp.getFieldData().toString().trim().length()==0){
                    vs = ValidityState.ERROR;
                    //Toast.makeText(activity,"ERROR1",Toast.LENGTH_LONG).show();
                    vs.setMessage(vp.getId() + ERROR_STR);
                    return vs;
                }
        //Toast.makeText(activity,"VALID1",Toast.LENGTH_LONG).show();
        vs = ValidityState.VALID;
        vs.setMessage(" ");
        return vs;
    }

    @Override
    public ValidityState getValidity(ValidationPair vp) {
        ValidityState vs = getDefaultState();
        //
        if (vp.getFieldData()!=null)
            if (vp.getFieldData().toString().trim().length()==0) {
                vs = ValidityState.ERROR;
                //Toast.makeText(activity,"ERROR",Toast.LENGTH_LONG).show();
                vs.setMessage(vp.getId() + ERROR_STR);
            }
        //Toast.makeText(activity,"VALID",Toast.LENGTH_LONG).show();
        vs = ValidityState.VALID;
        vs.setMessage(" ");
        return vs;
    }
}
