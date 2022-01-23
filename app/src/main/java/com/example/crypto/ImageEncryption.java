package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.crypto.Utils.MyEncrypter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

public class ImageEncryption extends AppCompatActivity {

    private static final String FILE_NAME_ENC = "Cummins_enc";
    private static final String FILE_NAME_DEC= "Cummins_dec.png";
    Button btn_enc,btn_dec;
    ImageView imageView;

    File myDir;

    String my_key="ltVkg0knCiDc9K80";//16 char=128 bit
    String my_spec_key="BentHldIPoOEawVa";//toc
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_encryption);

        btn_dec=(Button)findViewById(R.id.btn_decrypt);
        btn_enc=(Button)findViewById(R.id.btn_encrypt);
        imageView=(ImageView)findViewById(R.id.imageView);

        //Init path

        myDir = new File(Environment.getExternalStorageDirectory().toString()+"/Download");

        Dexter.withActivity(this)
                .withPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                })
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        btn_dec.setEnabled(true);
                        btn_enc.setEnabled(true);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        Toast.makeText(ImageEncryption.this, "You must enable permission", Toast.LENGTH_SHORT).show();
                    }
                }).check();

        btn_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outputFileDec = new File(myDir,FILE_NAME_DEC);
                File encFile = new File(myDir, FILE_NAME_ENC);
                try {
                    MyEncrypter.decryptToFile(my_key,my_spec_key,new FileInputStream(encFile),
                            new FileOutputStream(outputFileDec));

                    imageView.setImageURI(Uri.fromFile(outputFileDec));

                    //Delete file
                    //outputFileDec.delete();

                    Toast.makeText(ImageEncryption.this, "Decrypt", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_enc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Convert drawable to bitmap
                Drawable drawable= ContextCompat.getDrawable(ImageEncryption.this,R.drawable.solitude);
                BitmapDrawable bitmapDrawable=(BitmapDrawable)drawable;
                Bitmap bitmap=bitmapDrawable.getBitmap();
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
                InputStream is= new ByteArrayInputStream(stream.toByteArray());

                //Create file
                File outputFileEnc =new File(myDir,FILE_NAME_ENC);
                try {
                    MyEncrypter.encryptToFile(my_key,my_spec_key,is,new FileOutputStream(outputFileEnc));

                    Toast.makeText(ImageEncryption.this, "Encrypted", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                }
            }
        });


    }
}
