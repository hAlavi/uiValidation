package com.seroal.android.uivalidation;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Main Activity for first page of application
 * It will redirect user to Sign in or Sign up pages
 * @see com.seroal.android.uivalidation.SignUpActivity
 *
 * @author RouhAlavi
 * @version 0.1.0
 */
public class MainActivity extends Activity implements OnClickListener {

	Button btnSignIn;
	Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnSignIn = (Button) findViewById(R.id.btnSingIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }
	@Override
	public void onClick(View v) {
		Intent i = null;
		switch(v.getId()){
			case R.id.btnSingIn:
				i = new Intent(this,SignInActivity.class);
				break;
			case R.id.btnSignUp:
				i = new Intent(this,SignUpActivity.class);
				break;
		}
		startActivity(i);
	}


    
}
