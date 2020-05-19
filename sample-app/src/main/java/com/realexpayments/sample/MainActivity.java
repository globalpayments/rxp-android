package com.realexpayments.sample;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.realexpayments.hpp.HPPError;
import com.realexpayments.hpp.HPPManager;
import com.realexpayments.hpp.HPPManagerListener;

import java.util.HashMap;

/* Use these test urls:
 * https://rxp-hpp-ios.azurewebsites.net/hppRequestProducer.php
 * https://rxp-hpp-ios.azurewebsites.net/hppResponseConsumer-object.php
 * https://rxp-hpp-ios.azurewebsites.net/hppResponseConsumer-string.php
 * */

public class MainActivity extends AppCompatActivity implements HPPManagerListener {
    static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HPPManager hppManager = new HPPManager();

        hppManager.setHppRequestProducerURL("https://rxp-hpp-ios.azurewebsites.net/hppRequestProducer.php");
        hppManager.setHppURL("https://pay.sandbox.realexpayments.com/pay");
        hppManager.setHppResponseConsumerURL("https://rxp-hpp-ios.azurewebsites.net/hppResponseConsumer-string.php");

        HashMap<String, String> additionalHeaders = new HashMap<>();
        additionalHeaders.put("headerName1", "headerValue1");
        additionalHeaders.put("headerName2", "headerValue2");
        additionalHeaders.put("headerName3", "headerValue3");
//        hppManager.setAdditionalHeaders(additionalHeaders);

        Fragment hppManagerFragment = hppManager.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, hppManagerFragment)
                .commit();
    }

    @Override
    public void hppManagerCompletedWithResult(Object result) {
        String paymentResult = "hppManagerCompletedWithResult: " + result.toString();
        showResult(paymentResult);
    }

    @Override
    public void hppManagerFailedWithError(HPPError error) {
        String paymentResult = "hppManagerFailedWithError: " + error.getMessage() + " at url: " + error.getUrl();
        showResult(paymentResult);
    }

    @Override
    public void hppManagerCancelled() {
        String paymentResult = "hppManagerCancelled";
        showResult(paymentResult);
    }

    private void showResult(String paymentResult) {
        Log.d(TAG, paymentResult);
        new AlertDialog.Builder(this)
                .setTitle("Payment result")
                .setMessage(paymentResult)
                .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                })
                .show();
    }
}
