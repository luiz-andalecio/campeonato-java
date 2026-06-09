package campeonato;

public class RegraDesempatePenaltis implements RegraDesempate {

    @Override
    public Time decidirVencedor(Time casa, Time visitante) {
        if (casa == null || visitante == null) {
            throw new IllegalArgumentException("Times nao podem ser nulos");
        }

        int comparacao = casa.getNome().compareToIgnoreCase(visitante.getNome());
        if (comparacao <= 0) {
            return casa;
        }
        return visitante;
    }
}
