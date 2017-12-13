package com.meteorsoftech.dailyattendance.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codetroopers.betterpickers.calendardatepicker.CalendarDatePickerDialogFragment;
import com.codetroopers.betterpickers.calendardatepicker.MonthAdapter;
import com.meteorsoftech.dailyattendance.Adapter.RequestHandler;
import com.meteorsoftech.dailyattendance.R;
import com.meteorsoftech.dailyattendance.Validation.Validations;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Delete on 7/14/2017.
 */

public class Fragment_Registration extends android.support.v4.app.Fragment implements View.OnClickListener,Spinner.OnItemSelectedListener{
    EditText edt_empname,edt_emapaddres,edt_empaddress1,edt_empemail,
            edt_empmobile1,edt_empmobile2,edt_empqualification,edt_empactivity,edt_emppassword;
    Spinner spinner_bloodgroup,spinner_rank,spinner_mrn,spinner_gender;
    CheckBox checkBox_terms;
    Button button_register,button_reset;
    TextView tv_selectdate;
    ImageView imageView;
    ArrayAdapter<CharSequence> bloodgrp_Adapter,rankAdapter,mrnAdapter,genderAdapter;
    private String date = "2017/02/04";//yyyy/mm/dd
    String Name,Address,Address1,Email,MobileNo1,MobileNo2,Qualification,Activity,Date1,BloodGroup,Rank,MRN,CheckedTerms,SelectGender,SelectPassword;

    String url="https://ghawadediilip.000webhostapp.com/Services/rgistration.php";

    public static final String UPLOAD_URL = "http://ghawadediilip.000webhostapp.com/Services/uploadimage.php";
    public static final String UPLOAD_KEY = "image";
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    private Uri filePath;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.employeeregistrationfragment,container,false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //EditText
        edt_empname = (EditText)view.findViewById(R.id.edt_empName);
        edt_emapaddres = (EditText)view.findViewById(R.id.edt_empAdd);
        edt_empaddress1 = (EditText)view.findViewById(R.id.edt_empAdd1);
        edt_empemail = (EditText)view.findViewById(R.id.edt_empEmail);
        edt_empmobile1 = (EditText)view.findViewById(R.id.edt_emp_MobileNo1);
        edt_empmobile2 = (EditText)view.findViewById(R.id.edt_emp_MobileNo2);
        edt_empqualification = (EditText)view.findViewById(R.id.edt_empQualification);
        edt_empactivity = (EditText)view.findViewById(R.id.edt_empActivity);
        edt_emppassword = (EditText)view.findViewById(R.id.edt_emp_password);


        //Checkbox...
        checkBox_terms = (CheckBox)view.findViewById(R.id.check_terms);
        checkBox_terms.setOnClickListener(this);

        //ImageView profile_pic
        imageView = (ImageView)view.findViewById(R.id.profile_pic);
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(R.drawable.emp)
                .showImageOnFail(R.drawable.emp)
                .showImageOnLoading(R.drawable.emp).build();

        ImageLoader imageLoader = ImageLoader.getInstance();
       // imageLoader.displayImage();


        //Button
        button_register = (Button)view.findViewById(R.id.btn_register);
        button_reset = (Button)view.findViewById(R.id.btn_reset);

       SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        tv_selectdate = (TextView)view.findViewById(R.id.tv_joindate);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,0);
        tv_selectdate.setText(simpleDateFormat.format(calendar.getTime()));
        tv_selectdate.setOnClickListener(this);

        //bloodgrp_Adapter = new ArrayAdapter<CharSequence>(getActivity(),R.layout.spinner_bloodgrpselection_item,R.id.select_bloodGgroup);
        bloodgrp_Adapter = (ArrayAdapter.createFromResource(getActivity(),R.array.select_BloodGroup,R.layout.spinner_selection_item));
        //bloodgrp_Adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_bloodgroup = (Spinner)view.findViewById(R.id.spinner_bloodgrp);
        spinner_bloodgroup.setAdapter(bloodgrp_Adapter);
        spinner_bloodgroup.setOnItemSelectedListener(this);
        //spinner Gender
        genderAdapter = (ArrayAdapter.createFromResource(getActivity(),R.array.select_Gender,R.layout.spinner_selection_item));
        spinner_gender = (Spinner)view.findViewById(R.id.spinner_gender);
        spinner_gender.setAdapter(genderAdapter);
        //spinner rank
        rankAdapter = (ArrayAdapter.createFromResource(getActivity(),R.array.select_Rank,R.layout.spinner_selection_item));
        spinner_rank = (Spinner)view.findViewById(R.id.spinner_rank);
        spinner_rank.setAdapter(rankAdapter);

        //spinner mrn
        mrnAdapter = (ArrayAdapter.createFromResource(getActivity(),R.array.select_Mrn,R.layout.spinner_selection_item));
        spinner_mrn = (Spinner)view.findViewById(R.id.spinner_mrn);
        spinner_mrn.setAdapter(mrnAdapter);
                //Validation
        registerView();
            //Button onClick
        button_reset.setOnClickListener(this);
        button_register.setOnClickListener(this);
        imageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_joindate:
                selectDate();

                break;
            case R.id.btn_reset:
                resetEditText();
                break;
            case R.id.btn_register:
                if (checkValidation())
                {

                    GetDataFromEditText();
                    sendToServer(Name,Email,Address,Address1,MobileNo1,MobileNo2,Qualification,Activity,Date1,BloodGroup,Rank,MRN,CheckedTerms,SelectGender,SelectPassword);
                    //uploadImage();
                    //resetEditText();
                }
                else {
                    Toast.makeText(getActivity(),"While Error in submitting form",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.profile_pic:
                showFileChooser();
                break;
        }

    }
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String>{

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getContext(), "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();

                data.put(UPLOAD_KEY, uploadImage);

                String result = null;
                try {
                    result = rh.senPostRequest(UPLOAD_URL,data);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // String result = rh.sendPostRequest(UPLOAD_URL,data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void selectDate()
    {
        Calendar calendar = Calendar.getInstance();
        CalendarDatePickerDialogFragment cdp = new CalendarDatePickerDialogFragment();

        Log.d("test","date"+calendar.getTime());
        MonthAdapter.CalendarDay calendarDay = new MonthAdapter.CalendarDay();
        calendarDay.setDay(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.MONTH,0);
        cdp.setPreselectedDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        cdp.show(getActivity().getSupportFragmentManager(),"Material Calender Example");

        cdp.setOnDateSetListener(new CalendarDatePickerDialogFragment.OnDateSetListener() {
            @Override
            public void onDateSet(CalendarDatePickerDialogFragment dialog, int year, int monthOfYear, int dayOfMonth) {

                tv_selectdate.setText(year+"/"+(monthOfYear+1)+"/"+dayOfMonth);

            }
        });
    }


    public   void resetEditText()
    {
        edt_empname.setText(null);
        edt_emapaddres.setText(null);
        edt_empaddress1.setText(null);
        edt_empactivity.setText(null);
        edt_empqualification.setText(null);
        edt_empmobile2.setText(null);
        edt_empmobile1.setText(null);
        edt_empemail.setText(null);
        edt_emppassword.setText(null);


    }
    public void registerView()
    {
        edt_empname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                Validations.hasText(edt_empname);
            }
        });
        edt_empemail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                   Validations.isEmailAddress(edt_empemail,true);
            }
        });
        edt_empmobile1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                        Validations.isMobileNo(edt_empmobile1,true);
            }
        });
        /*edt_empmobile2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                Validations.isMobileNo(edt_empmobile2,true);
            }
        });*/
        edt_empqualification.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                Validations.hasText(edt_empqualification);
            }
        });
        /*edt_empactivity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                Validations.hasText(edt_empactivity);
            }
        });*/
        /*edt_empaddress1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                    Validations.hasText(edt_empaddress1);
            }
        });*/

        edt_emapaddres.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                Validations.hasText(edt_emapaddres);
            }
        });
    }

    public boolean checkValidation()
    {
        boolean ret=true;
        if (!Validations.hasText(edt_empname))ret=true;
        if (!Validations.hasText(edt_emapaddres))ret=true;
        if (!Validations.hasText(edt_empqualification))ret=true;
        if (!Validations.isMobileNo(edt_empmobile1,true))ret=false;
        if (!Validations.isEmailAddress(edt_empemail,true))ret=false;
        return ret;

    }
     public void GetDataFromEditText()
     {
         Name = edt_empname.getText().toString();
         Address = edt_empaddress1.getText().toString();
         Address1 = edt_emapaddres.getText().toString();
         Email = edt_empemail.getText().toString();
         Qualification = edt_empqualification.getText().toString();
         MobileNo1 = edt_empmobile1.getText().toString();
         MobileNo2 = edt_empmobile2.getText().toString();
         Activity = edt_empactivity.getText().toString();
         Date1=tv_selectdate.getText().toString();
         BloodGroup = spinner_bloodgroup.getSelectedItem().toString();
         Rank = spinner_rank.getSelectedItem().toString();
         MRN = spinner_mrn.getSelectedItem().toString();
         CheckedTerms = checkBox_terms.getText().toString();
         SelectGender = spinner_gender.getSelectedItem().toString();
         SelectPassword = edt_emppassword.getText().toString();


     }

     public void sendToServer(final String name,final String email,final String address,final String address1,
                              final String mob1,final String mob2,final String qualification,
                              final String activity, final String joindate,final String bloodgroup,
                              final String rank,final String marn,final String term,final String gender,final String password)
     {
         class  sendData extends AsyncTask<String, Void, String>
         {

             @Override
             protected String doInBackground(String... params) {
                 String QuickName = name;
                 String QuickAddress = address;
                 String QuickAddress1 = address1;
                 String QuickEmail = email;
                 String QuickMobileno1 = mob1;
                 String QuickMobileno2 = mob2;
                 String QuickQualificationn = qualification;
                 String QuickActivity = activity;
                 String QuickDate = joindate;
                 String QuickBloodGrp= bloodgroup;
                 String QuickRank = rank;
                 String QuickMRN = marn;
                 String QuickTerm = term;
                 String QuickGenderr = gender;
                 String QuickPassword= password;

                 List <NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                 nameValuePairs.add(new BasicNameValuePair("name", QuickName));
                 nameValuePairs.add(new BasicNameValuePair("address",QuickAddress));
                 nameValuePairs.add(new BasicNameValuePair("address1",QuickAddress1));
                 nameValuePairs.add(new BasicNameValuePair("email",QuickEmail));
                 nameValuePairs.add(new BasicNameValuePair("mob1",QuickMobileno1));
                 nameValuePairs.add(new BasicNameValuePair("mob2",QuickMobileno2));
                 nameValuePairs.add(new BasicNameValuePair("qualification",QuickQualificationn));
                 nameValuePairs.add(new BasicNameValuePair("activity",QuickActivity));
                 nameValuePairs.add(new BasicNameValuePair("bloodgroup",QuickBloodGrp));
                 nameValuePairs.add(new BasicNameValuePair("term",QuickTerm));

                 nameValuePairs.add(new BasicNameValuePair("rank",rank));
                 nameValuePairs.add(new BasicNameValuePair("marn",marn));
                 nameValuePairs.add(new BasicNameValuePair("gender",gender));
                 //nameValuePairs.add(new BasicNameValuePair("password",QuickPassword));
                 nameValuePairs.add(new BasicNameValuePair("password",QuickPassword));


                 nameValuePairs.add(new BasicNameValuePair("joindate",QuickDate));


                 try {
                     HttpClient httpClient = new DefaultHttpClient();
                     HttpPost httpPost = new HttpPost(url);

                     httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                     HttpResponse response = httpClient.execute(httpPost);
                     HttpEntity entity = response.getEntity();
                 } catch (UnsupportedEncodingException e) {
                     e.printStackTrace();
                 } catch (ClientProtocolException e) {
                     e.printStackTrace();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }


                 return "Data Submited Successfully";

             }

             @Override
             protected void onPostExecute(String s) {
                 super.onPostExecute(s);
                 //check if user is already register or not...
                /* JSONObject jsonObject = new JSONObject();

                 try {
                    String errmsg = jsonObject.getString(KEY_ERROR);

                     if (Integer.parseInt(errmsg)==0)
                     {
                         Toast.makeText(getContext(),"Already Registered",Toast.LENGTH_LONG).show();
                     }
                     else {
                         Toast.makeText(getActivity(), "Data Submited Successfully", Toast.LENGTH_LONG).show();
                     }
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
*/
                 Toast.makeText(getActivity(), "Data Submited Successfully", Toast.LENGTH_LONG).show();

             }
         }
         sendData sendData = new sendData();
         sendData.execute(name,email,address,address1,mob1,mob1,qualification,
                 activity,joindate,bloodgroup,rank,marn,term,gender,password);

     }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {}

}
