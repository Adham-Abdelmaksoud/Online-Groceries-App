package com.example.online_groceries_app.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.online_groceries_app.R;
import com.example.online_groceries_app.databinding.ActivityMobileNumberBinding;

public class MobileNumberActivity extends AppCompatActivity {

    private ActivityMobileNumberBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mobile_number);

        binding.textEdit.requestFocus();
        binding.textEdit.setInputType(InputType.TYPE_CLASS_PHONE);
        Selection.setSelection(binding.textEdit.getText(), binding.textEdit.getText().length());
        binding.textEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().startsWith("      +880 ")) {
                    binding.textEdit.setText("      +880 ");
                    Selection.setSelection(binding.textEdit.getText(), binding.textEdit.getText().length());
                }
            }
        });
        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MobileNumberActivity.this, SignInActivity.class);
                startActivity(intent);
            }
        });
        binding.arrowFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MobileNumberActivity.this, ConfirmationCodeActivity.class);
                startActivity(intent);
            }
        });
    }
}