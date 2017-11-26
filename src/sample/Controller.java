package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class Controller implements Initializable{
    //Declaraciòn de contenedores y controles
    //de nuestra interfaz.
    @FXML
    ComboBox Menu;
    @FXML
    Button BtnEjecutar;
    @FXML
    javafx.scene.layout.HBox HBox, ConTamaño;
    @FXML
    FlowPane Contenedor;
    @FXML
    Image img;
    //Creaciòn de un objeto de la clase cola
    Colas colas = new Colas();
    int TamC=0;

    /* -Mètodo para crear el Texfield donde se insertaran los valores dados por el usuario*/
    public void MostrarTexfieldInsert(){
        TextField texto1 = new TextField();
        texto1.setPromptText("Ingrese valor");
        HBox.getChildren().add(texto1);
        //Evento click para insertar los valores dentro del FlowPane
         texto1.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(4));//No acepta letras en el textfield
        texto1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (texto1.getText().equals("")){
                        colas.GenerarAlert("Validar", "La caja de texto se encuentra vacia!", "Favor de ingresar un valor");
                    }else{
                        int num = Integer.parseInt(texto1.getText());
                        texto1.setText("");
                        colas.insertar(num);
                        colas.mostrar();
                        //Invocaciòn del mètodo que llena el FlowPane con imagenes.
                        ColaImg();
                    }
                }catch (Exception e){

                }
            }
        });
    }
    //Mètodo que ingresa una imagen en el FlowPane cada ves que se inserte un valor
    public void ColaImg() {
        //Permite actualizar la cola graficamente.
        Contenedor.getChildren().clear();
        for (Nodo temp = colas.frente; temp != null; temp = temp.getProximo()) {
            LlenarColaM();
        }
    }
    //Mètodo de insercciòn de imagenes al contenedor Flowpane
    public void LlenarColaM(){
        HBox colaImgv = new HBox();
        File archivo = new File("src/sample/img/duda1.png");
        Image image = null;
        try {
            image = new Image(archivo.toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ImageView img = new ImageView();
        img.setImage(image);

        colaImgv.getChildren().add(img);
        Contenedor.getChildren().add(colaImgv);
    }
    //Mètodo que ingresa  el valor insertado por el usuario al FlowPane
    public void LLenarColaNumerica(){
        Contenedor.getChildren().clear();
        for (Nodo temp = colas.frente; temp != null; temp = temp.getProximo()) {
            Label ND;
            ND = new Label();
            ND.setStyle("-fx-background-color: deepskyblue; -fx-font-size:30px;");
            ND.setText(temp.getValor() + "");
            HBox colaImgv = new HBox();

            File archivo = new File("src/sample/img/flecha_atras.gif");
            Image image = null;
            try {
                image = new Image(archivo.toURI().toURL().toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            ImageView img = new ImageView();
            img.setImage(image);
            HBox.setAlignment(Pos.CENTER);
            //Si el valor ingresado es el primero en la cola solo
            //se muestra el valor
            if (temp==colas.frente){
                colaImgv.getChildren().add(ND);
            }else{
                //En otro caso se mostrara el valor junto con
                //una pequeña flecha.
                colaImgv.getChildren().add(img);
                colaImgv.getChildren().add(ND);
            }
            //Acciòn para llenar el FlowPane
            Contenedor.getChildren().add(colaImgv);
        }
    }
    //Mètodo que muestra el tamaño(longitud) de la cola
    //En un Alert.
    public void TamañoC(){
        //A la variable Tamc se le asigna el valor obtenido
        //del metodo Tamaño() de la clase Colas.
        TamC=colas.Tamaño();
        colas.GenerarAlert("Tamaño", "Tamaño de la cola: ",String.valueOf(TamC));
        ColaImg();
    }
    //Mètodo que muestra la posiciòn del valor a buscar dentro de la cola
    public void Buscar(){
        ColaImg();
        TextField texto = new TextField();
        texto.setPromptText("Ingrese valor a buscar");
        HBox.getChildren().add(texto);
        texto.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(4));
        texto.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (texto.getText().equals("")) {
                        colas.GenerarAlert("Tamaño","La cola esta vacia! ","No puede realizar la acciòn seleccionada");
                    } else {
                        int num = Integer.parseInt(texto.getText());
                        colas.GenerarAlert("Busqueda","Resultado de la busqueda : ",colas.BuscarValor(num));
                        texto.clear();
                        LLenarColaNumerica();
                    }
                } catch (Exception e) {

                }
            }
        });
    }
    //Mètodo para eliminar el primer elemento de la cola.
    public void Eliminar(){
        ColaImg();
        Contenedor.getChildren().clear();
        //Se invoca el mètodo extraer de la clase Colas
        colas.extraer();
        LLenarColaNumerica();
        TamañoC();
    }
    //Mètodo para Vaciar la cola
    public void VaciarCola() {
        ColaImg();
        Contenedor.getChildren().clear();
        //Se invoca el mètodo VaciarCola de la clase Colas
        //el cual eliminara todos los elementos de la cola
        colas.VaciarCola();
        TamañoC();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Llenado del ComboBox
        ObservableList<String> options =
                observableArrayList(
                        "Insertar",
                        "Tamaño",
                        "Mostrar",
                        "Buscar",
                        "Extraer",
                        "Vaciar"

                );
        this.Menu.setItems(options);
        //Evento click para el botòn ejecutar
        this.BtnEjecutar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //Creaciòn y asignaciòn de una variable del contenido
                //del ComboBox Menu.
                String selected = Menu.getValue().toString();
                //Condicional de selecciòn
                switch (selected) {
                    case "Insertar":
                        MostrarTexfieldInsert();
                        break;
                    case "Mostrar":
                        if (colas.Tamaño()<=0){
                            colas.GenerarAlert("Tamaño","La cola esta vacia! ","No puede realizar la acciòn seleccionada");
                        }else {
                            LLenarColaNumerica();
                            HBox.getChildren().clear();
                        }
                        break;
                    case "Buscar":
                        if (colas.Tamaño()<=0){
                            colas.GenerarAlert("Tamaño","La cola esta vacia! ","No puede realizar la acciòn seleccionada");
                        }else {
                            HBox.getChildren().clear();
                            Buscar();
                        }
                        break;
                    case "Extraer":
                        if (colas.Tamaño() == 0) {
                            colas.GenerarAlert("Tamaño","La cola esta vacia! ","No puede realizar la acciòn seleccionada");
                        } else {
                            HBox.getChildren().clear();
                            Eliminar();
                            TamC--;
                        }
                        break;
                    case "Tamaño":
                        if (colas.Tamaño() == 0) {
                            colas.GenerarAlert("Tamaño","La cola esta vacia! ","No puede realizar la acciòn seleccionada");
                        } else {
                            HBox.getChildren().clear();
                            TamañoC();
                        }
                        break;
                    case "Vaciar":
                        if (colas.Tamaño() == 0) {
                            colas.GenerarAlert("Tamaño","La cola esta vacia! ","No puede realizar la acciòn seleccionada");
                        } else {
                            HBox.getChildren().clear();
                            VaciarCola();
                        }
                        break;
                }

                Menu.setValue("Opciones");
            }

        });
    }
    public EventHandler numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[0-9.]")) {
                    if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                        e.consume();
                    } else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }

        };

    }
}
