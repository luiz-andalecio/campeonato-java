package campeonato;

public class RegraResultadoPontosCorridos implements RegraResultado {

    private final RegraPontuacao regraPontuacao;

    public RegraResultadoPontosCorridos (RegraPontuacao regraPontuacao) {
        this.regraPontuacao = regraPontuacao;
    }

    @Override
    public void aplicarResultado(Time casa, Time visitante, int golsCasa, int golsVisitante) {
        casa.registrarPartida(golsCasa, golsVisitante, true);
        visitante.registrarPartida(golsVisitante, golsCasa, false);
        if (golsCasa > golsVisitante) {
            casa.registrarVitoria();
            visitante.registrarDerrota();
            regraPontuacao.ganhar(casa);
            regraPontuacao.perder(visitante);
        } else if (golsVisitante > golsCasa) {
            casa.registrarDerrota();
            visitante.registrarVitoria();
            regraPontuacao.perder(casa);
            regraPontuacao.ganhar(visitante);
        } else {
            casa.registrarEmpate();
            visitante.registrarEmpate();
            regraPontuacao.empatar(casa);
            regraPontuacao.empatar(visitante);
        }
    }
}
