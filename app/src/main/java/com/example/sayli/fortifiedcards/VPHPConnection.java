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

public class VPHPConnection extends AsyncTask {
    public String st;
    @Override
    protected String doInBackground(Object[] params) {

        try {
            String scan=(String) params[0];



            String link="http://aniketvpatil.com/index1.php?scan="+scan;
            String data= URLEncoder.encode("scan", "UTF-8")+"="+URLEncoder.encode(scan,"UTF-8");

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

