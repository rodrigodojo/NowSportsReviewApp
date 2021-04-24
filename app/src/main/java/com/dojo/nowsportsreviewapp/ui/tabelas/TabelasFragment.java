package com.dojo.nowsportsreviewapp.ui.tabelas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dojo.nowsportsreviewapp.MainActivity;
import com.dojo.nowsportsreviewapp.MeuAdaptador;
import com.dojo.nowsportsreviewapp.Noticia;
import com.dojo.nowsportsreviewapp.R;
import com.dojo.nowsportsreviewapp.ui.principal.PrincipalFragment;

import java.util.ArrayList;

public class TabelasFragment extends Fragment {

    public RecyclerView minhaListaNews;
    public static ArrayList<Noticia> listaNoticia;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabelas, container, false);

        minhaListaNews = view.findViewById(R.id.minhaListaNews);

        //MeuAdaptador meuAdaptador = new MeuAdaptador(listaNoticia, this.getContext());
        //minhaListaNews.setAdapter(meuAdaptador);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
        minhaListaNews.setLayoutManager(layoutManager);

        return view;
    }
}