package com.seroal.android.uivalidation.base;

import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Base Class for Holding Each Element Data for Validation
 * Used to hold the instant of elements and field name of elements
 * Use java Reflection to access fields of UI elements by their names
 * You can use different field names for different type of validation like "getText" "isChecked"
 * @see com.seroal.android.uivalidation.base.ValidityState
 * @see java.lang.reflect.Method
 *
 * @author RouhAlavi
 * @version  0.1.0
 */
public class ValidationPair {

    private Object element;
    private String fieldName;
    private String expression;
    private String id;
    private boolean isRegex = true;
    private boolean isCaseSensitive = false;
    private boolean isTrimmed = true;
    //

    /**
     * Constructor of class
     * @param element Objects being monitored
     * @param fieldName field name of Object contains method name for accessing the property
     *                  such as "getText" for TextView or "isChecked" for CheckBox
     * @param id Id of element using for Different method implementation for each field name
     */
    public ValidationPair(Object element, String fieldName, String id){
        this.element = element;
        this.fieldName = fieldName;
        this.id = id;
    }

    /**
     * Default Constructor
     */
    public ValidationPair(){}

    /**
     * Returns Element that is being monitored
     * @return Object
     */
    public Object getElement() {
        return element;
    }

    /**
     * Change the element without changing other fields
     * @param element Can be TextView,CheckBox ,etc
     */
    public void setElement(Object element) {
        this.element = element;
    }

    /**
     * Returns Field Name
     * @return Name of field
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets Field name
     * @param fieldName Name of the field
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Static Method for accessing field in a class via getters
     * @param fieldName name of access method
     * @param o Accessible Object
     * @return Output of method o.fieldName
     */
    public static Object runGetter(String fieldName, Object o)
    {
        //  Find the correct method
        for (Method method : o.getClass().getMethods()) {
            if ((method.getName().equals(fieldName))) {

                //  Method found, run it
                try {
                    return method.invoke(o);


                } catch (IllegalAccessException | InvocationTargetException e) {
                    Log.d("", "Could not determine method: " + method.getName());
                }

            }

        }

        return null;
    }

    /**
     * Internally runs runGetter
     *
     * @return Data of Pair
     */
    public Object getFieldData(){
        return runGetter(getFieldName(),getElement());

    }

    /**
     * Returns Expression being used for validation
     * @return Expression
     */
    public String getExpression() {
        return expression;
    }

    /**
     * Sets Expression being used for validation
     * @param expression
     */
    public void setExpression(String expression) {
        this.expression = expression;
    }

    /**
     * Checks the equality of two different pairs
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof ValidationPair){
            ValidationPair vp = (ValidationPair)o;
            if ((vp.getElement().equals(this.element)&&
               (vp.getFieldName().trim().equalsIgnoreCase(this.fieldName.trim()))&&
               (vp.getId().trim().equalsIgnoreCase(this.getId().trim()))))
                return true;
        }
        return false;
    }

    /**
     * Indicates that the object uses Regular Expression or not
     * @return
     */
    public boolean isRegex() {
        return isRegex;
    }

    /**
     * Sets Use of REgular Expressions
     * @param isRegex
     */
    public void setRegex(boolean isRegex) {
        this.isRegex = isRegex;
    }

    /**
     * Returns the ID of Pair
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID of Pair
     * @param id Name of ID
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Indicates whether the pair is case sensitive or not
     * @return boolean
     */
    public boolean isCaseSensitive() {
        return isCaseSensitive;
    }

    /**
     * Sets Case sensitivity of pair
     * @param isCaseSensitive
     */
    public void setCaseSensitive(boolean isCaseSensitive) {
        this.isCaseSensitive = isCaseSensitive;
    }

    /**
     * Indicates the pair can be trimmed or not
     * @return boolean
     */
    public boolean isTrimmed() {
        return isTrimmed;
    }

    /**
     * Sets the trim option
     * @param isTrimmed
     */
    public void setTrimmed(boolean isTrimmed) {
        this.isTrimmed = isTrimmed;
    }
}
