package campeonato;

public interface RegraPontuacao {

    void ganhar(Time time);

    void empatar(Time time);

    void perder(Time time);
}
