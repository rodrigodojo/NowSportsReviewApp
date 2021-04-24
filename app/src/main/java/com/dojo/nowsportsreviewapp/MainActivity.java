package com.dojo.nowsportsreviewapp;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public ArrayList<Noticia> listaNoticia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_principal, R.id.nav_tabelas, R.id.nav_contato,R.id.nav_sobre)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public void enviarEmail(){

        String email = "email_support@gmail.com";
        Intent intent = new Intent( Intent.ACTION_SEND, Uri.parse(email));
        intent.putExtra(Intent.EXTRA_EMAIL , new String[]{email});
        intent.putExtra(Intent.EXTRA_SUBJECT , "Contato pelo aplicativo NowSportReview");
        intent.putExtra(Intent.EXTRA_TEXT, "Mensagem Automática");

        intent.setType("message/rfc822");

        startActivity( Intent.createChooser(intent,"Compartilhar") );
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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
                //connection.setRequestProperty(nomeUsuario,autorizacao);
                connection.connect();
                Log.i("meuLog", "conectou com sucesso");

                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();

                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);
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