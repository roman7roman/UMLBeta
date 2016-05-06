package com.herprogramacion.uml.otros;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.herprogramacion.uml.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMaterial extends Fragment {


    public FragmentMaterial() {
        // Requiere de un contructor vacio
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragmento_paginado, container, false);
    }

}
