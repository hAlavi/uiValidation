package com.seroal.android.uivalidation.base;

import java.util.regex.Pattern;

/**
 * Regular Expression Interface for Managing and Holding Expressions
 * @see com.seroal.android.uivalidation.base.Validator
 *
 * @author RouhAlavi
 * @version 0.1.0
 */
public interface RegExConfigHolder {
    /**
     * Returns the Pattern related to id
     * @param id Name of Methos
     * @return Pattern
     */
    public Pattern getExpression(String id);

    /**
     * Get Matching Error Message
     * @param id Name of Method
     * @param inputStr Input String
     * @return Message
     */
    public String getMatchMessage(String id, String inputStr);

}
