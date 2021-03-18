package com.jsonapp.mapsentrega.entregas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jsonapp.mapsentrega.R;

public class LocaisAdapter extends RecyclerView.Adapter<LocalViewHolder> {

    private final LocalEntrega[] localEntregas;

    public LocaisAdapter(LocalEntrega[] localEntregas) {
        this.localEntregas = localEntregas;
    }

    @NonNull
    @Override
    public LocalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_local, parent, false);
        return new LocalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalViewHolder holder, int position) {
        holder.tv_logradouro.setText(this.localEntregas[position].getLogradouro());
        holder.tv_numero.setText(this.localEntregas[position].getNumero());
        holder.tv_bairro.setText(this.localEntregas[position].getBairro());
        holder.tv_cidade.setText(this.localEntregas[position].getCidade());
    }

    @Override
    public int getItemCount() {
        return this.localEntregas.length;
    }
}
