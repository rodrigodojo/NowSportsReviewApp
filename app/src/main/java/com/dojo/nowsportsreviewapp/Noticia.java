package com.dojo.nowsportsreviewapp;

public class Noticia {

    public String timeCasa;
    public String timeVisitante;
    public String siglaCasa;
    public String siglaVisitante;
    public String placar;
    public String tituloCampeonato;

    public Noticia(String timeCasa, String timeVisitante, String siglaCasa, String siglaVisitante, String placar, String tituloCampeonato) {
        this.timeCasa = timeCasa;
        this.timeVisitante = timeVisitante;
        this.siglaCasa = siglaCasa;
        this.siglaVisitante = siglaVisitante;
        this.placar = placar;
        this.tituloCampeonato = tituloCampeonato;
    }

    public String getTimeCasa() {
        return timeCasa;
    }

    public void setTimeCasa(String timeCasa) {
        this.timeCasa = timeCasa;
    }

    public String getTimeVisitante() {
        return timeVisitante;
    }

    public void setTimeVisitante(String timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    public String getSiglaCasa() {
        return siglaCasa;
    }

    public void setSiglaCasa(String siglaCasa) {
        this.siglaCasa = siglaCasa;
    }

    public String getSiglaVisitante() {
        return siglaVisitante;
    }

    public void setSiglaVisitante(String siglaVisitante) {
        this.siglaVisitante = siglaVisitante;
    }

    public String getPlacar() {
        return placar;
    }

    public void setPlacar(String placar) {
        this.placar = placar;
    }

    public String getTituloCampeonato() {
        return tituloCampeonato;
    }

    public void setTituloCampeonato(String tituloCampeonato) {
        this.tituloCampeonato = tituloCampeonato;
    }
}
