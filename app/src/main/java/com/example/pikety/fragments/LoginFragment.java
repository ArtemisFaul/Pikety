package com.example.pikety.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.pikety.R;
import com.example.pikety.api.PiketSession;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;

import io.michaelrocks.libphonenumber.android.NumberParseException;
import io.michaelrocks.libphonenumber.android.PhoneNumberUtil;
import io.michaelrocks.libphonenumber.android.Phonenumber;
import me.grishka.appkit.Nav;
import me.grishka.appkit.api.ErrorResponse;
import me.grishka.appkit.api.SimpleCallback;

public class LoginFragment extends BaseToolbarFragment{

    private EditText phoneInput, codeInput;
    private Button resendBtn, nextBtn;
    private CountryCodePicker countryCodePicker;
    private LinearLayout resendCodeLayout;
    private boolean sentCode=false;
    private PhoneNumberUtil phoneNumberUtil;

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
//		setTitle(R.string.login);
    }

    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.login, container, false);

        phoneNumberUtil=PhoneNumberUtil.createInstance(getActivity());
        phoneInput=view.findViewById(R.id.phone_input);
        codeInput=view.findViewById(R.id.code_input);
        resendBtn=view.findViewById(R.id.resend_code);
        nextBtn=view.findViewById(R.id.next);
        countryCodePicker=view.findViewById(R.id.country_code_picker);
        resendCodeLayout=view.findViewById(R.id.resend_code_layout);

        codeInput.setVisibility(View.GONE);
        resendCodeLayout.setVisibility(View.GONE);

        countryCodePicker.registerPhoneNumberTextView(phoneInput);
        nextBtn.setOnClickListener(this::onNextClick);
        resendBtn.setOnClickListener(this::onResendClick);
        phoneInput.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2){

            }

            @Override
            public void afterTextChanged(Editable editable){
                try{
                    Phonenumber.PhoneNumber number=phoneNumberUtil.parse(phoneInput.getText().toString(), countryCodePicker.getSelectedCountryNameCode());
                    String country=phoneNumberUtil.getRegionCodeForNumber(number);
                    if(country!=null)
                        countryCodePicker.setCountryForNameCode(country);
                }catch(NumberParseException igonre){}
            }
        });

        return view;
    }

    private void onNextClick(View v){
        if(sentCode){
            PiketSession.userToken="4546465456465465";
            PiketSession.userID="454654564654654654";
            PiketSession.write();
            Nav.goClearingStack(getActivity(), HomeFragment.class, null);
        }else{
            sentCode=true;
            phoneInput.setEnabled(false);
            countryCodePicker.setClickable(false);
            codeInput.setVisibility(View.VISIBLE);
            resendCodeLayout.setVisibility(View.VISIBLE);
        }
    }

    private void onResendClick(View v){
    }
}
