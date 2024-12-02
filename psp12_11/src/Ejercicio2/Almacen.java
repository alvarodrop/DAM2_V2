package Ejercicio2;

public class Almacen {
    private int[] buffer = new int[2]; // Almacén de tamaño 2
    private int contador = 0; // Cuenta cuántos elementos hay en el almacén
    private int indice = 0; // Índice para agregar el siguiente número
    private boolean lleno = false; // Indica si el almacén está lleno
    private int siguienteNumero = 1; // Número que el productor va a generar

    // Metodo sincronizado para que el productor coloque un número en el almacén
    public synchronized void producir() throws InterruptedException {
        // Si el almacén está lleno, espera
        while (contador == 2) {
            wait();
        }

        // El productor coloca un número en el almacén
        buffer[indice] = siguienteNumero; // Genera el número de acuerdo a la secuencia
        System.out.println("Productor produjo: " + siguienteNumero);

        // Incrementa el número que se va a producir, y resetea si llega a 10
        siguienteNumero = siguienteNumero % 10 + 1;

        indice = (indice + 1) % 2; // Actualiza el índice, asegurando que se use el buffer circular
        contador++; // Aumenta el contador

        // Si el almacén no está lleno, notifica al consumidor que puede consumir
        notify();
    }

    // Metodo sincronizado para que el consumidor consuma un número del almacén
    public synchronized void consumir() throws InterruptedException {
        // Si el almacén está vacío, espera
        while (contador == 0) {
            wait();
        }

        // El consumidor consume el número en el almacén
        int numeroConsumido = buffer[(indice - contador + 2) % 2]; // El índice circular para el consumo
        System.out.println("Consumidor consumió: " + numeroConsumido);
        contador--; // Decrementa el contador

        // Si el almacén no está vacío, notifica al productor que puede producir
        notify();
    }
}
