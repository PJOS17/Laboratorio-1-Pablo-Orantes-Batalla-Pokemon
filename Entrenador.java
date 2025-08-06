import java.util.ArrayList;

public class Entrenador {
    private String nombre;
    private ArrayList<Pokemon> listaPokemons;

    public Entrenador(String nombre) {
        this.nombre = nombre;
        this.listaPokemons = new ArrayList<>();
    }

    public void agregarPokemon(Pokemon p) {
        if (listaPokemons.size() < 4) listaPokemons.add(p);
    }

    public Pokemon elegirPokemon(int indice) {
        Pokemon p = listaPokemons.get(indice);
        p.setUsado(true);
        return p;
    }

    public ArrayList<Pokemon> getListaPokemons() { return listaPokemons; }
    public String getNombre() { return nombre; }
}






