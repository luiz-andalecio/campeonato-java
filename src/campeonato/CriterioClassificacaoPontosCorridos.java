package campeonato;

public class CriterioClassificacaoPontosCorridos implements CriterioClassificacao {

    @Override
    public int compare(Time a, Time b) {
        int porPontos = Integer.compare(b.getPontos(), a.getPontos());
        if (porPontos != 0) {
            return porPontos;
        }

        int porVitorias = Integer.compare(b.getVitorias(), a.getVitorias());
        if (porVitorias != 0) {
            return porVitorias;
        }

        int porSaldo = Integer.compare(b.getSaldoGols(), a.getSaldoGols());
        if (porSaldo != 0) {
            return porSaldo;
        }

        int porGolsPro = Integer.compare(b.getGolsPro(), a.getGolsPro());
        if (porGolsPro != 0) {
            return porGolsPro;
        }

        return a.getNome().compareToIgnoreCase(b.getNome());
    }
}
