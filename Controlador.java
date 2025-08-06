import java.util.ArrayList;

public class Controlador {
    private Vista vista;
    private Batalla batalla;
    private ArrayList<Pokemon> pokemonsDisponibles;

    public Controlador(Vista vista) {
        this.vista = vista;
        this.batalla = new Batalla();
        this.pokemonsDisponibles = crearPokemons();
    }

    public void iniciarJuego() {
        vista.mostrarMensaje("=== TORNEO POKEMON ===");

        String nombre1 = vista.pedirTexto("Nombre del Entrenador 1: ");
        Entrenador e1 = new Entrenador(nombre1);
        String nombre2 = vista.pedirTexto("Nombre del Entrenador 2: ");
        Entrenador e2 = new Entrenador(nombre2);

        vista.mostrarMensaje("\n--- Selección de Pokémon (alternada) ---");
        seleccionarPokemonsAlternado(e1, e2);

        int puntos1 = 0, puntos2 = 0;

        for (int ronda = 1; ronda <= 4; ronda++) {
            vista.mostrarMensaje("\n=== RONDA " + ronda + " ===");
            Pokemon p1 = elegirPokemonDisponible(e1);
            Pokemon p2 = elegirPokemonDisponible(e2);

            String ganador = batalla.combatir(p1, p2, vista, e1.getNombre(), e2.getNombre());
            vista.mostrarMensaje("Ganador de la ronda: " + ganador);

            if (ganador.equals(e1.getNombre())) puntos1++;
            if (ganador.equals(e2.getNombre())) puntos2++;
        }

        if (puntos1 > puntos2) vista.mostrarMensaje("\nGanador del torneo: " + e1.getNombre());
        else if (puntos2 > puntos1) vista.mostrarMensaje("\nGanador del torneo: " + e2.getNombre());
        else vista.mostrarMensaje("\nEl torneo terminó en empate.");
    }

    private ArrayList<Pokemon> crearPokemons() {
        ArrayList<Pokemon> lista = new ArrayList<>();
        lista.add(new Pokemon("Charmander", "fuego", 50, 50, new HabilidadEspecial("Llama Final", "ataque", 30, 15)));
        lista.add(new Pokemon("Squirtle", "agua", 45, 60, new HabilidadEspecial("Escudo Natural", "defensa", 40, 20)));
        lista.add(new Pokemon("Bulbasaur", "planta", 48, 55, new HabilidadEspecial("Latigazo", "ataque", 25, 15)));
        lista.add(new Pokemon("Pikachu", "eléctrico", 55, 45, new HabilidadEspecial("Impacto Relámpago", "daño", 35, 20)));
        lista.add(new Pokemon("Vulpix", "fuego", 52, 48, new HabilidadEspecial("Fuego Místico", "ataque", 20, 15)));
        lista.add(new Pokemon("Poliwag", "agua", 43, 58, new HabilidadEspecial("Burbuja Defensiva", "defensa", 30, 20)));
        lista.add(new Pokemon("Oddish", "planta", 50, 53, new HabilidadEspecial("Esporas", "daño", 25, 15)));
        lista.add(new Pokemon("Electabuzz", "eléctrico", 60, 50, new HabilidadEspecial("Trueno Potente", "ataque", 40, 15)));
        return lista;
    }

    private void seleccionarPokemonsAlternado(Entrenador e1, Entrenador e2) {
        ArrayList<Integer> usadosGlobal = new ArrayList<>();
        for (int pick = 1; pick <= 4; pick++) {
            // Entrenador 1 elige
            mostrarDisponibles(usadosGlobal);
            int indice1 = vista.pedirEntero("Elige el Pokémon " + pick + " para " + e1.getNombre() + ": ");
            while (indice1 < 0 || indice1 >= pokemonsDisponibles.size() || usadosGlobal.contains(indice1)) {
                indice1 = vista.pedirEntero("Selección inválida o ya usada. Elige otro Pokémon para " + e1.getNombre() + ": ");
            }
            e1.agregarPokemon(pokemonsDisponibles.get(indice1));
            usadosGlobal.add(indice1);

            // Entrenador 2 elige
            mostrarDisponibles(usadosGlobal);
            int indice2 = vista.pedirEntero("Elige el Pokémon " + pick + " para " + e2.getNombre() + ": ");
            while (indice2 < 0 || indice2 >= pokemonsDisponibles.size() || usadosGlobal.contains(indice2)) {
                indice2 = vista.pedirEntero("Selección inválida o ya usada. Elige otro Pokémon para " + e2.getNombre() + ": ");
            }
            e2.agregarPokemon(pokemonsDisponibles.get(indice2));
            usadosGlobal.add(indice2);
        }
    }

    private void mostrarDisponibles(ArrayList<Integer> usadosGlobal) {
        vista.mostrarMensaje("\nPokémon disponibles:");
        for (int j = 0; j < pokemonsDisponibles.size(); j++) {
            if (!usadosGlobal.contains(j)) {
                Pokemon p = pokemonsDisponibles.get(j);
                vista.mostrarMensaje(j + ". " + p.getNombre() + " (" + p.getTipo() + ")");
            }
        }
    }

    private Pokemon elegirPokemonDisponible(Entrenador entrenador) {
        vista.mostrarMensaje("\nPokémon disponibles de " + entrenador.getNombre() + ":");
        for (int i = 0; i < entrenador.getListaPokemons().size(); i++) {
            Pokemon p = entrenador.getListaPokemons().get(i);
            if (!p.isUsado()) {
                vista.mostrarMensaje(i + ". " + p.getNombre() + " (Vida: " + p.getVida() + ")");
            }
        }
        int indice = vista.pedirEntero("Elige el Pokémon para esta ronda: ");
        while (indice < 0 || indice >= entrenador.getListaPokemons().size() || entrenador.getListaPokemons().get(indice).isUsado()) {
            indice = vista.pedirEntero("Ese Pokémon ya fue usado o selección inválida, selecciona otro: ");
        }
        return entrenador.elegirPokemon(indice);
    }
}


