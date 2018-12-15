package cl.kyo.minecartapp.activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cl.kyo.minecartapp.R;
import cl.kyo.minecartapp.dto.Usuario;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    EditText editUsername;
    EditText editPassword;
    List<Usuario> usuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);
    }
    public void ingresar(View v){
        String username = editUsername.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        traerUsuarios();
        for (int i = 0 ; i<=usuarios.size(); i++ ){
            String usernameActual, passwordActual;
            usernameActual = usuarios.get(i).getUsername();
            passwordActual = usuarios.get(i).getPassword();
            if (username.equals(usernameActual) && password.equals(passwordActual)){
                Intent intent;
                intent = new Intent(MainActivity.this, MenuMinecart.class);
                startActivity(intent);
            }
        }
    }

    private void traerUsuarios(){
        String url = "localhost/api-minecarts/getBuses.php";
        AsyncHttpClient cliente = new AsyncHttpClient();
        cliente.post(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200){
                    procesarRespuesta(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void procesarRespuesta(String response) {

        try {

            usuarios.clear();
            JSONArray json = new JSONArray(response);

            for (int i = 0; i < json.length(); i++) {

                Usuario usuario = new Usuario();
                usuario.setId(json.getJSONObject(i).getInt("id"));
                usuario.setUsername(json.getJSONObject(i).getString("username"));
                usuario.setPassword(json.getJSONObject(i).getString("password"));

                usuarios.add(usuario);

            }

        } catch (Exception ex) {
            Log.e("ERROR", ex.toString());
        }

    }
    public void limpiar(View v){
        editUsername.setText("");
        editPassword .setText("");
    }
}
