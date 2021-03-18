package com.jsonapp.mapsentrega;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.jsonapp.mapsentrega.entregas.LocaisAdapter;
import com.jsonapp.mapsentrega.entregas.LocalEntrega;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rc_lista_locais_entrega;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalEntrega[] localEntregas = new LocalEntrega[]{
                new LocalEntrega("Avenida Giuliano Checchettine", "476", "Vila Bela", "Franco da Rocha"),
                new LocalEntrega("Avenida Lafranch", "1", "Vila Bela", "Franco da Rocha")
        };

        this.rc_lista_locais_entrega = findViewById(R.id.rc_lista_locais_entrega);
        this.rc_lista_locais_entrega.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.rc_lista_locais_entrega.setAdapter(new LocaisAdapter(localEntregas));
        this.rc_lista_locais_entrega.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(MainActivity.this, MapsLocalizaEntregaActivity.class);
                startActivity(intent);
                return false;
            }
        });

    }
}