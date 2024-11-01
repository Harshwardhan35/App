package com.cscorner.leicestercollege;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    ImageButton button,button2,button3,button4,button5;
    private FusedLocationProviderClient fusedLocationProviderClient;
    DatabaseHandler databaseHandler;

    private  LocationSettingsRequest.Builder builder;
    String x="" , y="";
    private static final int REQUEST_LOCATION=1;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        button=findViewById(R.id.con_Image);
        button2=findViewById(R.id.noti_Image);
        button3=findViewById(R.id.sos_Image);
        button4=findViewById(R.id.alarm_Image);
        button5=findViewById(R.id.map_Image);


        databaseHandler=new DatabaseHandler(this);
        final MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.joker);
        locationManager=(LocationManager) getSystemService(LOCALE_SERVICE);
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            final AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Enable GPS").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            final AlertDialog alertDialog= builder.create();
            alertDialog.show();
        }
        else {
            Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location!=null){
                double lat=location.getLatitude();
                double lon=location.getLatitude();
                x=String.valueOf(lat);
                y=String.valueOf(lon);
            }
            else {
                Toast.makeText(this,"UNABLE TO FIND LOCATION",Toast.LENGTH_SHORT);
            }
        }
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
             Intent i=new Intent(getApplicationContext(),register.class);
             startActivity(i);
           }
       });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> thelist=new ArrayList<>();
                Cursor data = databaseHandler.getListContents();
                if(data.getCount()==0){
                    Toast.makeText(MainActivity2.this, "NO CONTENT TO SHOW", Toast.LENGTH_SHORT).show();
                }
                else {
                    while (data.moveToNext()){
                        thelist.add(data.getString(1));
                        Intent cal=new Intent(Intent.ACTION_CALL);
                        cal.setData(Uri.parse("tel:012345"));
                        if(ContextCompat.checkSelfPermission(getApplicationContext(),CALL_PHONE))==
                        PackageManager.PERMISSION_GRANTED);
        startActivity(cal);
                    }
                }else {
                    if(Build.VERSION.SDK_INT>=Build.VERSION.CODES.M){
                        requestPermissions(new String[{CALL_PHONE},1]);
                    }
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                Toast.makeText(MainActivity2.this, "PANIC SITUATION", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
         button4.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(MainActivity2.this,googlemaodemo.class);
                 startActivity(intent);
             }
         });
          button5.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {

              }
          });
    }
}