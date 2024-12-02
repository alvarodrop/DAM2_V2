package Ejercicio2_2;

import java.util.ArrayList;

public class Almacen {
    private ArrayList<Integer> huecos;
    private Integer ultimoConsumido;

    public Almacen() {
        // Inicializamos el ArrayList con capacidad 2 y valores null
        this.huecos = new ArrayList<>();
        huecos.add(null); // El primer hueco está vacío (null)
        huecos.add(null); // El segundo hueco también está vacío (null)
        this.ultimoConsumido = 0;
    }

    // Metodo para producir un número y colocarlo en el almacén
    public synchronized void producir(Integer numero) throws InterruptedException {
        // Si el almacén está lleno (no hay huecos vacíos), el productor espera
        while (!huecos.contains(null)) {
            wait();  // Espera a que haya espacio en el almacén
        }

        // Coloca el número en el primer hueco vacío
        for (int i = 0; i < huecos.size(); i++) {
            if (huecos.get(i) == null) {
                huecos.set(i, numero);
                System.out.println("Productor produjo: " + numero);
                mostrarEstado();  // Muestra el estado del almacén
                break;
            }
        }

        // Notifica al consumidor que hay algo para consumir
        notify();
    }

    // Metodo para consumir un número del almacén
    public synchronized void consumir() throws InterruptedException {
        // Si el almacén está vacío, el consumidor espera
        // Si ambos huecos están vacíos, el consumidor espera
        while (huecos.get(0) == null && huecos.get(1) == null) {
            wait();  // Espera a que haya algo para consumir
        }

        // Consume el primer número disponible en el almacén
        Integer consumido = null;


            for (int i = 0; i < huecos.size(); i++) {
                if (huecos.get(i) != null && huecos.get(i) == ultimoConsumido + 1) {
                    consumido = huecos.get(i);
                    ultimoConsumido = consumido;
                    huecos.set(i, null);  // Vacía el hueco
                    System.out.println("Consumidor consumió: " + consumido);
                    mostrarEstado();  // Muestra el estado del almacén
                    break;
                }
            }
        // Notifica al productor que hay espacio para producir
        notify();
    }

    // Metodo para mostrar el estado del almacén
    public void mostrarEstado() {
        System.out.print("Estado del Almacén: ");
        for (Integer hueco : huecos) {
            if (hueco == null) {
                System.out.print("[Vacío] ");
            } else {
                System.out.print(hueco + " ");
            }
        }
        System.out.println();  // Imprime una nueva línea después del estado
    }
}
