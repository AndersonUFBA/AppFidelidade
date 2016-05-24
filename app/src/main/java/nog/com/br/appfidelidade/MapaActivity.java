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
import android.widget.ListView;

import nog.com.br.appfidelidade.BonificacaoActivity;
import nog.com.br.appfidelidade.InserirPontuacaoActivity;
import nog.com.br.appfidelidade.ListarClientesActivity;
import nog.com.br.appfidelidade.LoginActivity;
import nog.com.br.appfidelidade.R;
import nog.com.br.appfidelidade.repository.MarcaMapaRepository;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;


public class MapaActivity extends AppCompatActivity {

    private LatLng location = new LatLng(-13.0011642, -38.5095745);
    private MarcaMapaRepository repository;
    //List<MarcaMapa> listaLoja = new ArrayList<MarcaMapa>();
    private ListView listaLoja ;
    List<MarcaMapa> listaLojaProxima = new ArrayList<MarcaMapa>();
     private LatLng location2 ;
    String nome = "" ;
    MarcaMapa loc = new MarcaMapa(-13.001104 , -38.507389, nome);

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        repository = new MarcaMapaRepository(this);
        List<MarcaMapa> listaLoja = repository.listarMarcaMapa();

       /* MarcaMapa a = new MarcaMapa(-13.001904,-38.507229,"testem1" );
        MarcaMapa b = new MarcaMapa(-13.001004,-38.506829,"testem2" );
        MarcaMapa c = new MarcaMapa(-14.001004,-38.506829,"testem2" );
        listaLoja.add(b);
        listaLoja.add(a);
        listaLoja.add(c);*/

        nome ="teste" ;
        for (MarcaMapa obj : listaLoja) {
            nome ="1";
            double latmenor = loc.latitude - 0.006 ;
            double latmaior = loc.latitude + 0.006 ;
            double logmenor = loc.longitude - 0.003 ;
            double logmaior = loc.longitude + 0.003 ;
            if ( obj.latitude > latmenor & obj.latitude < latmaior)
            {
                nome ="2";
                if(obj.longitude > logmenor & obj.longitude < logmaior)
                {
                    listaLojaProxima.add(obj);
                }

            }
        }
        for (MarcaMapa obj: listaLojaProxima) {
            nome ="3";
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            location2 = new LatLng(obj.latitude,  obj.longitude);
            map.addMarker(new MarkerOptions().position(location2).title(obj.nome)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

        }


        map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        map.addMarker(new MarkerOptions().position(location).title(nome));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 20));
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
    }
}
