package com.example.t4_final;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.t4_final.Moduls.Libro;
import com.example.t4_final.Services.RetrofitService;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistrarActivity extends AppCompatActivity {

    static final int REQUEST_PICK_FROM_GALLERY = 2;
    public String fileBase64;

    EditText txtTitulo, txtResumen, txtAutor, txtFecha, txtTienda1, txtTienda2, txtTienda3;
    ImageView imgLibro;
    Button btnGalery, btnGuardar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE}, 1001);
        }

        txtTitulo = findViewById(R.id.txtTitulo);
        txtResumen = findViewById(R.id.txtResumen);
        txtAutor = findViewById(R.id.txtAutor);
        txtFecha = findViewById(R.id.txtFecha);
        txtTienda1 = findViewById(R.id.txtTienda1);
        txtTienda2 = findViewById(R.id.txtTienda2);
        txtTienda3 = findViewById(R.id.txtTienda3);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService service = retrofit.create(RetrofitService.class);

        btnGalery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryAddPic();
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Libro libro = new Libro();
                libro.setTitulo(txtTitulo.getText().toString());
                libro.setResumen(txtResumen.getText().toString());
                libro.setAutor(txtAutor.getText().toString());
                libro.setFecha(txtFecha.getText().toString());
                libro.setTienda1(txtTienda1.getText().toString());
                libro.setTienda2(txtTienda2.getText().toString());
                libro.setTienda3(txtTienda3.getText().toString());

                Call<Libro> call = service.registrar(libro);

                call.enqueue(new Callback<Libro>() {
                    @Override
                    public void onResponse(Call<Libro> call, Response<Libro> response) {
                        Intent intent = new Intent(RegistrarActivity.this, Libro.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Libro> call, Throwable t) {

                    }
                });
            }
        });
    }

    private void galleryAddPic(){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,REQUEST_PICK_FROM_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        imgLibro.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        fileBase64 = gtFile(picturePath);

    }
    public static String gtFile(String filePath){
        Bitmap bmp = null;
        ByteArrayOutputStream bos = null;
        byte[] bt = null;
        String encodeString = null;
        try{
            bmp = BitmapFactory.decodeFile(filePath);
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG,100, bos);
            bt = bos.toByteArray();
            encodeString = Base64.encodeToString(bt, Base64.NO_WRAP);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return encodeString;
    }
}