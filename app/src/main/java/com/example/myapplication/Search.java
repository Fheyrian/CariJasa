package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class Search extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap map;
    SupportMapFragment mapFragment;
    SearchView searchView;
    Button btTesting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

//        searchView = findViewById(R.id.sv_location);
//        btTesting = findViewById( R.id.btTesting );
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.google_map);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                String location = searchView.getQuery().toString();
//                List<Address> addressList = null;
//
//                if (location != null || !location.equals("")){
//                    Geocoder geocoder = new Geocoder(Search.this);
//                    try {
//                        addressList = geocoder.getFromLocationName(location, 1);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    Address address = addressList.get(0);
//                    LatLng latLng = new LatLng(address.getLatitude(),address.getLongitude());
//                    map.addMarker(new MarkerOptions().position(latLng).title(location));
//                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                return false;
//            }
//        });

        mapFragment.getMapAsync(this);

        searchView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d( "logging" ,"clicked");
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
                Intent intent = new Autocomplete.IntentBuilder( AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(Search.this);
                startActivityForResult(intent, 1);
            }
        } );

        btTesting.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d( "logging" , "btTesting clicked");
                List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME);
                Intent intent = new Autocomplete.IntentBuilder( AutocompleteActivityMode.FULLSCREEN, fields)
                        .build(Search.this);
                startActivityForResult(intent, 1);
            }
        } );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if(requestCode==1){
            if(resultCode==RESULT_OK){
                Gson json = new Gson();
                String result = json.toJson( data );
                Log.d( "logging" ,result);
            }
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
    }
}
