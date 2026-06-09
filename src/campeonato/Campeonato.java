package campeonato;

import java.util.ArrayList;

public class Campeonato implements CompeticaoFutebol {

    protected ArrayList<Time> times;

    public Campeonato() {
        times = new ArrayList<>();
    }

    public void adicionarTime(Time t) {
        times.add(t);
    }
    
    
    @Override
    public void iniciarCampeonato() {
        System.out.println("Campeonato iniciado!");
    }
    
    @Override
    public Time registrarPartida(Time casa, Time visitante, int golsCasa, int golsVisitante) {
        return casa;
    }
    
    @Override
    public void mostrarSituacao() {
        System.out.println("Campeonato Generico");
    }
}