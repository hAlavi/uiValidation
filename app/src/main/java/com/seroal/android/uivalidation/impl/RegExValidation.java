package com.seroal.android.uivalidation.impl;

import com.seroal.android.uivalidation.base.RegExConfigHolder;

import java.util.regex.Pattern;

/**
 * Regular Expression Configuration manager
 * Implementation of RegExConfigHolder
 * @see com.seroal.android.uivalidation.base.RegExConfigHolder
 *
 * @author RouhAlavi
 * @version 0.1.0
 */
public class RegExValidation implements RegExConfigHolder {

    public static final String PASSWORD_ID = "password";
    public static final String EMAIL_ID = "email";
    public static final String NAME_ID = "name";
    public static final String DOB_ID = "DOB";
    public static final String FNAME_ID = "firstname";
    public static final String LNAME_ID = "lastname";

    private static final String EMAIL_ERROR = "Email is not valid!";
    private static final String PASSWORD_ERROR1 = "a-z and A-Z";
    private static final String PASSWORD_ERROR2 = "Numbers";
    private static final String PASSWORD_ERROR3 = "Symbols";
    private static final String PASSWORD_ERROR4 = "Must between 6-20 chars";

    private static final String PASSWORD_REGEX = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final String PASSWORD_REGEX1 = "((?=.*[a-z])(?=.*[A-Z]).{2,20})";
    private static final String PASSWORD_REGEX2 = "(?=.*\\d).{1,20}";
    private static final String PASSWORD_REGEX3 = "(?=.*[@#$%]).{1,20}";
    private static final String PASSWORD_REGEX4 = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    private static final String DATE_REGEX = "^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$";

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);

    @Override
    public Pattern getExpression(String id) {
        switch(id.toLowerCase()){
            case PASSWORD_ID:
                return PASSWORD_PATTERN;
            case EMAIL_ID:
                return EMAIL_PATTERN;
        }
        return null;
    }


    @Override
    public String getMatchMessage(String id, String inputStr) {
        if (inputStr!=null) {
            switch (id.toLowerCase()) {
                case PASSWORD_ID:
                    String error = "";
                    if (!Pattern.compile(PASSWORD_REGEX1).matcher(inputStr).matches())
                        error += PASSWORD_ERROR1;
                    if (!Pattern.compile(PASSWORD_REGEX2).matcher(inputStr).matches())
                        error += " "+PASSWORD_ERROR2;
                    if (!Pattern.compile(PASSWORD_REGEX3).matcher(inputStr).matches())
                        error += " "+PASSWORD_ERROR3;
                    if ((error.length()<5)&&(!Pattern.compile(PASSWORD_REGEX4).matcher(inputStr).matches()))
                        error += " "+PASSWORD_ERROR4;
                    return error.trim();

                case EMAIL_ID:
                    if (!getExpression(id).matcher(inputStr).matches())
                        return EMAIL_ERROR;
            }
        }
        return "";
    }
}
