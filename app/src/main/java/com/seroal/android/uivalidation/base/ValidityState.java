package com.seroal.android.uivalidation.base;

/**
 * Enumeration of States of Validity Checking
 * @see com.seroal.android.uivalidation.base.Validator
 *
 * @author RouhAlavi
 * @version 0.1.0
 */
public enum ValidityState {
    // States
    VALID, WARNING_VALID, ERROR;
    //
    private String message = null;

    /**
     * Sets the message of State
     * @param msg
     */
    public void setMessage(String msg){
        message = msg;
    }

    /**
     * Returns the message of state
     * @return
     */
    public String getMessage(){
        return message;
    }

}
