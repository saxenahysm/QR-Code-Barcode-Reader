package com.shyam.barcodereader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    Button btnScanBarcode;
    TextView messageText, messageFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageText = findViewById(R.id.textContent);
        messageFormat = findViewById(R.id.textFormat);
        btnScanBarcode = findViewById(R.id.btnScanBarcode);
        btnScanBarcode.setOnClickListener(v -> {
            /*IntentIntegrator intentIntegrator = new IntentIntegrator(this);
            intentIntegrator.setPrompt("Scan a QR-Code");
            intentIntegrator.setOrientationLocked(true);
            intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            intentIntegrator.initiateScan();*/
//            startActivity(new Intent(MainActivity.this,ScanBArcodeActivity.class));
            startActivity(new Intent(MainActivity.this,SquareScannerActivity.class));
        });
    }

    String TAG = "test";
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                // if the intentResult is not null we'll set the content and format of scan message
                Log.e(TAG, "onActivityResult: " + intentResult.getContents().length());
                Log.e(TAG, "onActivityResult: "+intentResult.getContents().substring(31,43));
                Log.e(TAG, "onActivityResult: "+intentResult.getContents());
                messageText.setText(intentResult.getContents());
                messageFormat.setText(intentResult.getContents().substring(31,43));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}