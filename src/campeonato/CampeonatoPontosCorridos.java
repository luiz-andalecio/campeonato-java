package campeonato;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class CampeonatoPontosCorridos extends Campeonato implements ICompeticao {

    private CriterioClassificacao criterioClassificacao;
    private RegraResultado regraResultado;

    public CampeonatoPontosCorridos() {
        super();
        this.criterioClassificacao = new CriterioClassificacaoPontosCorridos();
        this.regraResultado = new RegraResultadoPontosCorridos(new RegraPontuacaoPontosCorridos());
    }
    
    public CampeonatoPontosCorridos(CriterioClassificacao criterioClassificacao,
                                     RegraResultado regraResultado) {
        super();
        this.criterioClassificacao = criterioClassificacao;
        this.regraResultado = regraResultado;
    }

    public void setCriterioClassificacao(CriterioClassificacao criterioClassificacao) {
        this.criterioClassificacao = criterioClassificacao;
    }

    public void setRegraResultado(RegraResultado regraResultado) {
        this.regraResultado = Objects.requireNonNull(regraResultado);
    }

    @Override
    public void iniciarCampeonato() {
        System.out.println("Campeonato de pontos corridos iniciado!");
    }

    @Override
    public void mostrarClassificacao() {
        ArrayList<Time> ordenado = new ArrayList<>(times);
        if (criterioClassificacao != null) {
            Collections.sort(ordenado, criterioClassificacao);
        }

        for (Time t : ordenado) {
            System.out.println(t.getNome()
                    + " - " + t.getPontos() + " pontos"
                    + " | V: " + t.getVitorias()
                    + " | E: " + t.getEmpates()
                    + " | D: " + t.getDerrotas()
                    + " | SG: " + t.getSaldoGols()
                    + " | GP: " + t.getGolsPro()
                    + " | GC: " + t.getGolsContra());
        }
    }

    public void mostrarClassificacao(String titulo) {
        System.out.println(titulo);
        mostrarClassificacao();
    }

    @Override
    public Time registrarPartida(Time casa, Time visitante, int golsCasa, int golsVisitante) {
        Objects.requireNonNull(casa);
        Objects.requireNonNull(visitante);
        if (regraResultado == null) {
            throw new IllegalStateException("Regra de resultado nao configurada");
        }

        regraResultado.aplicarResultado(casa, visitante, golsCasa, golsVisitante);

        if (golsCasa > golsVisitante) {
            return casa;
        }
        if (golsVisitante > golsCasa) {
            return visitante;
        }
        return null;
    }

    @Override
    public void mostrarSituacao() {
        mostrarClassificacao("Tabela do Campeonato");
    }
}