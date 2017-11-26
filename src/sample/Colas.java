package sample;

import javafx.scene.control.Alert;
/**
 * Created by ZeroX on 25/11/2017.
 */
public class Colas {
    public Nodo frente; //el inicio de la cola
    private int tamaño;//Contador
    // public Nodo temp;

    //constructor simple
    public Colas() {
        this.frente = null;
        this.tamaño=0;
    }
    /*Mètodo para insertar siguiente elemento(nodo),
    * el elemento debe colocarse detràs del ùltimonodo*/
    public void insertar(int valor){
        Nodo nuevo = new Nodo(valor);
        if (frente == null){
            frente=nuevo;
        }else {
            Nodo temp = frente;
            while (temp.getProximo()!=null){
                temp= temp.getProximo();
            }
            temp.setProximo(nuevo);
        }
        tamaño++;
    }
    //Mètodo que almacena la longitud (tamaño) de la cola.
    public int Tamaño(){
        return tamaño;
    }
    //Mètodo para mostrar los elementos de la cola
    public void mostrar(){
        if (frente!= null) {
            Nodo temp= frente ;
            System.out.println("Los valores de la cola son: ");
            while (temp != null){
                System.out.println(temp.getValor());
                temp = temp.getProximo();
            }
        }else {
            System.out.println("La cola está vacia");
        }
    }
    //Mètodo para extrer el elemento del frente
    public void extraer() {
        if (tamaño == 0) {
            System.out.println("La cola esta vacia");
        } else {
            int valorExtraer = frente.getValor(); //variable temporal
            frente = frente.getProximo(); //cambiar el frente por el siguiente
            //Deolver el valor extraìdo de la cola
            System.out.println("Valor extraído: " + valorExtraer);
            tamaño--;
            mostrar();
            System.out.println("Tamaño: "+Tamaño());
        }
    }
    //Mètodo pra extraer todos los elementos de la cola (Vaciar cola)
    public void VaciarCola() {
        if (tamaño == 0) {
            System.out.println("La cola esta vacia");
        } else {
            //Mientras el frente sea distinto de null sese iran eliminando los nodos
            while (frente != null) {
                int valorExtraer = frente.getValor(); //variable temporal
                frente = frente.getProximo(); //cambiar el frente por el siguiente
                //Deolver el valor extraìdo de la cola
                System.out.println("Valor extraído: " + valorExtraer);
                tamaño--;
                mostrar();
                System.out.println("Tamaño: " + Tamaño());
            }
        }
    }
    //Mètodo para buscar un valor dentro de la cola
    public String BuscarValor(int val){
        Nodo tem=frente;
        int i=0;
        //Excepciòn
        try {
            //Si el valor a buscar se encuentra dento de la cola se ejecutara
            //el siguiente bloque de còdigo con su respectivo mensaje
            while(tem.getValor()!=val){
                tem=tem.getProximo();
                i++;
            }
            return "Valor encontrado en la posiciòn: "+(i+1);
        }catch (Exception e){
            //En caso contrario se mostrara el siguiente mensaje
            return  "Valor no encontrado";
        }
    }
    //Mètodo para generar mensajes (Alert).
    public void GenerarAlert(String Titulo, String encabezado,String Contenido){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(Contenido);
        alert.showAndWait();
    }
}
