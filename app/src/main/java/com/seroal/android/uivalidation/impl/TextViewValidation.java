package com.seroal.android.uivalidation.impl;

import android.content.Context;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.TextView;

import com.seroal.android.uivalidation.base.ValidationPair;
import com.seroal.android.uivalidation.base.Validator;
import com.seroal.android.uivalidation.base.ValidityState;

import java.util.ArrayList;

/**
 * Extending TextView for showing instant messages related to validation separately
 * and setting the error icon and message of TextViews
 *
 * @see com.seroal.android.uivalidation.base.Validator
 * @see com.seroal.android.uivalidation.impl.StringValidation
 * @see com.seroal.android.uivalidation.base.ValidationPair
 * @see com.seroal.android.uivalidation.base.ValidityState
 *
 * @author RouhAlavi
 * @version 0.1.0
 */
public class TextViewValidation extends TextView {
    Validator validator;
    ArrayList<TextView> observedTexts= new ArrayList<>();
    private static final String FIELD_NAME="getText";

    public TextViewValidation(Context context) {
        super(context);
    }

    public TextViewValidation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TextViewValidation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    public void setValidator(Validator v){
        validator = v;
    }

    public Validator getValidator(){
        return validator;
    }

    public void updateErrors(){
        if (validator.getValidity(false) == ValidityState.ERROR)
            setText(Html.fromHtml(validator.getValidity(false).getMessage()));

        for(ValidationPair vp: validator.getElements()){
            TextView tv = (TextView)vp.getElement();
            if (validator.getValidity(false) == ValidityState.ERROR) {
                //
                setText(Html.fromHtml(validator.getValidity(false).getMessage()));
                if (validator.getValidity(vp) == ValidityState.ERROR) {
                    tv.setError(validator.getValidity(vp).getMessage());

                    //Do other stuffs
                } else {
                    tv.setError(null);
                    //Do other stuffs
                }

            } else {

                tv.setError(null);
                setText(Html.fromHtml(validator.getValidity(false).getMessage()));
            }

        }

    }

    public void addObservedText(TextView ot, String validationId){
        try {
            if ((ot != null) && (!observedTexts.contains(ot))) {
                final TextView tv = ot;
                observedTexts.add(ot);
                //
                final ValidationPair element = new ValidationPair(ot, FIELD_NAME, validationId);
                validator.addElement(element);

                //
                tv.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        //
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        try {
                            if (validator.isInstantCheck()) {
                                if (validator.getValidity(false) == ValidityState.ERROR) {
                                    //
                                    setText(Html.fromHtml(validator.getValidity(false).getMessage()));
                                    if (validator.getValidity(element) == ValidityState.ERROR) {
                                        tv.setError(validator.getValidity(element).getMessage());

                                        //Do other stuffs
                                    } else {
                                        tv.setError(null);
                                        //Do other stuffs
                                    }

                                } else {

                                    tv.setError(null);
                                    setText(Html.fromHtml(validator.getValidity(false).getMessage()));
                                }
                            }
                        }
                        catch (Exception ex)
                        {
                            ex.printStackTrace();
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        //
                    }
                });
            }
        }
        catch(Exception ex){}
    }



}
