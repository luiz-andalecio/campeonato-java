package campeonato;

public class RegraPontuacaoCustomizada implements RegraPontuacao {

    @Override
    public void ganhar(Time time) {
        time.adicionarPontos(4);
        time.registrarVitoria();
    }

    @Override
    public void empatar(Time time) {
        time.adicionarPontos(2);
        time.registrarEmpate();
    }

    @Override
    public void perder(Time time) {
        time.registrarDerrota();
    }
}
