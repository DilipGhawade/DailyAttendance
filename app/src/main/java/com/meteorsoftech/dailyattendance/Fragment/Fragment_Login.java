package com.meteorsoftech.dailyattendance.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.meteorsoftech.dailyattendance.ActivityConnectionFailed;
import com.meteorsoftech.dailyattendance.R;
import com.meteorsoftech.dailyattendance.utils.Util;

/**
 * Created by Delete on 7/14/2017.
 */

public class Fragment_Login extends android.support.v4.app.Fragment implements View.OnClickListener {
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;

    private EditText etEmail;
    private EditText etPassword;
    Button buttonlogin,buttoncancel;
    TextView tv_register;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login,container,false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
                etEmail = (EditText)view.findViewById(R.id.edt_userid);
                etPassword = (EditText)view.findViewById(R.id.edt_password);
                tv_register = (TextView)view.findViewById(R.id.tv_new_user_register);


                buttonlogin = (Button)view.findViewById(R.id.btnLogin);
                buttoncancel = (Button)view.findViewById(R.id.btnCancel);
                buttonlogin.setOnClickListener(this);
                buttoncancel.setOnClickListener(this);
                tv_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnLogin:
                if(isValid())
            {
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                loginApp();
            }
                break;
            case R.id.btnCancel:
                getActivity().finish();
                break;
            case R.id.tv_new_user_register:
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.Main_Container,new Fragment_Registration() );
                ft.addToBackStack(null);
                ft.commit();
                break;

        }

    }

    public void loginApp()
    {
        if (etEmail.getText().toString().equals("admin")&& etPassword.getText().toString().equals("1234"))
        {
            if (Util.isConnectedToInternet(getActivity()))
            {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.Main_Container,new Fragment_Home());
               // ft.addToBackStack(null);
                ft.commit();


            }
            else
            {
                Intent i = new Intent(getActivity(), ActivityConnectionFailed.class);
                startActivity(i);
                getActivity().finish();

            }
        }
        else {
            Toast.makeText(getActivity(),"Invalid Username and Password ",Toast.LENGTH_LONG).show();
        }
    }

    public boolean isValid() {
        if (TextUtils.isEmpty(etEmail.getText().toString()))
        {
            etEmail.setError("Enter UserName");
            return false;
        }
        else if (TextUtils.isEmpty(etPassword.getText().toString()))
        {
            etPassword.setError("Enter password");
            return false;
        }
        return true;
    }
             /*public void checkLogin(View arg0)
    {
        final String email = etEmail.getText().toString();
        final String password = etPassword.getText().toString();
        new AsyncLogin().execute(email,password);
    }

    private class AsyncLogin extends AsyncTask<String,String,String>
    {
        ProgressDialog pdLoading = new ProgressDialog(getContext());
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                url = new URL("https://ghawadediilip.000webhostapp.com/Services/login.inc.php");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                conn = (HttpURLConnection)url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("emp_email",params[0])
                         .appendQueryParameter("password",params[1]);

                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();

            } catch (IOException e) {
                e.printStackTrace();
                return "exception";
            }
            try {
                int response_code = conn.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK)
                {
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null){
                        result.append(line);
                    }
                    return (result.toString());
                }
                else
                {
                    return "unsucessfully";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                conn.disconnect();
            }
            return "SuccessFully Login";
        }

        @Override
        protected void onPostExecute(String result) {
            pdLoading.dismiss();
            if (result.equalsIgnoreCase("true"))
            {
                //when login successful then call homelogin
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                //Fragment_Home fh = new Fragment_Home();
                ft.replace(R.id.Main_Container,new Fragment_Home());
               // ft.addToBackStack(null);
                ft.commit();
            }
            else if (result.equalsIgnoreCase("false"))
            {
                Toast.makeText(getContext(),"Invalid email and password",Toast.LENGTH_LONG).show();
            }
            else if (result.equalsIgnoreCase("exception")||result.equalsIgnoreCase("unsuccessful"))
            {
                Toast.makeText(getContext(),"OOPs! Something went wrong. Connection Problem.",Toast.LENGTH_LONG).show();

            }

        }
    }*/

}



