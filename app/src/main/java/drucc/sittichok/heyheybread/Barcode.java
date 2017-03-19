package drucc.sittichok.heyheybread;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Random;

public class Barcode extends AppCompatActivity {
    private ImageView imageView;
    private String idString, idBarcode,idOrder,Date,Name,Surname,SumTotal;
    private TextView Text,NumberOrder,Dateorder,NameOrder,TotalOrder;
    private int VatTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcode);

        getDataOrder();

        showview();

        setBarcode(idBarcode);

        settext();

    }

    private void settext() {
        Text.setText(idBarcode);
        NumberOrder.setText("เลขที่สั่งซื้อ : " + idOrder);
        Dateorder.setText("วันที่สั่งซื้อ : " + Date);
        NameOrder.setText("ผู้สั่งซื้อ : " + Name + " " + Surname);
        VatTotal = Integer.parseInt(SumTotal);
        int Valint = (VatTotal * 7) / 100;
        int SumVate = Valint + VatTotal;
        String totalVat = Integer.toString(SumVate);
        TotalOrder.setText("ยอดสั่งซื้อทั้งหมด : " + totalVat + " " + "บาท");
    }   // settext

    private void showview() {
        imageView = (ImageView) findViewById(R.id.imageView11);
        Text = (TextView) findViewById(R.id.textView60);
        NumberOrder = (TextView) findViewById(R.id.textView66);
        Dateorder = (TextView) findViewById(R.id.textView67);
        NameOrder = (TextView) findViewById(R.id.textView68);
        TotalOrder = (TextView) findViewById(R.id.textView72);
    }   // showview

    private void getDataOrder() {
        idString = getIntent().getStringExtra("ID");
        idBarcode = getIntent().getStringExtra("IDbarcode");
        idOrder = getIntent().getStringExtra("idOrder");
        Date = getIntent().getStringExtra("dateorder");
        Name = getIntent().getStringExtra("name");
        Surname = getIntent().getStringExtra("surname");
        SumTotal = getIntent().getStringExtra("total");
    }   // getDataOrder

    public void onBackPressed() {
        Intent objIntent = new Intent(Barcode.this, HubActivity.class);
        // ทำเสร็จแล้ว ให้ กลับไปหน้า HubActivity.class
        objIntent.putExtra("ID", idString); //แล้วส่งค่า ID คืนไปที่หน้า HubActivity.class ด้วย
        startActivity(objIntent);
        finish();
    }
    private void setBarcode(String strIdBarcode) {

        try {

            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            BitMatrix bitMatrix = multiFormatWriter.encode(strIdBarcode,BarcodeFormat.CODE_128,400,150);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }   // setBarcode

    public void finishBarcode(View v) {

        Intent objIntent = new Intent(Barcode.this, HubActivity.class);
        objIntent.putExtra("ID", idString);
        startActivity(objIntent);
        finish();
    }
}
