package com.dojo.nowsportsreviewapp.ui.tabelas;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TabelasFragment extends Fragment {

    public RecyclerView minhaListaNews;
    public ArrayList<Noticia> listaNoticia;
    private Context context;
    public JsonTask meuJson;
    private static String minhaUrlTeste = "https://www.json-generator.com/api/json/get/cvlKbvCoaG?indent=2";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabelas, container, false);

        listaNoticia = new ArrayList<>();
        meuJson = new JsonTask();
        meuJson.execute(minhaUrlTeste);

        minhaListaNews = view.findViewById(R.id.minhaListaNews);
        context.getApplicationContext();

        // precisa arrumar um jeito de pegar o context
        MeuAdaptador meuAdaptador = new MeuAdaptador(listaNoticia, context);
        minhaListaNews.setAdapter(meuAdaptador);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        minhaListaNews.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                JSONObject listaJson = new JSONObject(result);
                JSONArray rodada = listaJson.getJSONArray("questionario");
                String tituloCampeonato = listaJson.getString("titulo");

                for (int i = 0; i < rodada.length(); i++) {
                    JSONObject noticia = rodada.getJSONObject(i);;

                    String timeCasa = noticia.getString("timeCasa");
                    String timeVisitante = noticia.getString("timeVisitante");
                    String siglaCasa = noticia.getString("siglaCasa");
                    String siglaVisitante = noticia.getString("siglaVisitante");
                    String placar = noticia.getString("placar");


                    Noticia minhaNoticia = new Noticia(timeCasa, timeVisitante, siglaCasa, siglaVisitante, placar, tituloCampeonato);
                    listaNoticia.add(minhaNoticia);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}