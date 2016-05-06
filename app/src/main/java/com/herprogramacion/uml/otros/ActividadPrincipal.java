package com.herprogramacion.uml.otros;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import com.herprogramacion.uml.R;
import com.herprogramacion.uml.ahorcado_uml.MainAhorcado;
import com.herprogramacion.uml.correo.EnviarCorreoActivity;
import com.herprogramacion.uml.grafica.GraficoActivityDos;
import com.herprogramacion.uml.quiz.Grafica;


public class ActividadPrincipal extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        agregarToolbar();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        if (navigationView != null) {
            prepararDrawer(navigationView);
            // Seleccionar item por defecto
            seleccionarItem(navigationView.getMenu().getItem(0));
        }
    }
    private void seleccionarInicio(Menu menu){


    }

    private void agregarToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            // Poner ícono del drawer toggle
            ab.setHomeAsUpIndicator(R.drawable.drawer_toggle);
            ab.setDisplayHomeAsUpEnabled(true);
        }

    }

    private void prepararDrawer(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        seleccionarItem(menuItem);
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void seleccionarItem(MenuItem itemDrawer) {
        Fragment fragmentoGenerico = null;
        FragmentManager fragmentManager = getSupportFragmentManager();


        switch (itemDrawer.getItemId()) {
            case R.id.item_inicio:
                fragmentoGenerico = new FragmentoInicio();
                break;
            case R.id.item_capitulos:
                startActivity(new Intent(this, CapitulosActivity.class));
                break;
            case R.id.item_acerca:
                fragmentoGenerico = new FragmentDesarrollador();
                break;
            case R.id.item_configuracion:
                startActivity(new Intent(this, ActividadConfiguracion.class));
                break;
            case R.id.item_videos:
                startActivity(new Intent(this, ActividadVideo.class));
                break;
            case R.id.item_audios:
                startActivity(new Intent(this, ActividadAudio.class));
                break;
            case R.id.item_juego:
                startActivity(new Intent(this, MainAhorcado.class));
                break;
            case R.id.item_grafica:
                startActivity(new Intent(this, Grafica.class));
                break;
            case R.id.item_card:
                startActivity(new Intent(this, GraficoActivityDos.class));
                break;
            case R.id.item_correo:
                startActivity(new Intent(this, EnviarCorreoActivity.class));
                break;

        }
        if (fragmentoGenerico != null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.contenedor_principal, fragmentoGenerico)
                    .commit();
        }

        // Setear título actual
        setTitle(itemDrawer.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.itm_menu_ayuda_sugerencia:
                startActivity(new Intent(ActividadPrincipal.this, ActividadPrincipal.class));
                break;
            case R.id.itm_salir:
                itmSalida();
                break;
            case R.id.itm_ayuda:
                startActivity(new Intent(ActividadPrincipal.this, AyudaActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void itmSalida(){
        finish();
        Intent intent1=new Intent(Intent.ACTION_MAIN);
        intent1.addCategory(Intent.CATEGORY_HOME);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent1);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


    }
}
