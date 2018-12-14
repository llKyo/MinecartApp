package cl.kyo.minecartapp.activies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cl.kyo.minecartapp.R;
import cl.kyo.minecartapp.dto.Minecart;
import cz.msebera.android.httpclient.Header;

public class ListarMinecart extends AppCompatActivity {

    private ListView listViewMinecart;
    private List<Minecart> listadoMinecart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_minecart);
        this.init();
        this.llenarListado();
    }

    private void llenarListado() {
        String url = "http://jriquelme.ddns.net/apis/api-pronks/getCheckout.php";
        AsyncHttpClient cliente = new AsyncHttpClient();

        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                switch (statusCode) {
                    case 200:

                        procesarRespuesta(new String(responseBody));
                        llenarListView();
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void init() {
        this.listViewMinecart = findViewById(R.id.listViewMinecart);
    }

    private void llenarListView(){
        ArrayAdapter<Minecart> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                this.listadoMinecart
        );
        this.listViewMinecart.setAdapter(adapter);
    }

    private void procesarRespuesta(String response){
        try{
            this.listadoMinecart.clear();
            JSONArray json = new JSONArray(response);
            for (int i = 0; i < json.length(); i++) {
                Minecart minecart = new Minecart();
                minecart.setId(json.getJSONObject(i).getInt("id"));
                minecart.setPatente(json.getJSONObject(i).getString("patente"));
                minecart.setModelo(json.getJSONObject(i).getString("modelo"));
                minecart.setNumFlota(json.getJSONObject(i).getInt("numFlota"));
                minecart.setBateria(json.getJSONObject(i).getInt("bateria"));
                minecart.setCabina(json.getJSONObject(i).getInt("cabina"));
                minecart.setBoveda(json.getJSONObject(i).getInt("boveda"));
                minecart.setPreBoveda(json.getJSONObject(i).getInt("preBoveda"));

                this.listadoMinecart.add(minecart);

            }
        }catch (Exception ex){
            Log.e("ERROR", ex.toString());
        }
    }
}
