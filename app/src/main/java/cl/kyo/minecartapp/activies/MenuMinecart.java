package cl.kyo.minecartapp.activies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import cl.kyo.minecartapp.R;
import cl.kyo.minecartapp.utils.Menu;

public class MenuMinecart extends AppCompatActivity {

    ListView listViewMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_minecart);
        this.init();
        this.llenarLista();
        this.iniciarListener();
    }

    private void init(){
        this.listViewMenu = findViewById(R.id.listViewMenu);
    }

    private void llenarLista(){
        ArrayAdapter<Menu> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                Menu.values());

        this.listViewMenu.setAdapter(adapter);
    }

    private void iniciarListener() {

        this.listViewMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Menu seleccion = Menu.values()[i];
                Intent intent;
                switch (seleccion) {

                    case CHECKOUT_MINECART:

                        intent = new Intent(MenuMinecart.this, AgregarMinecart.class);
                        startActivity(intent);

                        break;
                    case LISTAR_CHECKOUT_MINECART:

                        intent = new Intent(MenuMinecart.this, ListarMinecart.class);
                        startActivity(intent);

                        break;
                }

            }
        });
    }
}
