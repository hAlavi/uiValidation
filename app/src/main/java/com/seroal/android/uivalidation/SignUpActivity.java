package com.seroal.android.uivalidation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.seroal.android.uivalidation.base.RegExConfigHolder;
import com.seroal.android.uivalidation.base.Validator;
import com.seroal.android.uivalidation.base.ValidityState;
import com.seroal.android.uivalidation.impl.DoubleValidation;
import com.seroal.android.uivalidation.impl.RegExValidation;
import com.seroal.android.uivalidation.impl.SimpleValidation;
import com.seroal.android.uivalidation.impl.TextViewValidation;
import com.seroal.android.uivalidation.util.DateFragment;

import java.util.Calendar;

/**
 * Activity for Sign up
 * All classes show their power to user in this class
 * @see com.seroal.android.uivalidation.impl.DoubleValidation
 * @see com.seroal.android.uivalidation.base.Validator
 *
 * @author RouhAlavi
 * @version 0.1.0
 */
public class SignUpActivity extends Activity {
    Validator passwordValidator;
    Validator emailValidator;
    Validator simpleValidator;
    RegExConfigHolder rxHolder;
    TextViewValidation tvvPassword;
    TextViewValidation tvvEmail;
    TextViewValidation tvvDateOfBirth;
    TextView tvErrorMsg;
    EditText password;
    EditText passwordConfirm;
    EditText email;
    EditText emailConfirm;
    EditText gender;
    EditText dateOfBirth;
    CheckBox chUpdate;
    Calendar dobCalendar = Calendar.getInstance();
    DateFragment dateFragment;
    Button signUpBtn;
    private String[] genderList={"Male","Female"};
    private static final String ERROR_MESSAGE_F="Bro do you even input?";
    private static final String ERROR_MESSAGE="Please fill the form correctly based on below tips!";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        //
        setupUI(findViewById(R.id.activityMain));
        //
        tvErrorMsg = (TextView) findViewById(R.id.tvErrorMsg);
        //
        tvvPassword = (TextViewValidation) findViewById(R.id.passwordValidation);
        tvvEmail = (TextViewValidation) findViewById(R.id.emailValidation);
        tvvDateOfBirth = (TextViewValidation) findViewById(R.id.dobValidation);
        //
        email = (EditText) findViewById(R.id.etEmail);
        emailConfirm = (EditText) findViewById(R.id.etEmailConfirm);
        //
        password = (EditText) findViewById(R.id.etPass);
        passwordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
        //
        chUpdate = (CheckBox) findViewById(R.id.chUpdate);
        //
        gender = (EditText) findViewById(R.id.etGender);
        dateOfBirth = (EditText) findViewById(R.id.etDateOfBirth);
        //
        signUpBtn = (Button) findViewById(R.id.btnRegister);
        //
        injectDependencies();
        //
        initializeListeners();
    }

    /**
     * Inject dependencies of higher level implementations
     * As it clears, the sign up activity is based on
     * Dependency Injection Design Pattern
     */
    private void injectDependencies() {
        //
        rxHolder = new RegExValidation();
        //
        passwordValidator = new DoubleValidation(password,passwordConfirm);
        passwordValidator.setRegExConfigHolder(rxHolder);
        passwordValidator.setInstantCheck(true);
        ((DoubleValidation)passwordValidator).setId(RegExValidation.PASSWORD_ID);
        ((DoubleValidation)passwordValidator).setCaseSen(true);
        ((DoubleValidation)passwordValidator).setTrim(false);
        //
        emailValidator = new DoubleValidation(email,emailConfirm);
        emailValidator.setRegExConfigHolder(rxHolder);
        emailValidator.setInstantCheck(true);
        ((DoubleValidation)emailValidator).setId(RegExValidation.EMAIL_ID);
        //
        simpleValidator = new SimpleValidation(dateOfBirth,RegExValidation.DOB_ID);
        //Simple validation does not use the RegEx Manager
        //simpleValidator.setRegExConfigHolder(rxHolder);
        simpleValidator.setInstantCheck(true);
        simpleValidator.setActivity(this);
        //
        tvvDateOfBirth.setValidator(simpleValidator);
        tvvDateOfBirth.addObservedText(dateOfBirth, RegExValidation.DOB_ID);
        //
        tvvPassword.setValidator(passwordValidator);
        tvvPassword.addObservedText(password, RegExValidation.PASSWORD_ID);
        tvvPassword.addObservedText(passwordConfirm, RegExValidation.PASSWORD_ID);
        //
        tvvEmail.setValidator(emailValidator);
        tvvEmail.addObservedText(email, RegExValidation.EMAIL_ID);
        tvvEmail.addObservedText(emailConfirm, RegExValidation.EMAIL_ID);
        //
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
    public void setupUI(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(SignUpActivity.this);
                    return false;
                }

            });
        }
    }
    /**
     * Initialize Listeners for different UI controls
     */
    private void initializeListeners(){
        setUpdateListener();
        setDateOfBirthSpinner();
        setGenderSpinner();
        setUpSignUp();
    }

    private void setUpdateListener(){
        chUpdate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                passwordValidator.setInstantCheck(isChecked);
                emailValidator.setInstantCheck(isChecked);
            }
        });
        //
    }

    private void setUpSignUp(){
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (emailValidator.getValidity(false)== ValidityState.ERROR &&
                        (passwordValidator.getValidity(false) == ValidityState.ERROR)&&
                        (simpleValidator.getValidity(false)== ValidityState.ERROR))
                    tvErrorMsg.setText(ERROR_MESSAGE_F);
                else if (emailValidator.getValidity(false)== ValidityState.ERROR ||
                        (passwordValidator.getValidity(false) == ValidityState.ERROR)||
                        (simpleValidator.getValidity(false)== ValidityState.ERROR)) {

                    Toast.makeText(SignUpActivity.this, ERROR_MESSAGE, Toast.LENGTH_LONG).show();
                    // Updates error on UI
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvvEmail.updateErrors();
                            tvvDateOfBirth.updateErrors();
                            tvvPassword.updateErrors();
                            tvErrorMsg.setText("");
                        }
                    });
                }
                else
                    SignUpActivity.this.finish();
            }
        });
    }
    private void setDateOfBirthSpinner(){
        dateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showDatePickerDialog(v);
                }
                catch (Exception ex){
                    Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void showDatePickerDialog(View v) {
        dateFragment = new DateFragment();
        dateFragment.setTextView(dateOfBirth);
        dateFragment.setCalendar(dobCalendar);
        dateFragment.setCancelable(true);
        dateFragment.show(getFragmentManager(), "Date of birth");

    }

    private void setGenderSpinner(){
        final ArrayAdapter<String> spinnerGenders = new  ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, genderList);
        final Context context = this;

        gender.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Gender")
                        .setAdapter(spinnerGenders, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                gender.setText(genderList[which]);
                                dialog.dismiss();
                            }
                        })
                        .create().show();
            }
        });
    }
}