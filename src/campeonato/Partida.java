package campeonato;

public class Partida {

    private final Time casa;
    private final Time visitante;
    private final RegraResultado regraPartida;

    public Partida(Time casa, Time visitante, RegraResultado regraPartida) {
        this.casa = casa;
        this.visitante = visitante;
        this.regraPartida = regraPartida;
    }

    public void jogar(int golsCasa, int golsVisitante) {
        regraPartida.aplicarResultado(casa, visitante, golsCasa, golsVisitante);
    }
}