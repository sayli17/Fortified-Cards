package com.example.sayli.fortifiedcards;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class VScan extends AppCompatActivity implements ZXingScannerView.ResultHandler{
    private ZXingScannerView mScanner;
    String scanString_vendor;
    private static String shopkeeper_card_no;

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
        scanString_vendor=getScan(rawResult);// Toast.makeText(this, "The scan result is stored in the variable scanresult  having value "+scanresult, Toast.LENGTH_SHORT ).show();
        shopkeeper_card_no=scanString_vendor;

        getShopkeeper_card_no();
        goToEA();

    }

    public static String getShopkeeper_card_no(){
        return shopkeeper_card_no;
    }

    public void goToEA(){
        startActivity(new Intent(this,EnterAmount.class));
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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cscan, menu);
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

