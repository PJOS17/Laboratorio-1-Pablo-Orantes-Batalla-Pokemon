import java.util.Scanner;

public class Vista {
    private Scanner sc = new Scanner(System.in);

    public String pedirTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.next();
    }

    public int pedirEntero(String mensaje) {
        System.out.print(mensaje);
        while (!sc.hasNextInt()) {
            sc.next(); // desechar
            System.out.print("Entrada inválida. " + mensaje);
        }
        return sc.nextInt();
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static void main(String[] args) {
        Vista vista = new Vista();
        Controlador controlador = new Controlador(vista);
        controlador.iniciarJuego();
    }
}






