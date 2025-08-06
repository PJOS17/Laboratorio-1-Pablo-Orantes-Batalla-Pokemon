public class Batalla {
    public String combatir(Pokemon p1, Pokemon p2, Vista vista, String nombre1, String nombre2) {
        while (p1.estaVivo() && p2.estaVivo()) {
            // Turno del primer jugador
            int accion1 = vista.pedirEntero(nombre1 + ": 1=Atacar, 2=Usar Habilidad -> ");
            if (accion1 == 1) {
                int danio = p1.calcularAtaque(p2);
                p2.recibirDaño(danio);
                vista.mostrarMensaje(p1.getNombre() + " ataca y causa " + danio + " de daño. Vida de " + p2.getNombre() + ": " + p2.getVida());
            } else {
                String resultado = p1.aplicarHabilidad(p2);
                vista.mostrarMensaje(p1.getNombre() + " usa " + resultado + ". Vida de " + p1.getNombre() + ": " + p1.getVida() + ", Vida de " + p2.getNombre() + ": " + p2.getVida());
            }

            if (!p2.estaVivo()) break;

            // Turno del segundo jugador
            int accion2 = vista.pedirEntero(nombre2 + ": 1=Atacar, 2=Usar Habilidad -> ");
            if (accion2 == 1) {
                int danio = p2.calcularAtaque(p1);
                p1.recibirDaño(danio);
                vista.mostrarMensaje(p2.getNombre() + " ataca y causa " + danio + " de daño. Vida de " + p1.getNombre() + ": " + p1.getVida());
            } else {
                String resultado = p2.aplicarHabilidad(p1);
                vista.mostrarMensaje(p2.getNombre() + " usa " + resultado + ". Vida de " + p2.getNombre() + ": " + p2.getVida() + ", Vida de " + p1.getNombre() + ": " + p1.getVida());
            }
        }

        return p1.estaVivo() ? nombre1 : nombre2;
    }
}

