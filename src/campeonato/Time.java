package campeonato;

public class Time {

    private String nome;
    private int pontos;
    private int golsPro;
    private int golsContra;
    private int golsFora;
    private int vitorias;
    private int empates;
    private int derrotas;

    private static int totalTimes = 0;

    public Time(String nome) {
        this.nome = nome;
        this.pontos = 0;
        this.golsPro = 0;
        this.golsContra = 0;
        this.golsFora = 0;
        this.vitorias = 0;
        this.empates = 0;
        this.derrotas = 0;
        totalTimes++;
    }

    public void adicionarPontos(int pontos) {
        this.pontos += pontos;
    }

    public void registrarPartida(int golsFeitos, int golsSofridos, boolean mandante) {
        this.golsPro += golsFeitos;
        this.golsContra += golsSofridos;
        if (!mandante) {
            this.golsFora += golsFeitos;
        }
    }

    public void registrarVitoria() {
        this.vitorias++;
    }

    public void registrarEmpate() {
        this.empates++;
    }

    public void registrarDerrota() {
        this.derrotas++;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    
    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getPontos() {
        return pontos;
    }

    public int getGolsPro() {
        return golsPro;
    }

    public int getGolsContra() {
        return golsContra;
    }

    public int getSaldoGols() {
        return golsPro - golsContra;
    }

    public int getGolsFora() {
        return golsFora;
    }

    public int getVitorias() {
        return vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public static int getTotalTimes() {
        return totalTimes;
    }
}