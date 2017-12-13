package com.meteorsoftech.dailyattendance.Validation;

import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by Delete on 7/17/2017.
 */

public class Validations {

    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
    public static final String MOBILENO_REGEX= "^[0-9]{10}$";

    //Error msg
    public static final String REQUIRED_MSG="required";
    public static final String EMAIL_MSG="invalid email";
    public static final String MOBILENO_MSG="enter 10 digit only";

    public static boolean isEmailAddress(EditText editText,boolean required)
    {
        return isValid(editText,EMAIL_REGEX,EMAIL_MSG,required);
    }

    public static boolean isMobileNo(EditText editText,boolean required)
    {
        return isValid(editText,MOBILENO_REGEX,MOBILENO_MSG,required);
    }

    public static boolean isValid(EditText editText,String regex,String ermsg,boolean required)
    {
        String text = editText.getText().toString().trim();
        editText.setError(null);
        if (required && !hasText(editText))
            return false;
        if (required && !Pattern.matches(regex,text))
        {
            editText.setError(ermsg);
            return false;

        };
        return true;
    }
    public static boolean  hasText(EditText editText)
    {
        String text = editText.getText().toString().trim();
        editText.setError(null);
        if (text.length()==0)
        {
            editText.setError(REQUIRED_MSG);
            return false;
        }
        return true;
    }
}
