public class Pokemon {
    private String nombre;
    private String tipo;
    private int ataque;
    private int vida;
    private HabilidadEspecial habilidad;
    private boolean usado;
    private int bonusAtaque;

    public Pokemon(String nombre, String tipo, int ataque, int vida, HabilidadEspecial habilidad) {
        this.nombre = nombre;
        this.tipo = tipo.toLowerCase();
        this.ataque = ataque;
        this.vida = vida;
        this.habilidad = habilidad;
        this.usado = false;
        this.bonusAtaque = 0;
    }

    public int calcularAtaque(Pokemon rival) {
        int ataqueTotal = ataque + bonusAtaque;
        ataqueTotal += calcularEfectoTipo(rival);
        return ataqueTotal;
    }

    /**
     * Aplica la habilidad y devuelve descripción de lo sucedido.
     */
    public String aplicarHabilidad(Pokemon rival) {
        int efecto = habilidad.activarHabilidad();
        String nombreHabilidad = habilidad.getNombre();
        if (efecto != 0) {
            String tipoEfecto = habilidad.getEfecto();
            if (tipoEfecto.equals("ataque")) {
                bonusAtaque += efecto;
                return nombreHabilidad + " (bonus de ataque +" + efecto + ")";
            }
            if (tipoEfecto.equals("defensa")) {
                vida += efecto;
                return nombreHabilidad + " (recupera vida +" + efecto + ")";
            }
            if (tipoEfecto.equals("daño")) {
                rival.recibirDaño(efecto);
                return nombreHabilidad + " (daño extra " + efecto + " al rival)";
            }
        }
        return nombreHabilidad + " falló";
    }

    private int calcularEfectoTipo(Pokemon rival) {
        String tipoRival = rival.tipo;

        if (tipo.equals("fuego") && tipoRival.equals("planta")) return 20;
        if (tipo.equals("planta") && tipoRival.equals("agua")) return 20;
        if (tipo.equals("agua") && tipoRival.equals("fuego")) return 20;
        if (tipo.equals("eléctrico") && tipoRival.equals("agua")) return 20;

        if (tipo.equals("fuego") && tipoRival.equals("agua")) return -10;
        if (tipo.equals("agua") && tipoRival.equals("planta")) return -10;
        if (tipo.equals("planta") && tipoRival.equals("fuego")) return -10;
        if (tipo.equals("eléctrico") && tipoRival.equals("planta")) return -10;

        return 0;
    }

    public void recibirDaño(int cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public int getVida() { return vida; }
    public boolean isUsado() { return usado; }
    public void setUsado(boolean usado) { this.usado = usado; }
}
