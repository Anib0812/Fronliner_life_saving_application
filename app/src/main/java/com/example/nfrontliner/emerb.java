package com.example.nfrontliner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationRequest;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class emerb extends AppCompatActivity {
    TextView fname1;
    TextView lname1;
    TextView age2;
    TextView bg2;
    TextView gen2;
    TextView email1;
    TextView ph2;
    TextView emph2;
    TextView la;
    TextView lo;
    TextView address;
    Button pb, loc,log;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    Geocoder geocoder;
    List<Address>adds ;
    FusedLocationProviderClient fusedLocationProviderClient;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_FNAME="name";
    private static final String KEY_LNAME="lname";
    private static final String KEY_AGE1="age1";
    private static final String KEY_BG1="bg1";
    private static final String KEY_GEN1="gen";
    private static final String KEY_EMAIL="email";
    private static final String KEY_PH1="ph1";
    private static final String KEY_EMPH1="emph1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_emerb);
        fname1 = findViewById(R.id.fn);
        lname1 = findViewById(R.id.ln);
        age2 = findViewById(R.id.age);
        bg2 = findViewById(R.id.bg);
        gen2 = findViewById(R.id.gen);
        email1 = findViewById(R.id.em);
        ph2 = findViewById(R.id.ph);
        emph2 = findViewById(R.id.emph);
        address=findViewById(R.id.add);
        la = findViewById(R.id.la);
        lo = findViewById(R.id.lo);
        loc = findViewById(R.id.up);
        log=findViewById(R.id.lg);
        pb=findViewById(R.id.hel);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(emerb.this);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_FNAME, null);
        String lname = sharedPreferences.getString(KEY_LNAME, null);
        String age1 = sharedPreferences.getString(KEY_AGE1, null);
        String bg1 = sharedPreferences.getString(KEY_BG1, null);
        String gen1 = sharedPreferences.getString(KEY_GEN1, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String ph1 = sharedPreferences.getString(KEY_PH1, null);
        String emph1 = sharedPreferences.getString(KEY_EMPH1, null);
        if (name != null || lname != null || age1 != null || bg1 != null || gen1 != null||email!=null||ph1!=null||emph1!=null) {
            fname1.setText(name);
            lname1.setText(lname);
            age2.setText( age1);
            bg2.setText( bg1);
            gen2.setText(gen1);
            email1.setText(email);
            ph2.setText(ph1);
            emph2.setText(emph1);
            log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.clear();
                    editor.commit();
                    finish();
                    Toast.makeText(emerb.this, "LOGOUT SUCCESS", Toast.LENGTH_SHORT).show();
                }

            });
            pb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ContextCompat.checkSelfPermission(emerb.this, Manifest.permission.SEND_SMS)
                            == PackageManager.PERMISSION_GRANTED) {
                        sendmsg();
                    } else {
                        ActivityCompat.requestPermissions(emerb.this, new String[]{Manifest.permission.SEND_SMS}, 100);
                    }
                    rootNode=FirebaseDatabase.getInstance();
                    reference=rootNode.getReference("users");
                    String Name=fname1.getText().toString();
                    String Age=age2.getText().toString();
                    String Bg=bg2.getText().toString();
                    String Email=email1.getText().toString();
                    String Ph=ph2.getText().toString();
                    String Latitude=la.getText().toString();
                    String Longitude=lo.getText().toString();
                    String Address=address.getText().toString();
                    String Em_num=emph2.getText().toString();
                    userhelper userhelper=new userhelper(Name,Age,Bg,Email,Ph,Latitude,Longitude,Address,Em_num);

                    reference.child(Ph).setValue(userhelper);


                }
            });
            loc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ActivityCompat.checkSelfPermission(emerb.this
                            , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(emerb.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        getCurrentLocation();
                    } else {
                        ActivityCompat.requestPermissions(emerb.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                    }


                }
            });


        }
    }

    private void sendmsg() {

        String Phone =emph2.getText().toString();
        String operator="7440528270";
        String message = "ITS AN EMERGENCY HELP";
        String pname=fname1.getText().toString();
        String sname=lname1.getText().toString();
        String pbg=bg2.getText().toString();
        String log=lo.getText().toString();
        String add1=address.getText().toString().trim();
        if (!Phone.isEmpty() && !message.isEmpty()) {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(Phone,null,message+":-  N:-"+pname+" "+sname+" BG:-"+pbg+" A:-"+add1,null,null);
            Toast.makeText(this, "Message sent", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Some problem", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendmsg();
        } else {
            Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();

        }


    }

    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();
                    if (location != null) {
                        la.setText(String.valueOf(location.getLatitude()));
                        lo.setText(String.valueOf(location.getLongitude()));
                        geocoder=new Geocoder(emerb.this, Locale.getDefault());
                        try {
                            adds=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        String address1 =adds.get(0).getAddressLine(0);
                        address.setText(address1);
                    }
                }
            });
        } else {
            com.google.android.gms.location.LocationRequest locationRequest = new com.google.android.gms.location.LocationRequest()
                    .setPriority(LocationRequest.QUALITY_HIGH_ACCURACY)
                    .setInterval(10000)
                    .setFastestInterval(1000)
                    .setNumUpdates(1);
            LocationCallback locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(@NonNull LocationResult locationResult) {
                    Location location1 = locationResult.getLastLocation();
                    la.setText(String.valueOf(location1.getLatitude()));
                    lo.setText(String.valueOf(location1.getLongitude()));



                }
            };
        }
    }
}