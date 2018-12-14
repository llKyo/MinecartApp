package cl.kyo.minecartapp.activies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cl.kyo.minecartapp.R;
import cl.kyo.minecartapp.dto.Minecart;
import cz.msebera.android.httpclient.Header;

public class AgregarMinecart extends AppCompatActivity {

    EditText editPatente;
    EditText editModelo;
    EditText editNroFlota;
    CheckBox cbxBasteria;
    CheckBox cbxPuertaCabina;
    CheckBox cbxPuertaBoveda;
    CheckBox cbxCamaraPreBoveda;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_minecart);
        init();
    }

    private void init() {
        editPatente = findViewById(R.id.editPatente);
        editModelo = findViewById(R.id.editModelo);
        editNroFlota = findViewById(R.id.editNroFlota);
        cbxBasteria = findViewById(R.id.cbxBasteria);
        cbxPuertaCabina = findViewById(R.id.cbxPuertaCabina);
        cbxPuertaBoveda = findViewById(R.id.cbxPuertaBoveda);
        cbxCamaraPreBoveda = findViewById(R.id.cbxCamaraPreBoveda);
    }

    public void agregarMinecart(View view) {
        if (validar()){
            Minecart minecart = new Minecart();
            minecart.setPatente(editPatente.getText().toString());
            minecart.setModelo(editModelo.getText().toString());
            minecart.setNumFlota(Integer.parseInt(editNroFlota.getText().toString()));
            minecart.setBateria(cbxBasteria.isChecked()?1:0);
            minecart.setBoveda(cbxPuertaBoveda.isChecked()?1:0);
            minecart.setCabina(cbxPuertaCabina.isChecked()?1:0);
            minecart.setPreBoveda(cbxCamaraPreBoveda.isChecked()?1:0);

            String url = "http://jriquelme.ddns.net/apis/api-pronks/getCheckout.php";
            AsyncHttpClient cliente = new AsyncHttpClient();
            RequestParams params = new RequestParams();

            params.put("patente", minecart.getPatente());
            params.put("modelo", minecart.getModelo());
            params.put("numFlota", minecart.getNumFlota());
            params.put("bateria", minecart.getBateria());
            params.put("cabina", minecart.getCabina());
            params.put("boveda", minecart.getBoveda());
            params.put("preBoveda", minecart.getPreBoveda());

            cliente.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    if (statusCode == 200) {

                        if (new String(responseBody).contains("error")) {
                            Toast.makeText(AgregarMinecart.this,
                                    new String(responseBody),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AgregarMinecart.this,
                                    "Minecart agregado " + new String(responseBody),
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }


    }

    private boolean validar() {
        return true;
    }
}
