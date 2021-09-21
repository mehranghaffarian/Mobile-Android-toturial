package com.example.practice1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText email;
    private EditText userPass;
    private EditText userPassConfirm;
    private CheckBox showPass;
    private CheckBox agree;
    private RadioGroup genders;
    private Spinner countries;
    private Button register;

    private boolean registrationSuccessful = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.userName);
        email = findViewById(R.id.userEmail);
        userPass = findViewById(R.id.userPass);
        userPassConfirm = findViewById(R.id.userConfirmPass);
        showPass = findViewById(R.id.showPass);
        genders = findViewById(R.id.genders);
        countries = findViewById(R.id.countries);
        agree = findViewById(R.id.agree);
        register = findViewById(R.id.register);

        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    userPass.setInputType(InputType.TYPE_CLASS_TEXT);
                    userPassConfirm.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    userPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    userPassConfirm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                userPass.setSelection(userPass.getText().length());
                userPassConfirm.setSelection(userPassConfirm.getText().length());
            }
        });
        agree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Toast toast = Toast.makeText(MainActivity.this, "you agreed to the terms of policy", Toast.LENGTH_SHORT);

                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.color.green);
                    toast.show();
                } else if(!registrationSuccessful){
                    Toast toast = Toast.makeText(MainActivity.this, "you have to agree to the terms of policy for registration", Toast.LENGTH_SHORT);

                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.color.red);
                    toast.show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (canRegister()) {
                    emptyFields();

                    Toast toast = Toast.makeText(MainActivity.this, "You are registered successfully", Toast.LENGTH_SHORT);

                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.color.green);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "Please fill the form correctly", Toast.LENGTH_SHORT);

                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.color.red);
                    toast.show();
                }
            }
        });
    }

    private void emptyFields() {
        registrationSuccessful = true;

        name.setText("");
        email.setText("");
        userPass.setText("");
        userPassConfirm.setText("");
        agree.setChecked(false);
        showPass.setChecked(false);

        registrationSuccessful = false;
    }

    private boolean canRegister() {
        if (agree.isChecked() && userPass.getText().toString().equals(userPassConfirm.getText().toString()) && userPass.getText().toString().length() > 4 && email.getText().toString().contains("@") && !name.getText().toString().equals("")) {
            if(countries.getSelectedItem().toString().equals("CHOOSE"))
                return false;

            switch (genders.getCheckedRadioButtonId()) {
                case R.id.male:
                case R.id.female:
                    return true;
                default:
                    return false;
            }
        }
        return false;
    }
}