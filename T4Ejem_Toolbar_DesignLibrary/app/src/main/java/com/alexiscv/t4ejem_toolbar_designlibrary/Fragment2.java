package com.alexiscv.t4ejem_toolbar_designlibrary;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment2 extends Fragment {

    /**
     * Cre un nuevo Objeto y lo devuelve
     *
     * @return
     */
    public static Fragment2 newInstance() {
        Fragment2 fragment = new Fragment2();
        return fragment;
    }

    public Fragment2() {
        // Es necesario un constructor publico vacio.
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        return inflater.inflate(R.layout.fragment_2, container, false);

    }

}
