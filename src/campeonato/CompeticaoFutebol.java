package campeonato;

public interface CompeticaoFutebol {

    void adicionarTime(Time t);

    void iniciarCampeonato();

    Time registrarPartida(Time casa, Time visitante, int golsCasa, int golsVisitante);

    void mostrarSituacao();
}
