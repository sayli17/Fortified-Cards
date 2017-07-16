package com.example.sayli.fortifiedcards;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.google.zxing.Result;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.logging.Formatter;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class VScan extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static String shopkeeper_card_no;
    String scanString_vendor;
    private ZXingScannerView mScanner;
    //    ServerSocket serverSocket=null;
//    double dueAmount;

    // MainActivity var=new MainActivity();

    public static String getShopkeeper_card_no() {
        return shopkeeper_card_no;
    }

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
        scanString_vendor = getScan(rawResult);// Toast.makeText(this, "The scan result is stored in the variable scanresult  having value "+scanresult, Toast.LENGTH_SHORT ).show();
        try {
            shopkeeper_card_no = AESDecryption.decrypt(scanString_vendor);
            System.out.print(shopkeeper_card_no);
        } catch (Exception e) {
            e.printStackTrace();
        }
        getShopkeeper_card_no();
        goToEA();
    }

    public void goToEA() {
//        Thread socketThread = new Thread(new SocketThread());
//        socketThread.start();


        WaitingForAmount waitingForAmount = new WaitingForAmount(8080);
        waitingForAmount.execute();
    }

    public class WaitingForAmount extends AsyncTask<Void, Void, Void> {

        String vendorIP;
        int port;
        double dueAmount;
        String myIP;

        public String setVendorIP() {
            //getting ip address from shopkeeper card no.
            vendorIP = shopkeeper_card_no.substring(26, shopkeeper_card_no.length());
            return vendorIP;
        }

        WaitingForAmount(int port) {
            this.port = port;
            this.vendorIP = setVendorIP();
//            try {
//                Enumeration<NetworkInterface> enumNetworkInterfaces = NetworkInterface
//                        .getNetworkInterfaces();
//                while (enumNetworkInterfaces.hasMoreElements()) {
//                    NetworkInterface networkInterface = enumNetworkInterfaces
//                            .nextElement();
//                    Enumeration<InetAddress> enumInetAddress = networkInterface
//                            .getInetAddresses();
//                    while (enumInetAddress.hasMoreElements()) {
//                        InetAddress inetAddress = enumInetAddress.nextElement();
//
//                        if (inetAddress.isSiteLocalAddress()) {
//                            myIP = inetAddress.getHostAddress();
//                        }
//
//                    }
//
//                }
//
//
//            } catch (SocketException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                myIP = "Something Wrong! " + e.toString() + "\n";
//            }
//            System.out.println(myIP);
//            System.out.println(vendorIP);
        }


        @Override
        protected Void doInBackground(Void... voids) {
            Socket socket = null;
            DataInputStream dataInputStream = null;
            DataOutputStream dataOutputStream = null;
            try {
                socket = new Socket(vendorIP, port);
                while (dataInputStream == null) {
                    dataInputStream = new DataInputStream(socket.getInputStream());
                }
                dueAmount = Double.parseDouble(dataInputStream.readUTF());
                socket.close();
                Intent i = new Intent(VScan.this, ConfirmAmount.class);
                i.putExtra("Due Amount", dueAmount);
                startActivity(i);
                return null;

            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }


    }

    //    private class SocketThread extends Thread {
//        static final int sPORT = 8080;
//        @Override
//        public void run() {
//            Socket socket=null;
//            DataOutputStream dataOutputStream=null;
//            DataInputStream dataInputStream=null;
//            try{
//                serverSocket=new ServerSocket(sPORT);
//                VScan.this.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        ProgressDialog progressDialog=new ProgressDialog(VScan.this,ProgressDialog.STYLE_SPINNER);
//                    }
//                });
//                while (true){
//                    socket=serverSocket.accept();
//                    dataInputStream=new DataInputStream(socket.getInputStream());
//                    dataOutputStream=new DataOutputStream(socket.getOutputStream());
//                    dueAmount=Double.parseDouble(dataInputStream.readUTF());
//                }
//            } catch (Exception e){
//            }
//            super.run();
//        }
//    }
    private String getScan(Result scanRaw) {
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
        scanresult = scanRaw.getText().toString();
        return scanresult;

    }


}


