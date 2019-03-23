package com.planzappdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.planzappdemo.Connection.LocalHostConnection;

import java.io.IOException;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    TextInputEditText name, username, password, confirmPassword, age, address, lat, lon, pin;

    MaterialButton signUpButton;

    String name_val, username_val, password_val, confirmPassword_val, age_val, address_val, lat_val, lon_val, pin_val;

    Map<String, String> keyValMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        init();

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getValues();
                } catch (PasswordMismatchException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public void getValues() throws PasswordMismatchException {
        name_val = name.getEditableText().toString().trim();
        keyValMap.put("name", name_val);

        username_val = username.getEditableText().toString().trim();
        keyValMap.put("username", username_val);

        password_val = password.getEditableText().toString().trim();
        keyValMap.put("password", password_val);

        confirmPassword_val = confirmPassword.getEditableText().toString().trim();

        if(!(confirmPassword_val.equalsIgnoreCase(password_val))){
            throw new PasswordMismatchException("Password and confirm password do not match");
        }

        age_val = age.getEditableText().toString().trim();
        keyValMap.put("age", age_val);

        address_val = address.getEditableText().toString().trim();
        keyValMap.put("address", address_val);

        lat_val = lat.getEditableText().toString().trim();
        keyValMap.put("lat", lat_val);

        lon_val = lon.getEditableText().toString().trim();
        keyValMap.put("lon", lon_val);

        pin_val = pin.getEditableText().toString().trim();
        keyValMap.put("pin", pin_val);
    }

    public void submitData(){

        LocalHostConnection localHostConnection = new LocalHostConnection(this.getApplicationContext());
        try {
            localHostConnection.POSTRequest(keyValMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(){
        name = (TextInputEditText) findViewById(R.id.name_editText);
        username =(TextInputEditText) findViewById(R.id.username_editText);
        password =(TextInputEditText) findViewById(R.id.password_editText);
        confirmPassword =(TextInputEditText) findViewById(R.id.confirmPassword_editText);
        age = (TextInputEditText) findViewById(R.id.age_editText);
        address = (TextInputEditText) findViewById(R.id.address_editText);
        lat = (TextInputEditText) findViewById(R.id.lat_editText);
        lon = (TextInputEditText) findViewById(R.id.lon_editText);
        pin = (TextInputEditText) findViewById(R.id.pin_editText);
        signUpButton = (MaterialButton) findViewById(R.id.submit_signup);
    }

    private class PasswordMismatchException extends Exception {
        public PasswordMismatchException(String s) {
            super(s);
        }
    }
}
