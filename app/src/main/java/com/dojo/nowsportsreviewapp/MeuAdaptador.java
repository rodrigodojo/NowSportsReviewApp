package com.dojo.nowsportsreviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MeuAdaptador extends RecyclerView.Adapter {

    ArrayList<Noticia> listaNoticia;
    Context context;

    public MeuAdaptador(ArrayList<Noticia> listaNoticia, Context context) {
        this.listaNoticia = listaNoticia;
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.forma_noticia,parent,false);
        MeuViewHolder meuViewHolder = new MeuViewHolder(view);
        return meuViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MeuViewHolder meuViewHolder = (MeuViewHolder) holder;
        Noticia minhaNoticia = listaNoticia.get(position);

        meuViewHolder.placar.setText(minhaNoticia.getPlacar());
        meuViewHolder.siglaCasa.setText(minhaNoticia.getSiglaCasa());
        meuViewHolder.timeCasa.setText(minhaNoticia.getTimeCasa());
        meuViewHolder.siglaVisitante.setText(minhaNoticia.getSiglaVisitante());
        meuViewHolder.timeVisitante.setText(minhaNoticia.getTimeVisitante());
        meuViewHolder.titulo.setText(minhaNoticia.getTituloCampeonato());

    }

    @Override
    public int getItemCount() {
        return listaNoticia.size();
    }
}
