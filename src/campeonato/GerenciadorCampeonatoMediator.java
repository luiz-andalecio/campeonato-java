package campeonato;

import java.util.ArrayList;
import java.util.List;

public class GerenciadorCampeonatoMediator implements IMediatorCampeonato {
    private List<Time> timesInscritos;

    public GerenciadorCampeonatoMediator() {
        this.timesInscritos = new ArrayList<>();
    }

    @Override
    public void registrarTime(Time time) {
        if (!timesInscritos.contains(time)) {
            timesInscritos.add(time);
            time.setMediator(this);
            System.out.println("O time " + time.getNome() + " foi registrado no campeonato.");
        }
    }

    @Override
    public void reportarResultado(Time mandante, Time visitante, int golsMandante, int golsVisitante) {
        System.out.println("\n[Mediador] Processando partida: " + mandante.getNome() + " " + golsMandante + " x " + golsVisitante + " " + visitante.getNome());
        
        mandante.registrarPartida(golsMandante, golsVisitante, true);
        visitante.registrarPartida(golsVisitante, golsMandante, false);

        if (golsMandante > golsVisitante) {
            mandante.registrarVitoria();
            mandante.adicionarPontos(3);
            visitante.registrarDerrota();
        } else if (golsMandante < golsVisitante) {
            visitante.registrarVitoria();
            visitante.adicionarPontos(3);
            mandante.registrarDerrota();
        } else {
            mandante.registrarEmpate();
            mandante.adicionarPontos(1);
            visitante.registrarEmpate();
            visitante.adicionarPontos(1);
        }
    }
}