package campeonato;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CampeonatoComLog extends CampeonatoDecorador {

    private ArrayList<String> logs;
    private DateTimeFormatter formatter;

    public CampeonatoComLog(CompeticaoFutebol campeonatoDecorante) {
        super(campeonatoDecorante);
        this.logs = new ArrayList<>();
        this.formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    }

    private void registrarLog(String mensagem) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = "[" + timestamp + "] " + mensagem;
        logs.add(logEntry);
        System.out.println(logEntry);
    }

    @Override
    public void adicionarTime(Time t) {
        registrarLog("TIME ADICIONADO: " + t.getNome());
        super.adicionarTime(t);
    }

    @Override
    public void iniciarCampeonato() {
        registrarLog("CAMPEONATO INICIADO");
        super.iniciarCampeonato();
    }

    @Override
    public Time registrarPartida(Time casa, Time visitante, int golsCasa, int golsVisitante) {
        registrarLog("PARTIDA REGISTRADA: " + casa.getNome() + " (" + golsCasa + ") vs (" 
                    + golsVisitante + ") " + visitante.getNome());
        return super.registrarPartida(casa, visitante, golsCasa, golsVisitante);
    }

    @Override
    public void mostrarSituacao() {
        registrarLog("SITUAÇÃO DO CAMPEONATO CONSULTADA");
        System.out.println();
        super.mostrarSituacao();
    }

    public void mostrarLogs() {
        System.out.println("\n=== HISTÓRICO DE LOGS ===");
        for (String log : logs) {
            System.out.println(log);
        }
    }
}
