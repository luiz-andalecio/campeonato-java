package campeonato;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CampeonatoComPenalidade extends CampeonatoDecorador {

    private Map<Time, Integer> penalidades;

    public CampeonatoComPenalidade(CompeticaoFutebol campeonatoDecorante) {
        super(campeonatoDecorante);
        this.penalidades = new HashMap<>();
    }

    @Override
    public void adicionarTime(Time t) {
        penalidades.put(t, 0);
        super.adicionarTime(t);
    }

    public void aplicarPenalidade(Time time, int pontos) {
        Objects.requireNonNull(time, "Time não pode ser nulo");
        if (pontos <= 0) {
            throw new IllegalArgumentException("Pontos de penalidade devem ser positivos");
        }

        time.adicionarPontos(-pontos);
        penalidades.put(time, penalidades.getOrDefault(time, 0) + pontos);
        System.out.println("[PENALIDADE] " + time.getNome() + " recebeu uma penalidade de " 
                         + pontos + " ponto(s). Total de penalidades: " + penalidades.get(time));
    }

    @Override
    public void mostrarSituacao() {
        System.out.println("\n=== CAMPEONATO COM PENALIDADES ===");
        super.mostrarSituacao();
        
        System.out.println("\n--- Penalidades Aplicadas ---");
        boolean temPenalidades = false;
        for (Map.Entry<Time, Integer> entry : penalidades.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println(entry.getKey().getNome() + ": " + entry.getValue() + " ponto(s) removido(s)");
                temPenalidades = true;
            }
        }
        if (!temPenalidades) {
            System.out.println("Nenhuma penalidade aplicada.");
        }
    }
}
