package com.alexiscv.t11ej1_preferenciasparapantalladeconfiguracion;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Como esta activity la hemos creado a mano
 * tendremos que registrarla en el manifest manualmente.
 * <activity
 * android:name=".SettingsActivity"
 * android:label="@string/app_name">
 * </activity>
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        // Cargamos las preferencias desde un recurso XML
        addPreferencesFromResource(R.xml.mis_preferencias);

    }
}
