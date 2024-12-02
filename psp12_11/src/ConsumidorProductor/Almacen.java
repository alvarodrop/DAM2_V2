package ConsumidorProductor;

import java.util.ArrayList;

public class Almacen {
    private ArrayList<Integer> buffer;
    private int capacidad;
    private int contador;  // Controla la cantidad de elementos en el buffer
    private int elementoConsumido;
    private ArrayList<Integer> listaProducidos;


    // Constructor que inicializa el buffer y su capacidad
    public Almacen(int capacidad) {
        this.capacidad = capacidad;
        this.buffer = new ArrayList<>(capacidad);  // Inicializa el ArrayList con la capacidad especificada
        this.listaProducidos = new ArrayList<>();
        this.elementoConsumido = 0;
        this.contador = 0;

        // Llena el buffer con ceros
        for (int i = 0; i < capacidad; i++) {
            buffer.add(0);  // Añade ceros al buffer hasta alcanzar la capacidad
        }
    }




    public synchronized void producir(int numero,int idProductor) throws InterruptedException {
        // Si el almacen está lleno, no produce
        // La variable que me lleva el recuento de los huecos es contador
        while (contador == capacidad){
            wait();
        }


        // Buscar una posición vacía para colocar el número
        for (int i = 0; i < capacidad; i++) {
            // Calcula el índice en función del número, reiniciando cuando llega a la capacidad
            int indice = (numero - 1) % capacidad;  // Usamos el módulo para asegurarnos de que el índice siempre esté en el rango válido

            // Verifica que la posición esté vacía y no se haya producido ya el número
            if (buffer.get(indice) == 0 && !listaProducidos.contains(numero)) {
                buffer.set(indice, numero);  // Coloca el número en la posición correspondiente
                contador++;  // Incrementa el contador de elementos en el buffer
                listaProducidos.add(numero);  // Agrega el número a la lista de producidos

                System.out.println("Productor " + idProductor + " produjo: " + numero);
                estadoBuffer();  // Muestra el estado del buffer
                break;
            }
        }

        notify();  // Notifica a los consumidores que hay algo para consumir
    }


    public synchronized void consumir(int idConsumidor) throws InterruptedException {
          // Almacena el valor consumido

        // Si el almacen está vacío, no consume
        while (contador == 0){
            wait();  // El consumidor espera hasta que haya algo para consumir
        }

        //Usas un índice (consumidorIndice) para consumir el siguiente elemento en el buffer.
        for (int i = 0; i < buffer.size(); i++) {
            if (buffer.get(i) != 0 && buffer.get(i) == elementoConsumido + 1) {  // Busca el primer elemento no vacío
                elementoConsumido = buffer.get(i);  // Guarda el elemento a consumir
                buffer.set(i, 0);  // Vuelve a poner el lugar como vacío
                contador--;  // Decrementa el contador de elementos en el buffer
                System.out.println("Consumidor "+idConsumidor+" consumió: " + elementoConsumido);
                estadoBuffer();  // Muestra el estado del buffer
                break;
            }
        }

        notify();  // Notifica a los productores que hay espacio para producir
    }

    // Metodo para mostrar el estado del buffer
    public void estadoBuffer() {
        System.out.print("[");
        for (Integer posicion : buffer) {
            System.out.print(posicion + " ");
        }
        System.out.println("]");
        System.out.println();
    }
}
