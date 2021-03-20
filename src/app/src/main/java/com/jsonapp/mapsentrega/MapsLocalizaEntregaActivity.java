package com.jsonapp.mapsentrega;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.jsonapp.mapsentrega.entregas.Localizacao;

import java.io.IOException;
import java.util.List;

public class MapsLocalizaEntregaActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static final String[] PERMISSOES = {
        Manifest.permission.ACCESS_FINE_LOCATION
    };
    private static final int REQUESTCODE = 1;
    private static final int PLACE_PICKER_REQUEST = 1;
    private GoogleApiClient googleApiClient;
    private GoogleApiClient mGoogleApiCLient;
    private SupportMapFragment mapFragment;
    private Location mLocation;
    private RequestQueue requestQueue;
    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int read = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(read == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, PERMISSOES, REQUESTCODE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_localiza_entrega);

        if(mGoogleApiCLient == null) {
            mGoogleApiCLient = new GoogleApiClient
                    .Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .build();
        }

        this.requestQueue = new Volley().newRequestQueue(getApplicationContext());

    }

    @Override
    protected void onStart() {
        super.onStart();

        mGoogleApiCLient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mGoogleApiCLient.disconnect();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Geocoder geocoder = new Geocoder(getApplicationContext());
        try {
            List<Address> location = geocoder.getFromLocation(mLocation.getLatitude(), mLocation.getLongitude(), 1);
            String endereco = String.format("%s, %s, %s - %s",
                    location.get(0).getThoroughfare(),
                    location.get(0).getSubThoroughfare(),
                    location.get(0).getSubLocality(),
                    location.get(0).getSubAdminArea());

            LatLng sydney = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
            googleMap.addMarker(new MarkerOptions()
                    .position(sydney)
                    .title(endereco));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        int read = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if(mGoogleApiCLient == null){
            mGoogleApiCLient = new GoogleApiClient
                    .Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .build();
        }


        LocationServices
                .getFusedLocationProviderClient(this)
                .getLastLocation().addOnSuccessListener(this, location-> {
                    if(location != null) {
                        mLocation = location;
                        mapFragment.getMapAsync(this);
                    }
                });

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}