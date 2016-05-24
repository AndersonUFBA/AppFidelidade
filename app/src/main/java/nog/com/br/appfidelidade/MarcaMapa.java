package nog.com.br.appfidelidade;

/**
 * Created by edmilso on 5/16/2016.
 */
public class MarcaMapa {
    String nome;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    Double latitude;
    Double longitude;

    public MarcaMapa(Double latitude, Double longitude, String nome) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome = nome;
    }
}
