package campeonato;

public abstract class CampeonatoDecorador implements CompeticaoFutebol {

    protected CompeticaoFutebol campeonatoDecorante;

    public CampeonatoDecorador(CompeticaoFutebol campeonatoDecorante) {
        this.campeonatoDecorante = campeonatoDecorante;
    }

    @Override
    public void adicionarTime(Time t) {
        campeonatoDecorante.adicionarTime(t);
    }

    @Override
    public void iniciarCampeonato() {
        campeonatoDecorante.iniciarCampeonato();
    }

    @Override
    public Time registrarPartida(Time casa, Time visitante, int golsCasa, int golsVisitante) {
        return campeonatoDecorante.registrarPartida(casa, visitante, golsCasa, golsVisitante);
    }

    @Override
    public void mostrarSituacao() {
        campeonatoDecorante.mostrarSituacao();
    }
}
