package sample;

/**
 * Created by edd on 24/11/2017.
 */
public class Nodo {
    private int valor;
    //Puntero hacia el siguiente nodo
    private Nodo proximo;

    //Constructor simple, inicia sin valor.
    public Nodo() {
        this.valor = 0;
        this.proximo=null;

    }
    //Constructor
    public Nodo (int valor){
        this.valor=valor;
        this.proximo=null;
    }
    //Mètodo que nos permite establecer un valor al nodo
    public void setValor(int valor){
        this.valor = valor;
    }
    //Mètodo que nos permite establecer el proximo nodo de
    //cola en caso de que exista un proximo
    public void setProximo(Nodo siguiente)  {
        this.proximo = siguiente;
    }
    //Mètodo que nos permite obtener o recuperar el
    //valor almacenado en un nodo
    public int getValor()
    {
        return this.valor;
    }
    //Mètodo que nos permite obtener el siguiente nodo a enlazar en la cola
    public Nodo getProximo()
    {
        return this.proximo;
    }

}

