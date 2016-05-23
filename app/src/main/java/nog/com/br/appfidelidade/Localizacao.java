package nog.com.br.appfidelidade;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;


/**
 * Created by cardoso on 22/05/16.
 */

public class Localizacao implements android.location.LocationListener {

    private Location location;

    public Localizacao(Context c) {
        location = null;
        LocationManager locationManager = (LocationManager) c.getSystemService(c.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    }

    public void onLocationChanged(Location location) {
        this.location = location;

        //Este objeto location que ira retornar as coordenadas:
        double latitute = location.getLatitude();
        double longitude = location.getLongitude();
    }

    public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
        //chamado quando o estado muda de Provider. Status possíveis: OUT_OF_SERVICE, TEMPORARILY_UNAVAILABLE ou AVAILABLE.
    }

    public void onProviderEnabled(String arg0) {
        //chamado quando o provedor é ativada pelo usuário
    }

    public void onProviderDisabled(String arg0) {
        //chamado quando o provedor é desativado pelo usuário, se ele já estiver desativado, é chamado imediatamente após requestLocationUpdates
    }

    public Location getLocation() {
        return location;
    }
}