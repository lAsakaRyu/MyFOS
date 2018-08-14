package com.audio.yametech.myfos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.audio.yametech.myfos.Entity.InstanceDataHolder;
import com.audio.yametech.myfos.Entity.Security;
import com.audio.yametech.myfos.Entity.Verification;

public class PasswordActivity extends AppCompatActivity {
    private EditText oldPassEditText;
    private EditText newPassEditText;
    private EditText conPassEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        oldPassEditText = (EditText) findViewById(R.id.oldPassEditText);
        newPassEditText = (EditText) findViewById(R.id.newPassEditText);
        conPassEditText = (EditText) findViewById(R.id.conPassEditText);
    }

    public void changePasswordButtonPressed(View event){
        String username = InstanceDataHolder.getInstance().get_ActiveStaff().get_ID();
        String oldPass = oldPassEditText.getText().toString();
        Verification verification = InstanceDataHolder.getInstance().get_DbHelper().verifyLoginCredential(username,oldPass);
        if(verification.get_VerificationStatus() == 2){
            String newPass = newPassEditText.getText().toString();
            if(oldPass.equals(newPass)){
                newPassEditText.setError("Old and New Password cannot be the same.");
                newPassEditText.requestFocus();
            }
            else if(newPass.length()<5){
                newPassEditText.setError("Password must be greater than 4 characters.");
                newPassEditText.requestFocus();
            }
            else if(newPass.equals(conPassEditText.getText().toString())){
                InstanceDataHolder.getInstance().get_DbHelper().updateSecurityInfo(new Security(verification.get_ID(),username,newPass));
                super.onBackPressed();
                Toast.makeText(this,"Password successfully updated.",Toast.LENGTH_SHORT).show();
            }
            else{
                conPassEditText.setError("Password and Confirm Password must be the same.");
                conPassEditText.requestFocus();
            }
        }
        else{
            oldPassEditText.setError("Incorrect Old Password.");
            oldPassEditText.requestFocus();
        }
    }
}
