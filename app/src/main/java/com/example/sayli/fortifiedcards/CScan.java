package com.example.sayli.fortifiedcards;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.concurrent.ExecutionException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class CScan extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScanner;
    public String scanString;
    private static String[]check_imei_static;
    private static String customer_card_no;
    TelephonyManager telephonyManager;


    // MainActivity var=new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cscan);
        mScanner = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScanner);
        mScanner.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScanner.startCamera();// Start camera

    }

    @Override
    public void onPause() {
        super.onPause();
        mScanner.stopCamera();   // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        scanString=getScan(rawResult);// Toast.makeText(this, "The scan result is stored in the variable scanresult  having value "+scanresult, Toast.LENGTH_SHORT ).show();


        try {
            customer_card_no=AESDecryption.decrypt(scanString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print(customer_card_no);

        String imei=getCellId();
        //Toast.makeText(this, "IMEI No. is " + imei, Toast.LENGTH_LONG).show();
        String[]array_user=getValues(customer_card_no);
        //Toast.makeText(this,array_user[0]+""+array_user[1],Toast.LENGTH_LONG).show();
        Boolean proceed= check(array_user, imei);

        if (proceed)
        {
            startActivity(new Intent(this,VScan.class));
        }
        else{
            Toast.makeText(this, "Unauthorized User", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(this,php_imei,Toast.LENGTH_LONG).show();

        //  Toast.makeText(this,var.authorized,Toast.LENGTH_LONG).show();

    }
    private String getScan(Result scanRaw){
        String scanresult;
        Log.e("handler", scanRaw.getText()); // Prints scan results
        Log.e("handler", scanRaw.getBarcodeFormat().toString()); // Prints the scan format (qrcode)
        // show the scanner result into dialog box.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(scanRaw.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();
        mScanner.resumeCameraPreview(this);
        scanresult=scanRaw.getText().toString();
        return scanresult;

    }

    private boolean check(String []check, String d_id_real) {
        Boolean f=Boolean.FALSE;
        if (d_id_real.equalsIgnoreCase(check[0])) {
            f=Boolean.TRUE;
            return f;
        } else {

            return f;
        }
    }

    public String getCellId(){
        telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String cell_id=telephonyManager.getDeviceId();
        return cell_id;
    }

    private String[] getPHPConnection(String scan, String device_id) {

        String []response = {"No values found!"};
        try {
            response = (String[]) new PHPConnection().execute(scan, device_id).get();
            System.out.print(response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return response;

    }
    public  String[] getValues(String t){
        String[] check_imei=  getPHPConnection(t,getCellId());
        check_imei_static=check_imei;
        check_imei_statically();
        return check_imei;
    }
    public static String[] check_imei_statically()
    {
        return check_imei_static;
    }
    public static String getCardNo()
    {
        return customer_card_no;
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu items) {
        // Inflate the items; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.items.menu_cscan, items);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/


}




