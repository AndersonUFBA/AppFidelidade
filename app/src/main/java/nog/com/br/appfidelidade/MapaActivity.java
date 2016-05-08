package nog.com.br.appfidelidade;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import nog.com.br.appfidelidade.BonificacaoActivity;
import nog.com.br.appfidelidade.InserirPontuacaoActivity;
import nog.com.br.appfidelidade.ListarClientesActivity;
import nog.com.br.appfidelidade.LoginActivity;
import nog.com.br.appfidelidade.R;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapaActivity extends AppCompatActivity {

    private LatLng location = new LatLng(-35.035662, -51.235472);

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

        map.addMarker(new MarkerOptions().position(location).title("Você esta Aqui"));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 20));
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }
}

