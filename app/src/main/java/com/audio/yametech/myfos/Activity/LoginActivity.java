package com.audio.yametech.myfos.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.audio.yametech.myfos.Entity.InstanceDataHolder;
import com.audio.yametech.myfos.Entity.DBHelper;
import com.audio.yametech.myfos.Entity.Security;
import com.audio.yametech.myfos.Entity.Staff;
import com.audio.yametech.myfos.Entity.Verification;
import com.audio.yametech.myfos.R;

import java.util.List;


public class LoginActivity extends AppCompatActivity {

    private UserLoginTask mAuthTask = null;


    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        InstanceDataHolder.getInstance().set_DbHelper(new DBHelper(this));
    }

    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        mUsernameView.setError(null);
        mPasswordView.setError(null);

        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_field_required));
            focusView = mUsernameView;
            cancel = true;
        } else if (!isUsernameValid(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_username));
            focusView = mUsernameView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);
            mAuthTask = new UserLoginTask(username, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isUsernameValid(String username) {
        return username.contains("S");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    protected Activity getThisActivity(){
        return this;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String username;
        private final String password;
        private Verification result = new Verification(2);

        UserLoginTask(String email, String password) {
            this.username = email;
            this.password = password;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }
            List<Security> securityList = InstanceDataHolder.getInstance().get_DbHelper().getAllSecurityList();
            //Log.i("MyFOS",securityList.toString());
            List<Staff> staffList = InstanceDataHolder.getInstance().get_DbHelper().getAllStaffList();
            if(staffList.isEmpty()) {
                InstanceDataHolder.getInstance().get_DbHelper().addNewStaff(new Staff(InstanceDataHolder.getInstance().get_DbHelper().getNewID("staff", "S"), "Alex Chew", "Manager", "Male", "22/09/1997", "970922565225", "0163844970", "chewhw-wa15@student.tarc.edu.my", "Working", "970922", "08/08/2018", "?"));
            }
            if(securityList.isEmpty()) {
                InstanceDataHolder.getInstance().get_DbHelper().addNewSecurity(new Security(InstanceDataHolder.getInstance().get_DbHelper().getNewID("security","X"),"S1001","12341234"));
            }
            result = InstanceDataHolder.getInstance().get_DbHelper().verifyLoginCredential(username,password);
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            if (success) {
                switch(result.get_VerificationStatus()){
                    case 0:
                        mUsernameView.setError(getString(R.string.error_invalid_username));
                        mUsernameView.requestFocus();
                        break;
                    case 1:
                        mPasswordView.setError(getString(R.string.error_incorrect_password));
                        mPasswordView.requestFocus();
                        break;
                    default:
                        Staff loginStaff = InstanceDataHolder.getInstance().get_DbHelper().getStaffByID(result.get_StaffID());
                        InstanceDataHolder.getInstance().set_ActiveStaff(loginStaff);
                        Intent intent;
                        if(password.equals(loginStaff.get_DefaultPass())) {
                            Toast.makeText(getThisActivity(), "First time login. Please change your password!", Toast.LENGTH_LONG).show();
                            intent = new Intent(getBaseContext(), PasswordActivity.class);
                            InstanceDataHolder.getInstance().set_FirstTimeLogin(true);
                            startActivity(intent);
                        }
                        else {
                            intent = new Intent(getBaseContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                }
            }
            showProgress(false);
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

