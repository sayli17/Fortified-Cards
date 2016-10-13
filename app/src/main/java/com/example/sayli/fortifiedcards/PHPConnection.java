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

public  class PHPConnection extends AsyncTask  {
    public String st_balance=null,sta_imei=null;
    @Override
    protected String[] doInBackground(Object[] params) {

        try {
            String scan=(String) params[0];
            String imei=(String) params[1];


            String link="http://aniketvpatil.com/index.php?scan="+scan+" & imei="+imei;
            String data= URLEncoder.encode("scan","UTF-8")+"="+URLEncoder.encode(scan,"UTF-8");
            data+= "&" + URLEncoder.encode("imei","UTF-8")+"="+URLEncoder.encode(imei,"UTF-8");

            URL url= new URL(link);
            URLConnection conn=url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr= new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb= new StringBuilder();
            StringBuilder sb1= new StringBuilder();

            String line;
            while((line = in.readLine())!= null)
            {
                sb.append(line);
                break;
            }
            st_balance=sb.toString();
            String line1;
            while((line1 = in.readLine())!= null)
            {
                sb1.append(line1);
                break;
            }
            sta_imei=sb1.toString();
            String []strings={st_balance,sta_imei};


            return strings;

        }
        catch (MalformedURLException e) {
            return new String[]{e.toString()};

        } catch (UnsupportedEncodingException e) {
            return new String[]{e.toString()};
        } catch (IOException e) {
            return new String[]{e.toString()};
        }
    }


    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

}

