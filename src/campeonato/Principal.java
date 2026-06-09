package campeonato;

import java.io.PrintStream;

public class Principal {

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║     PADRÃO DECORATOR - CAMPEONATO FUTEBOLÍSTICO                   ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝\n");

        System.out.println("\n▶ EXEMPLO 1: CAMPEONATO COM VALIDAÇÃO");
        System.out.println("────────────────────────────────────────────────────────────────────\n");
        exemploCampeonatoComValidacao();

        System.out.println("\n\n▶ EXEMPLO 2: CAMPEONATO COM LOG");
        System.out.println("────────────────────────────────────────────────────────────────────\n");
        exemploCampeonatoComLog();

        System.out.println("\n\n▶ EXEMPLO 3: CAMPEONATO COM BÔNUS POR VITÓRIAS CONSECUTIVAS");
        System.out.println("────────────────────────────────────────────────────────────────────\n");
        exemploCampeonatoComBonus();

        System.out.println("\n\n▶ EXEMPLO 4: CAMPEONATO COM PENALIDADES");
        System.out.println("────────────────────────────────────────────────────────────────────\n");
        exemploCampeonatoComPenalidade();

        System.out.println("\n\n▶ EXEMPLO 5: CAMPEONATO COM MÚLTIPLOS DECORADORES");
        System.out.println("────────────────────────────────────────────────────────────────────\n");
        exemploCampeonatoComMultiplosDecoradores();

