package ConsumidorProductor2;


public class Productor  implements Runnable{
    private Almacen almacen;
    public Productor(Almacen almacen){
        this.almacen=almacen;
    }
    @Override
    public void run(){
        Integer[] numeros={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
        for(Integer numero : numeros){
            try{
                almacen.producir(numero);
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}