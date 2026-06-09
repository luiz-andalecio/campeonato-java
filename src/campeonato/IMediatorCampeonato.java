package campeonato;

public interface IMediatorCampeonato {
    void registrarTime(Time time);
    void reportarResultado(Time mandante, Time visitante, int golsMandante, int golsVisitante);
}