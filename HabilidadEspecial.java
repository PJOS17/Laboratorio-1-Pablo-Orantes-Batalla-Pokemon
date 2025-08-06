public class HabilidadEspecial {
    private String nombre;
    private String efecto; // "ataque", "defensa", "daño"
    private int probabilidad; // 0-100
    private int valor;

    public HabilidadEspecial(String nombre, String efecto, int probabilidad, int valor) {
        this.nombre = nombre;
        this.efecto = efecto.toLowerCase();
        this.probabilidad = probabilidad;
        this.valor = valor;
    }

    public int activarHabilidad() {
        int random = (int)(Math.random() * 100);
        if (random < probabilidad) return valor;
        return 0;
    }

    public String getNombre() { return nombre; }
    public String getEfecto() { return efecto; }
}






