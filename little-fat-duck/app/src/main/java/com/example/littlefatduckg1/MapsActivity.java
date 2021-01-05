package com.example.littlefatduckg1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GoogleMap mMap;
    SupportMapFragment supportMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        if(ActivityCompat.checkSelfPermission(MapsActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            supportMapFragment.getMapAsync(this);
        }
        else{
            ActivityCompat.requestPermissions(MapsActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 404);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng LFD_1U = new LatLng(3.1490541, 101.6160813);
        mMap.addMarker(new MarkerOptions().position(LFD_1U).title("LFD 1 Utama"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LFD_1U, 10));

        LatLng LFD_TCM = new LatLng(3.1302613, 101.6269094);
        mMap.addMarker(new MarkerOptions().position(LFD_TCM).title("LFD Tropicana City Mall"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LFD_TCM, 10));

        LatLng LFD_MyTown = new LatLng(3.1345809, 101.7240218);
        mMap.addMarker(new MarkerOptions().position(LFD_MyTown).title("LFD MyTOWN Shopping Centre"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LFD_MyTown, 10));

        LatLng LFD_Bangsar = new LatLng(3.1283698, 101.6793402);
        mMap.addMarker(new MarkerOptions().position(LFD_Bangsar).title("LFD Jalan Bangsar"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LFD_Bangsar, 10));

        LatLng LFD_ECM= new LatLng(3.1101692, 101.5866695);
        mMap.addMarker(new MarkerOptions().position(LFD_ECM).title("LFD Evolve Concept Mall"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LFD_ECM, 10));

        LatLng LFDxYumiBoba_JlnCamar = new LatLng(3.1533970, 101.5790339);
        mMap.addMarker(new MarkerOptions().position(LFDxYumiBoba_JlnCamar).title("LFD & Yumi Bubble Tea (Jalan Camar)"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LFDxYumiBoba_JlnCamar, 10));

        LatLng LFDxYumiBoba_Glomac = new LatLng(3.1372263, 101.6151881);
        mMap.addMarker(new MarkerOptions().position(LFDxYumiBoba_Glomac).title("LFD & Yumi Bubble Tea (Glomac Centro)"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LFDxYumiBoba_Glomac, 10));

        LatLng LFD_TheMines = new LatLng(3.0277205, 101.7187046);
        mMap.addMarker(new MarkerOptions().position(LFD_TheMines).title("LFD The Mines"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LFD_TheMines, 10));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 404){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                supportMapFragment.getMapAsync(this);
            }
        }
    }
}