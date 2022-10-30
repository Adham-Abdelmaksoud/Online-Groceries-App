package com.example.online_groceries_app.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.example.online_groceries_app.R;
import com.example.online_groceries_app.databinding.ActivityConfirmationCodeBinding;

public class ConfirmationCodeActivity extends AppCompatActivity {

    private ActivityConfirmationCodeBinding binding;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_confirmation_code);

        binding.textEdit.setInputType(InputType.TYPE_CLASS_PHONE);
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmationCodeActivity.this, MobileNumberActivity.class);
                startActivity(intent);
            }
        });
        binding.textEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(binding.textEdit.getText().length() > 4){
                    binding.textEdit.setText(binding.textEdit.getText().subSequence(0, 4));
                }
                Selection.setSelection(binding.textEdit.getText(), binding.textEdit.getText().length());
            }
        });
        binding.resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ConfirmationCodeActivity.this, "Resend Code Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        binding.arrowFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmationCodeActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        });
    }
}