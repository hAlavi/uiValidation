package com.seroal.android.uivalidation.impl;

import android.app.Activity;
import android.util.Log;

import com.seroal.android.uivalidation.base.RegExConfigHolder;
import com.seroal.android.uivalidation.base.ValidationPair;
import com.seroal.android.uivalidation.base.Validator;
import com.seroal.android.uivalidation.base.ValidityState;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Implementation of Validator for Manipulating Strings
 * this version only includes regular expression manipulations
 * @see com.seroal.android.uivalidation.base.Validator
 *
 * @author RouhAlavi
 * @version 0.1.0
 */
public class StringValidation implements Validator{
    List<ValidationPair> elements = new ArrayList<>();
    Activity activity = null;
    RegExConfigHolder rxConfig= null;
    boolean instantCheck = true;

    @Override
    public ValidityState getValidity(boolean showState) {
        StringBuilder sb = new StringBuilder();
        String name = "";
        ValidityState vs = getDefaultState();
        for(ValidationPair vp: elements)
        {
            // check if is regular expression
            if (vp.isRegex()) {
                // access to the field and check the validation using regular expression
                if (rxConfig!=null) {
                    Pattern pt = rxConfig.getExpression(vp.getId());
                    name= String.valueOf(pt.matcher(vp.getFieldData().toString()).matches());
                        if (!pt.matcher(vp.getFieldData().toString()).matches())
                            sb.append(rxConfig.getMatchMessage(vp.getId(),
                                    vp.getFieldData().toString()));



                }
            }
           // Debug only
           // vs.setMessage(runGetter(vp.getFieldName(),vp.getElement()).toString()+":"+name);
        }
        if (sb.toString().trim().length()>0)
            vs= ValidityState.ERROR;
        else
            vs= ValidityState.VALID;
        vs.setMessage(sb.toString().trim());
        return vs;
    }



    @Override
    public String getName() {
        return "StringComplexValidationV1";
    }

    @Override
    public void setElements(List<ValidationPair> elements) {
        this.elements = elements;
    }

    @Override
    public List<ValidationPair> getElements() {
        return elements;
    }

    @Override
    public ValidityState getDefaultState() {
        return ValidityState.VALID;
    }

    @Override
    public void addElement(ValidationPair element) {
        if (!elements.contains(element))
            elements.add(element);
    }

    @Override
    public void deleteElement(ValidationPair element) {
        elements.remove(element);
    }

    @Override
    public boolean hasElement(ValidationPair element) {
        return elements.contains(element);
    }

    @Override
    public void setActivity(Activity act) {
        activity = act;
    }

    @Override
    public Activity getActivity() {
        return activity;
    }

    @Override
    public void setRegExConfigHolder(RegExConfigHolder rxConfig) {
        this.rxConfig = rxConfig;
    }

    @Override
    public ValidityState getValidity(ValidationPair vp) {
        StringBuilder sb = new StringBuilder();
        ValidityState vs = getDefaultState();

        if (vp.isRegex()) {
            if (rxConfig != null) {
                Pattern pt = rxConfig.getExpression(vp.getId());
                //
                if (!pt.matcher(vp.getFieldData().toString()).matches()) {
                    sb.append(rxConfig.getMatchMessage(vp.getId(),
                            vp.getFieldData().toString()));
                    Log.d(getName(),sb.toString());
                }
            }
        }

        if (sb.length()>0)
            vs= ValidityState.ERROR;
        else
            vs = ValidityState.VALID;

        vs.setMessage(sb.toString().trim());
        //Debug only
        //vs.setMessage(runGetter(vp.getFieldName(),vp.getElement()).toString()+":"+ptrn+":"+vs.toString());

        return vs;
    }

    @Override
    public boolean isInstantCheck() {
        return instantCheck;
    }

    @Override
    public void setInstantCheck(boolean iCheck) {
        instantCheck = iCheck;
    }
}
