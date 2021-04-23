package com.dojo.nowsportsreviewapp.ui.tabelas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dojo.nowsportsreviewapp.Noticia;
import com.dojo.nowsportsreviewapp.R;

import java.util.ArrayList;

public class TabelasFragment extends Fragment {

    public static ArrayList<Noticia> listaNoticia;
    Button botaoVoltar;
    public RecyclerView minhaListaNews;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        minhaListaNews = findViewById(R.id.minhaListaNews);

        MeuAdaptador meuAdaptador = new MeuAdaptador(listaNoticia,getApplicationContext());
        minhaListaNews.setAdapter(meuAdaptador);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        minhaListaNews.setLayoutManager(layoutManager);
*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tabelas, container, false);
    }
}