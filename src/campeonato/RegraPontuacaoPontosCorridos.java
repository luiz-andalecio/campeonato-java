package campeonato;

public class RegraPontuacaoPontosCorridos implements RegraPontuacao {

    @Override
    public void ganhar(Time time) {
        time.adicionarPontos(3);
    }

    @Override
    public void empatar(Time time) {
        time.adicionarPontos(1);
    }

    @Override
    public void perder(Time time) {
        time.adicionarPontos(0);
    }
}
