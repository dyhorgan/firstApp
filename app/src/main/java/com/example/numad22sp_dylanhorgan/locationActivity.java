package com.example.numad22sp_dylanhorgan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.LocationManager;

import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

public class locationActivity extends AppCompatActivity {
    private LocationRequest locationRequest;


  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if(requestCode == 1){
      if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
        Button locationButton = findViewById(R.id.getLocationButton);
        locationButton.setText("Touch to Get Location");
      }else{
        Toast.makeText(this, "Permission Not Granted", Toast.LENGTH_SHORT).show();
      }
    }

  }

  private boolean isGPSAvailable(){
    LocationManager locManager = null;
    boolean available = false;
    if(locManager == null){
      locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }
    available = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    return available;
  }

  private void activateGPS(){


    LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
      .addLocationRequest(locationRequest);
    builder.setAlwaysShow(true);

    Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
      .checkLocationSettings(builder.build());

    result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
      @Override
      public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

        try {
          LocationSettingsResponse response = task.getResult(ApiException.class);
          Toast.makeText(locationActivity.this, "GPS is already turned on", Toast.LENGTH_SHORT).show();

        } catch (ApiException e) {

          switch (e.getStatusCode()) {
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

              try {
                ResolvableApiException resolvableApiException = (ResolvableApiException)e;
                resolvableApiException.startResolutionForResult(locationActivity.this, 2);
              } catch (IntentSender.SendIntentException ex) {
                ex.printStackTrace();
              }
              break;

            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
              //Device does not have location
              break;
          }
        }
      }
    });
  }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

      locationRequest = LocationRequest.create();
      locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
      locationRequest.setInterval(5000);
      locationRequest.setFastestInterval(2000);

        Button getLocation = findViewById(R.id.getLocationButton);


        View locationLayout = findViewById(R.id.locationLayout);


        getLocation.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                  //Snackbar snack = Snackbar.make(locationLayout, "Clicked", Snackbar.LENGTH_LONG);
                  //snack.show();

                  getLocation.setText("Touch to Get Location");
                  if(isGPSAvailable()){

                    System.out.println("FIRING GPS AVAILABLE");
                    LocationServices.getFusedLocationProviderClient(locationActivity.this).requestLocationUpdates(locationRequest, new LocationCallback() {

                        @Override
                        public void onLocationResult(@NonNull LocationResult locationResult) {
                          super.onLocationResult(locationResult);

                          Log.i(null, "CALLBACK FIRING");

                          LocationServices.getFusedLocationProviderClient(locationActivity.this).removeLocationUpdates(this);

                          if(locationResult != null && locationResult.getLocations().size() > 0){
                            int index = locationResult.getLocations().size() - 1;
                            double latitude = locationResult.getLocations().get(index).getLatitude();
                            double longitude = locationResult.getLocations().get(index).getLongitude();


                            TextView latText = findViewById(R.id.latitudeText);
                              TextView longText = findViewById(R.id.longitudeText);
                              latText.setText("latitude: " + String.valueOf(latitude) );
                              longText.setText("longitude: " + String.valueOf(longitude));
                          }
                        }
                      }, Looper.getMainLooper());
                    //Snackbar snack3 = Snackbar.make(locationLayout, "Hitting Bottom", Snackbar.LENGTH_LONG);
                    //snack3.show();
                  }else{
                    activateGPS();
                  }
                 }else{
                  requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
            }else{
              Snackbar snack = Snackbar.make(locationLayout, "Permission Auto-Granted", Snackbar.LENGTH_LONG);
              snack.show();
            }
          }
//                                           TextView latText = findViewById(R.id.latitudeText);
//                                           TextView longText = findViewById(R.id.longitudeText);
//                                           latText.setText("blah");
//                                           longText.setText("blah");
//                                         }
           }
          );
        };

}
