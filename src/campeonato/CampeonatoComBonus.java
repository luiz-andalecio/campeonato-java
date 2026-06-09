package campeonato;

import java.util.HashMap;
import java.util.Map;

public class CampeonatoComBonus extends CampeonatoDecorador {

    private Map<Time, Integer> vitorias_consecutivas;
    private final int BONUS_POR_VITORIA_CONSECUTIVA = 1;
    private final int MINIMO_VITORIAS_PARA_BONUS = 3;

    public CampeonatoComBonus(CompeticaoFutebol campeonatoDecorante) {
        super(campeonatoDecorante);
        this.vitorias_consecutivas = new HashMap<>();
    }

    @Override
    public void adicionarTime(Time t) {
        vitorias_consecutivas.put(t, 0);
        super.adicionarTime(t);
    }

    @Override
    public Time registrarPartida(Time casa, Time visitante, int golsCasa, int golsVisitante) {
        Time vencedor = super.registrarPartida(casa, visitante, golsCasa, golsVisitante);

        if (golsCasa > golsVisitante) {
            vitorias_consecutivas.put(casa, vitorias_consecutivas.getOrDefault(casa, 0) + 1);
            vitorias_consecutivas.put(visitante, 0); // Reseta consecutivas do visitante

            aplicarBonus(casa);
        } else if (golsVisitante > golsCasa) {
            // Visitante venceu
            vitorias_consecutivas.put(visitante, vitorias_consecutivas.getOrDefault(visitante, 0) + 1);
            vitorias_consecutivas.put(casa, 0); // Reseta consecutivas da casa
            
            aplicarBonus(visitante);
        } else {
            // Empate - reseta ambos
            vitorias_consecutivas.put(casa, 0);
            vitorias_consecutivas.put(visitante, 0);
        }

        return vencedor;
    }

    private void aplicarBonus(Time time) {
        int consecutivas = vitorias_consecutivas.get(time);
        if (consecutivas >= MINIMO_VITORIAS_PARA_BONUS) {
            int bonus = (consecutivas - MINIMO_VITORIAS_PARA_BONUS + 1) * BONUS_POR_VITORIA_CONSECUTIVA;
            time.adicionarPontos(bonus);
            System.out.println("[BÔNUS] " + time.getNome() + " recebeu " + bonus 
                             + " ponto(s) de bônus por " + consecutivas + " vitória(s) consecutiva(s)!");
        }
    }

    @Override
    public void mostrarSituacao() {
        System.out.println("\n=== CAMPEONATO COM BÔNUS POR VITÓRIAS CONSECUTIVAS ===");
        super.mostrarSituacao();
        
        System.out.println("\n--- Vitórias Consecutivas ---");
        for (Map.Entry<Time, Integer> entry : vitorias_consecutivas.entrySet()) {
            if (entry.getValue() > 0) {
                System.out.println(entry.getKey().getNome() + ": " + entry.getValue() + " vitória(s)");
            }
        }
    }
}
