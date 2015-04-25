package com.seroal.android.uivalidation.base;

import android.app.Activity;

import java.util.List;

/**
 * Base Interface for Different Validation Strategies
 * Capable of being in contact with different fields of objects using definition of ValidationPair
 * @see com.seroal.android.uivalidation.base.ValidationPair
 * @see com.seroal.android.uivalidation.base.ValidityState
 *
 * @author RouhAlavi
 * @version 0.1.0
 */
public interface Validator {
    /**
     * Returns validity of all elements stored in using implemented strategy
     * @param showState indicates that the validity check will be shown seperately to user or not
     * @return state of validation
     */
    public ValidityState getValidity(boolean showState);

    /**
     * Returns name of implemented strategy
     * will be used for debugging and logging
     * @return name
     */
    public String getName();

    /**
     * Set elements of validator
     * @param elements List of Elements
     */
    public void setElements(List<ValidationPair> elements);

    /**
     * Returns elements of validator
     * @return List of elements
     */
    public List<ValidationPair> getElements();

    /**
     * Returns default state of Validator
     * Will be used when default state implementing needed
     * @return Validity State
     */
    public ValidityState getDefaultState();

    /**
     * Add Element to the list of elements
     * @param element Element
     */
    public void addElement(ValidationPair element);

    /**
     * Delete element from list of elements
     * @param element Element
     */
    public void deleteElement(ValidationPair element);

    /**
     * Detects whether the element is in list or not
     * @param element Element
     * @return boolean
     */
    public boolean hasElement(ValidationPair element);

    /**
     * Set the activity for updating grapgical elements directly or showing toasts!
     * Used when want to implement getValidity(true)
     * @param act Current Activity
     */
    public void setActivity(Activity act);

    /**
     * Returns current activity related to data validation
     * @return Activity
     */
    public Activity getActivity();

    /**
     * Sets Regular Expression Manager
     * Used for Dependency Injection **
     * @param rxConfig RegEx Manager
     */
    public void setRegExConfigHolder(RegExConfigHolder rxConfig);

    /**
     * Returns Validity of specific element
     * @param vp Element
     * @return boolean
     */
    public ValidityState getValidity(ValidationPair vp);

    /**
     * Indicates whether the implementation should check the validity instantly or not
     * Used within the implementation of User Interface listeners
     * @return is instant check
     */
    public boolean isInstantCheck();

    /**
     * Sets the instant check to Interact instantly with UI listeners
     * @param iCheck  is Instant check
     */
    public void setInstantCheck(boolean iCheck);
}
