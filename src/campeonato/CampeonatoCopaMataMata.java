package campeonato;

import java.util.ArrayList;
import java.util.Objects;

public class CampeonatoCopaMataMata extends Campeonato {

    private final RegraDesempate regraDesempate;
    private final ArrayList<Time> vivos;
    private final ArrayList<Time> eliminados;
    private boolean iniciado;

    public CampeonatoCopaMataMata() {
        this(new RegraDesempatePenaltis());
    }

    public CampeonatoCopaMataMata(RegraDesempate regraDesempate) {
        super();
        this.regraDesempate = Objects.requireNonNull(regraDesempate);
        this.vivos = new ArrayList<>();
        this.eliminados = new ArrayList<>();
        this.iniciado = false;
    }
    
    @Override
    public void iniciarCampeonato() {
        iniciado = true;
        vivos.clear();
        vivos.addAll(times);
        eliminados.clear();
        System.out.println("Copa mata-mata iniciada!");
    }

    @Override
    public void adicionarTime(Time t) {
        super.adicionarTime(t);
        if (iniciado) {
            vivos.add(t);
        }
    }

    @Override
    public Time registrarPartida(Time casa, Time visitante, int golsCasa, int golsVisitante) {
        Objects.requireNonNull(casa);
        Objects.requireNonNull(visitante);
        if (casa == visitante) {
            throw new IllegalArgumentException("Times devem ser diferentes");
        }
        if (!vivos.contains(casa) || !vivos.contains(visitante)) {
            throw new IllegalStateException("Time nao esta mais na competicao");
        }

        casa.registrarPartida(golsCasa, golsVisitante, true);
        visitante.registrarPartida(golsVisitante, golsCasa, false);

        Time vencedor;
        Time perdedor;

        if (golsCasa > golsVisitante) {
            casa.registrarVitoria();
            visitante.registrarDerrota();
            vencedor = casa;
            perdedor = visitante;
        } else if (golsVisitante > golsCasa) {
            casa.registrarDerrota();
            visitante.registrarVitoria();
            vencedor = visitante;
            perdedor = casa;
        } else {
            casa.registrarEmpate();
            visitante.registrarEmpate();
            vencedor = regraDesempate.decidirVencedor(casa, visitante);
            perdedor = vencedor == casa ? visitante : casa;
        }

        eliminar(perdedor);
        return vencedor;
    }

    @Override
    public void mostrarSituacao() {
        if (vivos.size() == 1) {
            System.out.println("Campeao: " + vivos.get(0).getNome());
        } else {
            System.out.println("Times vivos: " + vivos.size());
        }

        for (Time t : vivos) {
            System.out.println(t.getNome());
        }

        if (!eliminados.isEmpty()) {
            System.out.println("Eliminados:");
            for (Time t : eliminados) {
                System.out.println(t.getNome());
            }
        }
    }

    private void eliminar(Time time) {
        vivos.remove(time);
        if (!eliminados.contains(time)) {
            eliminados.add(time);
        }
    }
}