        System.out.println("\n╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                    FIM DA DEMONSTRAÇÃO                             ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");
    }

    private static void exemploCampeonatoComValidacao() {
        CriterioClassificacao criterio = new CriterioClassificacaoPontosCorridos();
        RegraResultado regra = new RegraResultadoPontosCorridos(
            new RegraPontuacaoPontosCorridos()
        );

        // Cria campeonato base e decora com validação
        CompeticaoFutebol campeonato = new CampeonatoPontosCorridos(criterio, regra);
        CompeticaoFutebol campeonatoValidado = new CampeonatoComValidacao(campeonato);

        Time a = new Time("Flamengo");
        Time b = new Time("Vasco");
        Time c = new Time("Botafogo");

        campeonatoValidado.adicionarTime(a);
        campeonatoValidado.adicionarTime(b);
        campeonatoValidado.adicionarTime(c);

        campeonatoValidado.iniciarCampeonato();

        campeonatoValidado.registrarPartida(a, b, 2, 1);
        campeonatoValidado.registrarPartida(b, c, 1, 0);

        // Tenta registrar com times diferentes (deve gerar erro)
        try {
            campeonatoValidado.registrarPartida(a, a, 1, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERRO CAPTURADO] " + e.getMessage());
        }

        // Tenta registrar com time não registrado (deve gerar erro)
        try {
            Time naoRegistrado = new Time("Palmeiras");
            campeonatoValidado.registrarPartida(a, naoRegistrado, 2, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERRO CAPTURADO] " + e.getMessage());
        }

        campeonatoValidado.mostrarSituacao();
    }

    private static void exemploCampeonatoComLog() {
        CriterioClassificacao criterio = new CriterioClassificacaoPontosCorridos();
        RegraResultado regra = new RegraResultadoPontosCorridos(
            new RegraPontuacaoPontosCorridos()
        );

        CompeticaoFutebol campeonato = new CampeonatoPontosCorridos(criterio, regra);
        CampeonatoComLog campeonatoComLog = new CampeonatoComLog(campeonato);

        Time x = new Time("São Paulo");
        Time y = new Time("Corinthians");

        campeonatoComLog.adicionarTime(x);
        campeonatoComLog.adicionarTime(y);

        campeonatoComLog.iniciarCampeonato();
        campeonatoComLog.registrarPartida(x, y, 3, 2);
        campeonatoComLog.registrarPartida(y, x, 1, 0);

        campeonatoComLog.mostrarSituacao();

        // Exibe o histórico de logs
        campeonatoComLog.mostrarLogs();
    }

    private static void exemploCampeonatoComBonus() {
        CriterioClassificacao criterio = new CriterioClassificacaoPontosCorridos();
        RegraResultado regra = new RegraResultadoPontosCorridos(
            new RegraPontuacaoPontosCorridos()
        );

        CompeticaoFutebol campeonato = new CampeonatoPontosCorridos(criterio, regra);
        CompeticaoFutebol campeonatoComBonus = new CampeonatoComBonus(campeonato);

        Time t1 = new Time("Atlético Mineiro");
        Time t2 = new Time("Grêmio");
        Time t3 = new Time("Internacional");

        campeonatoComBonus.adicionarTime(t1);
        campeonatoComBonus.adicionarTime(t2);
        campeonatoComBonus.adicionarTime(t3);

        campeonatoComBonus.iniciarCampeonato();

        // t1 vence 3 partidas consecutivas (recebe bônus a partir da 3ª)
        campeonatoComBonus.registrarPartida(t1, t2, 2, 0);
        System.out.println("t1 pontos: " + t1.getPontos());
        
        campeonatoComBonus.registrarPartida(t1, t3, 1, 0);
        System.out.println("t1 pontos: " + t1.getPontos());
        
        campeonatoComBonus.registrarPartida(t2, t1, 0, 2);
        System.out.println("t1 pontos: " + t1.getPontos());

        campeonatoComBonus.mostrarSituacao();
    }

    private static void exemploCampeonatoComPenalidade() {
        CriterioClassificacao criterio = new CriterioClassificacaoPontosCorridos();
        RegraResultado regra = new RegraResultadoPontosCorridos(
            new RegraPontuacaoPontosCorridos()
        );

        CompeticaoFutebol campeonato = new CampeonatoPontosCorridos(criterio, regra);
        CampeonatoComPenalidade campeonatoComPenalidade = new CampeonatoComPenalidade(campeonato);

        Time p1 = new Time("Santos");
        Time p2 = new Time("Cruzeiro");

        campeonatoComPenalidade.adicionarTime(p1);
        campeonatoComPenalidade.adicionarTime(p2);

        campeonatoComPenalidade.iniciarCampeonato();

        campeonatoComPenalidade.registrarPartida(p1, p2, 2, 0);
        System.out.println("Pontos de Santos antes da penalidade: " + p1.getPontos());

        // Aplica penalidade por mau comportamento
        campeonatoComPenalidade.aplicarPenalidade(p1, 2);
        System.out.println("Pontos de Santos após penalidade: " + p1.getPontos());

        campeonatoComPenalidade.mostrarSituacao();
    }

    private static void exemploCampeonatoComMultiplosDecoradores() {
        CriterioClassificacao criterio = new CriterioClassificacaoPontosCorridos();
        RegraResultado regra = new RegraResultadoPontosCorridos(
            new RegraPontuacaoPontosCorridos()
        );

        // Cria a base
        CompeticaoFutebol campeonato = new CampeonatoPontosCorridos(criterio, regra);

        // Decora com validação
        campeonato = new CampeonatoComValidacao(campeonato);

        // Decora com log
        CampeonatoComLog campeonatoComLog = new CampeonatoComLog(campeonato);

        // Decora com bônus
        CompeticaoFutebol campeonatoFinal = new CampeonatoComBonus(campeonatoComLog);

        Time m1 = new Time("Manchester City");
        Time m2 = new Time("Liverpool");
        Time m3 = new Time("Chelsea");

        campeonatoFinal.adicionarTime(m1);
        campeonatoFinal.adicionarTime(m2);
        campeonatoFinal.adicionarTime(m3);

        campeonatoFinal.iniciarCampeonato();

        campeonatoFinal.registrarPartida(m1, m2, 3, 1);
        campeonatoFinal.registrarPartida(m1, m3, 2, 0);
        campeonatoFinal.registrarPartida(m2, m3, 1, 0);

        campeonatoFinal.mostrarSituacao();

        // Exibe logs apenas se a instância for CampeonatoComLog
        if (campeonatoComLog != null) {
            campeonatoComLog.mostrarLogs();
        }
    }
}
