package campeonato;

import java.util.ArrayList;
import java.util.Objects;

public class CampeonatoComValidacao extends CampeonatoDecorador {

    private ArrayList<Time> timesRegistrados;

    public CampeonatoComValidacao(CompeticaoFutebol campeonatoDecorante) {
        super(campeonatoDecorante);
        this.timesRegistrados = new ArrayList<>();
    }

    @Override
    public void adicionarTime(Time t) {
        Objects.requireNonNull(t, "Time não pode ser nulo");
        timesRegistrados.add(t);
        super.adicionarTime(t);
    }

    @Override
    public Time registrarPartida(Time casa, Time visitante, int golsCasa, int golsVisitante) {
        if (casa == visitante) {
            throw new IllegalArgumentException("Times devem ser diferentes");
        }

        if (!timesRegistrados.contains(casa)) {
            throw new IllegalArgumentException("Time " + casa.getNome() + " não está registrado no campeonato");
        }

        if (!timesRegistrados.contains(visitante)) {
            throw new IllegalArgumentException("Time " + visitante.getNome() + " não está registrado no campeonato");
        }

        if (golsCasa < 0 || golsVisitante < 0) {
            throw new IllegalArgumentException("Gols não podem ser negativos");
        }

        System.out.println("[VALIDAÇÃO] Partida válida: " + casa.getNome() + " vs " + visitante.getNome());
        return super.registrarPartida(casa, visitante, golsCasa, golsVisitante);
    }

    @Override
    public void mostrarSituacao() {
        System.out.println("\n=== CAMPEONATO COM VALIDAÇÃO ===");
        super.mostrarSituacao();
    }
}
