package com.jsonapp.mapsentrega.entregas;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.jsonapp.mapsentrega.R;

public class LocalViewHolder extends RecyclerView.ViewHolder {
    public final AppCompatTextView tv_logradouro;
    public final AppCompatTextView tv_numero;
    public final AppCompatTextView tv_bairro;
    public final AppCompatTextView tv_cidade;

    public LocalViewHolder(@NonNull View itemView) {
        super(itemView);

        this.tv_logradouro = itemView.findViewById(R.id.tv_logradouro);
        this.tv_numero = itemView.findViewById(R.id.tv_numero);
        this.tv_bairro = itemView.findViewById(R.id.tv_bairro);
        this.tv_cidade = itemView.findViewById(R.id.tv_cidade);
    }
}
