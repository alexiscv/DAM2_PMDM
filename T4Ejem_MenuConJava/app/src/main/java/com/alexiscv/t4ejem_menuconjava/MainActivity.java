package com.alexiscv.t4ejem_menuconjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // IDS de las opciones del menú
    private static final int MNU_OPC1 = 1;
    private static final int MNU_OPC2 = 2;
    private static final int MNU_OPC3 = 3;
    private static final int MNU_OPC4 = 4;

    private static final int SMNU_OPC1 = 41;
    private static final int SMNU_OPC2 = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Llamo al método que me crea el menú
        crarMenu(menu);
        return true;
    }

    /**
     * Método propio, para crear el menú
     *
     * @param menu
     */
    private void crarMenu(Menu menu) {
        // El método add() devuelve un obj. de tipo MenuItem que
        // represetará una opción del menú
        // Sobre este obj. MenuItem podemos aplicar varios
        // métodos como setIcon(ID_de_un_drawable) para asignar un icono
        // setAlphabeticShortcut(caracter) asigna una tecla de atajo alfabética
        // para esa opción del menú
        MenuItem mnuOpc1 = menu.add(menu.NONE, MNU_OPC1, menu.NONE, "Opción 1");
        mnuOpc1.setIcon(android.R.drawable.ic_menu_preferences);

        MenuItem mnuOpc2 = menu.add(menu.NONE, MNU_OPC2, menu.NONE, "Opción 2");
        mnuOpc2.setAlphabeticShortcut('b');
        mnuOpc2.setIcon(android.R.drawable.ic_menu_compass);

        // Otra forma de añadir opciones, de forma más comprimida
        menu.add(menu.NONE, MNU_OPC3, menu.NONE, "Opción 3").setAlphabeticShortcut('c').setIcon(android.R.drawable.ic_menu_agenda);

        // Añadir un SubMenu
        SubMenu smnu = menu.addSubMenu(Menu.NONE, MNU_OPC4, Menu.NONE, "Opción 4").setIcon(android.R.drawable.ic_menu_agenda);
        smnu.add(Menu.NONE, SMNU_OPC1, Menu.NONE, "Opción 4.1");
        smnu.add(Menu.NONE, SMNU_OPC2, Menu.NONE, "Opción 4.2");

    }

    /**
     * Responder a eventos del menú
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MNU_OPC1:
                Toast.makeText(this, "Opción 1 pulsada!", Toast.LENGTH_SHORT).show();
                return true;

            case MNU_OPC2:
                Toast.makeText(this, "Opción 2 pulsada!", Toast.LENGTH_SHORT).show();
                return true;

            case MNU_OPC3:
                Toast.makeText(this, "Opción 3 pulsada!", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
