package com.example.sayli.fortifiedcards;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Aniiket on 9/5/2016.
 */
public class APHPConnection extends AsyncTask {
    public String st;
    @Override
    protected String doInBackground(Object[] params) {

        try {
            String c_card=(String) params[0];
            String v_card=(String) params[1];
            String new_customer_balance=(String)params[2];
            String new_vendor_balance=(String)params[3];
            String link="http://aniketvpatil.com/index2.php?c_card="+c_card+"&v_card="+v_card+"& new_customer_balance="+new_customer_balance+"& new_vendor_balance="+new_vendor_balance;
            String data= URLEncoder.encode("c_card", "UTF-8")+"="+URLEncoder.encode(c_card,"UTF-8");
            data+= "&" + URLEncoder.encode("v_card","UTF-8")+"="+URLEncoder.encode(v_card,"UTF-8");
            data+= "&" + URLEncoder.encode("new_customer_balance","UTF-8")+"="+URLEncoder.encode(new_customer_balance,"UTF-8");
            data+= "&" + URLEncoder.encode("new_vendor_balance","UTF-8")+"="+URLEncoder.encode(new_vendor_balance,"UTF-8");

            URL url= new URL(link);
            URLConnection conn=url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb= new StringBuilder();

            String line;
            while((line = in.readLine()) != null)
            {
                sb.append(line);

                break;
            }
            st=sb.toString();
            return st;

        }
        catch (MalformedURLException e) {
            return e.toString();

        } catch (UnsupportedEncodingException e) {
            return e.toString();
        } catch (IOException e) {
            return e.toString();
        }
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }
}