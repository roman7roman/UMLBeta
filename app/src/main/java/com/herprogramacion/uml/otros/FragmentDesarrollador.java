package com.herprogramacion.uml.otros;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.herprogramacion.uml.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDesarrollador extends Fragment {

    private Button btnCorreo;


    public FragmentDesarrollador() {
        // Requiere de un contructor vacio
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.developer_layout, container, false);
        return view;
    }

}
