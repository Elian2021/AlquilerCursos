/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilercursos;

import static conexion.Conexion.obtener_connexio_BD;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
/**
 *
 * @author elian
 */
public class AlquilerCursos extends Application{
    
    Scene Escena, EscenaAlquiler;
    BorderPane contenedor;
    Pane PaneCursos, PaneAlquilados, PaneClientes, PaneAlquilarCurso;
    VBox vbox;
    
    
    
    TableView<Curso> tblCursos;
    TableView<CursoAlquilado> tblalquiler;
    TableView<Cliente> tblcliente;
    
    ObservableList<Curso> cursos;
    ObservableList<CursoAlquilado> alquilado;
    ObservableList<Cliente> clientes;
    ObservableList<Cliente> filtrarcliente;

    
    
    CursoAlquilado curalquilado;
    
    Label lblDni, lblCurso, lblFiltrar, lblHoras;
    TextField txtDni, txtCurso, txtFiltrar, txtHoras;

    public static void main(String[] args) {
    Application.launch(args);
  
       
    }
    
    @Override
    public void start (Stage Escenario) {
        
        // Declaracion del contenedor
        contenedor = new BorderPane ();
        
        //La tabla se fijara a la izquierda y estilos
        PaneCursos = new Pane();
        PaneCursos = tablaCursos();
        contenedor.setLeft(PaneCursos);
        tblCursos.setMaxWidth(310);
        tblCursos.setStyle("-fx-font: 14 Arial; -fx-base: #CBDDF8");
        
        //FiltrarClientes y la tabla se fijara a la derecha y estilos
        lblFiltrar = new Label("DNI");
        lblFiltrar.setStyle("-fx-font: 20 Arial");
        lblFiltrar.setTranslateX(200);
        lblFiltrar.setTranslateY(505);
        txtFiltrar = new TextField();
        txtFiltrar.setStyle("-fx-base: ECEBEB");
        txtFiltrar.setTranslateX(250);
        txtFiltrar.setTranslateY(500);
        txtFiltrar.setOnAction(e -> FiltrarDni());       
       
        //La tabla se fijara a la derecha y estilos
        
        PaneClientes = new Pane();
        PaneClientes = tablaClientes();
        contenedor.setRight(PaneClientes);
        tblcliente.setMinWidth(710);       
        tblcliente.setStyle("-fx-font: 14 Arial; -fx-base: #CBDDF8");
        //agregar campos Label
        PaneClientes.getChildren().addAll(lblFiltrar,txtFiltrar);
        
        //La tabla se fijara al final de la pagina
        PaneAlquilados = new Pane();
        PaneAlquilados = tablaAlquilados();
        contenedor.setBottom(PaneAlquilados);

// Formulario

        
        lblDni = new Label("DNI");
        lblDni.setStyle("-fx-font: 20 Arial");
        lblDni.setTranslateX(200);
        lblDni.setTranslateY(105);
        txtDni = new TextField();
        txtDni.setStyle("-fx-base: ECEBEB");
        txtDni.setTranslateX(250);
        txtDni.setTranslateY(100);
        txtDni.setOnAction(e -> SeleccionarDni());
        
        lblCurso = new Label("Curso");
        lblCurso.setStyle("-fx-font: 20 Arial");
        lblCurso.setTranslateX(200);
        lblCurso.setTranslateY(155);
        txtCurso = new TextField();
        txtCurso.setStyle("-fx-base: ECEBEB");
        txtCurso.setTranslateX(300);
        txtCurso.setTranslateY(150);
        txtCurso.setOnAction(e -> SeleccionarCarnet());
        
        lblHoras = new Label("Horas");
        lblHoras.setStyle("-fx-font: 20 Arial");
        lblHoras.setTranslateX(200);
        lblHoras.setTranslateY(200);
        txtHoras = new TextField();
        txtHoras.setStyle("-fx-base: ECEBEB");
        txtHoras.setTranslateX(280);
        txtHoras.setTranslateY(200);
        txtHoras.setOnAction(e -> InsertarHoras());
        
        
        PaneAlquilarCurso = new Pane();        
        contenedor.setCenter(PaneAlquilarCurso);
        PaneAlquilarCurso.getChildren().addAll(lblDni, lblCurso, lblHoras, txtDni, txtCurso, txtHoras);       
        
        
        Escena = new Scene(contenedor); 
        contenedor.setStyle("-fx-base: #767474");
        
        //tblalquiler.setStyle("-fx-font: 14 Arial; -fx-base: #CBDDF8");
           
      
        Escenario.setTitle("Alquiler Cursos");       
        Escenario.setMinWidth(1300);
        Escenario.setHeight(900);
        Escenario.setScene(Escena);       
        Escenario.show();
            

    };
    
    



    public Pane tablaCursos(){
        // Declaracion tabla para mostrar todos los cursos disponibles
        tblCursos= new TableView();
        //Declaracion de la columna
        TableColumn<Curso, Integer> colId = new TableColumn<>("Id");
        TableColumn<Curso, String> colNombre = new TableColumn<>("Nombre");
        TableColumn<Curso, String> colPrecio = new TableColumn<>("Precio Hora");
        TableColumn<Curso, Double> colCarnet = new TableColumn<>("Tipo Carnet");

        tblCursos.getColumns().addAll(colId, colNombre, colPrecio, colCarnet);
        
        colId.setCellValueFactory(new PropertyValueFactory("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory("una_hora"));
        colCarnet.setCellValueFactory(new PropertyValueFactory("carnet_federacion"));
        
        cursos = MostrarCursos();
        
        tblCursos.setItems(cursos);
        
        PaneCursos.getChildren().addAll(tblCursos);
  
        return PaneCursos;    
    }       
            
                
        
            

    public Pane tablaClientes(){
        
        tblcliente = new TableView();
        
        TableColumn <Cliente, String> colDni = new TableColumn<>("DNI");
        TableColumn <Cliente, String> colNombre = new TableColumn<>("Nombre");
        TableColumn <Cliente, String> colApellido = new TableColumn<>("Apellido");        
        TableColumn <Cliente, String> colCorreo = new TableColumn<>("Correo");        
        TableColumn <Cliente, Integer> colTelefono = new TableColumn<>("Telefono");        
        TableColumn <Cliente, String> colFamiliar = new TableColumn<>("Familiar");        
        TableColumn <Cliente, String> colCompeticion = new TableColumn<>("Competicion");
         
        tblcliente.getColumns().addAll(colDni, colNombre, colApellido, colCorreo, colTelefono, colFamiliar, colCompeticion);

        colDni.setCellValueFactory(new PropertyValueFactory("Dni"));
        colNombre.setCellValueFactory(new PropertyValueFactory("Nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory("Apellido"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("Correo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory("Telefono"));
        colFamiliar.setCellValueFactory(new PropertyValueFactory("Familiar"));
        colCompeticion.setCellValueFactory(new PropertyValueFactory("Federacion"));
        
        
        clientes = Cliente();
        tblcliente.setItems(clientes);
        
        PaneClientes.getChildren().addAll(tblcliente);
        
        return PaneClientes;
        

        
        
    }
    
        public Pane tablaAlquilados(){    
         // Declara tabla mostrar todos los cursos alquilados
         
         tblalquiler = new TableView();
         
         TableColumn <CursoAlquilado, String> colDni = new TableColumn<>("DNI");
         TableColumn <CursoAlquilado, Integer> colCurso = new TableColumn<>("Curso");
         TableColumn <CursoAlquilado, Integer> colDescuento = new TableColumn<>("Descuento");
         TableColumn <CursoAlquilado, Integer> colHoras = new TableColumn<>("Hora");
         TableColumn <CursoAlquilado, Double> colPrecios = new TableColumn<>("Precio");
         TableColumn <CursoAlquilado, Date> colFecha = new TableColumn<>("Fecha");
         
         tblalquiler.getColumns().addAll(colDni, colCurso, colDescuento, colHoras, colPrecios, colFecha);
         
         colDni.setCellValueFactory(new PropertyValueFactory("Dni"));
         colCurso.setCellValueFactory(new PropertyValueFactory("id"));
         colDescuento.setCellValueFactory(new PropertyValueFactory("descuento"));
         colHoras.setCellValueFactory(new PropertyValueFactory("hora"));
         colPrecios.setCellValueFactory(new PropertyValueFactory("precio"));
         colFecha.setCellValueFactory(new PropertyValueFactory("fecha"));
         
         alquilado = Alquilados();
         
         tblalquiler.setItems(alquilado);
         
         PaneAlquilados.getChildren().addAll(tblalquiler);
         
         return PaneAlquilados;   
    }
        
    
    public ObservableList<Curso>  MostrarCursos() {
        
        cursos = FXCollections.observableArrayList();
        
         
        
        try{
            Connection conexion = obtener_connexio_BD();
            String curs = "SELECT * FROM alquilercursos";

            Statement stmtpa = conexion.createStatement();
            ResultSet rs = stmtpa.executeQuery(curs);
            
            while(rs.next()){
                
               int id = rs.getInt("ID"); 
               String nombre = rs.getString("Nombre");
               double precio = rs.getDouble("una_hora");
               String carnet = rs.getString("carnet_federacion");
            
                
              Curso curso = new Curso(id, nombre, precio, carnet);
              
                cursos.add(curso);
                
            }  
            
        }catch(Exception ex){
            ex.printStackTrace();
            
        }        
        return cursos;
      
    }
    
       
    public ObservableList<CursoAlquilado>  Alquilados() {
        
        alquilado = FXCollections.observableArrayList();  

        try{
            Connection conexion = obtener_connexio_BD();
            String alquiler = "SELECT * FROM realiza";

            Statement stmtpa = conexion.createStatement();
            ResultSet rs = stmtpa.executeQuery(alquiler);
            
            while(rs.next()){   
                
               String dni = rs.getString("Dni");
               int curso = rs.getInt("id_curso");
               int descuento = rs.getInt("descuento");
               int horas = rs.getInt("horas");
               double precio = rs.getDouble("precio");
               String fecha = rs.getString("Fecha");
               
               
               CursoAlquilado curalquilado = new CursoAlquilado(dni, curso, descuento, horas, precio, fecha);
              
               alquilado.add(curalquilado);
                
            }           
            
        }catch(Exception ex){
            ex.printStackTrace();
            
        }        
        return alquilado; 
      
    }
    
    public ObservableList<Cliente> Cliente(){
        
        clientes = FXCollections.observableArrayList();
        filtrarcliente = FXCollections.observableArrayList();
        
        try{
            Connection conexion = obtener_connexio_BD();
            String cli = "SELECT * FROM cliente";

            Statement stmtpa = conexion.createStatement();
            ResultSet rs = stmtpa.executeQuery(cli);
            
            while(rs.next()){
                
                String Dni = rs.getString("DNI");
                String Nombre = rs.getString("Nombre");
                String Apellido = rs.getString("Apellido");
                String Correo = rs.getString("Correo");
                int Telefono = rs.getInt("Telefono");
                String Familiar = rs.getString("Numfamilia");
                String Federacion = rs.getString("numfederacion");
                
                
                Cliente cliente = new Cliente (Dni, Nombre, Apellido, Correo, Telefono, Familiar, Federacion);
                
                clientes.add(cliente);
            }
            
        }catch (Exception ex){
            ex.printStackTrace();
        
    }
        return clientes;
        
        
        
    }
    
    
   public ArrayList Alquilarcursos(){
       
       
       ArrayList alquilar = new ArrayList();
       
       try{
           
            Connection conexion = obtener_connexio_BD();
            String cli = "SELECT * FROM cliente";

            Statement stmtpa = conexion.createStatement();
            ResultSet rs = stmtpa.executeQuery(cli);
           
       }catch (Exception ex){
            ex.printStackTrace();
        
       }
       return null;
       
       
       
       
       
       
       
       
       
       
   }
//   public static String getDNI(){
//        Connection conexion = obtener_connexio_BD();
//        String vaDNI = "SELECT DNI FROM pedido WHERE ID='"+comanda+"'";
//
//        Statement stmtDNI = conexion.createStatement();
//        ResultSet cs = stmtDNI.executeQuery(vaDNI);
//        cs.next();
//        String dniBD = cs.getString("DNI");
//        
//        if(dniBD == null){
//            return  null;
//        }
//   
//        return dniBD;
//    }
   
   
   public void FiltrarDni(){
       
       String filtroDni = this.txtFiltrar.getText();       
       
       if(filtroDni.isEmpty()){
           this.tblcliente.setItems(clientes);
       }else {
           this.filtrarcliente.clear();
           
           for(Cliente c: this.clientes){
               if(c.getDni().toLowerCase().contains(filtroDni.toLowerCase())){
                   this.filtrarcliente.add(c);
               }
           }
           
           this.tblcliente.setItems(filtrarcliente);
       }
       
   
   
   }
   
   public Cliente SeleccionarDni(){
       
       Cliente client = this.tblcliente.getSelectionModel().getSelectedItem();
       
       if(client != null){
           this.txtDni.setText(client.getDni());
           
       }
       return client;
    
    }
   
    public Curso SeleccionarCarnet(){
       
       Curso curso = this.tblCursos.getSelectionModel().getSelectedItem();
       
       if(curso != null){
           this.txtCurso.setText(curso.getNombre());
           
       }
       return curso;
    
    }
   
    public void InsertarHoras(){
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //*****************VALIDACIONES ***************************************\\
    
    /* public static boolean validarDni(String dni) {
          denei=dni.
        // TODO code application logic here
        char[] lletraDni = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
                'H', 'L', 'C', 'K', 'E' };
        //dniInt=0;       
        String dniNum = "";
				
		// TODO Auto-generated method stub
        boolean dniCorrecte = true;
        // donem longitud al dni
        if (dni.length() != 9) {
            System.out.println("El dni ha de contenir 9 caracters");
            dniCorrecte=false;
            return false;
        } else {
        // mirem que l'�ltim d�git tingui una lletra

            char ult = dni.charAt(dni.length()-1);
            // Convertim minuscula a majuscula
            if (ult >= 'a' && ult <= 'z') {
                ult -= 32;
            }
            if (ult >= 'A' && ult <= 'Z') {
            // mirem que els primers 8 digits siguin numeros
                for (int i = 0; i < 8; i++) {
                    if (dni.charAt(i) < '0' || dni.charAt(i) > '9') {
                        System.out.println("Les 8 primeres posicions han de ser numeriques");
                        dniCorrecte = false;
                        return false;
                        //break;
                    }
                }
                char lletra = 0;

                if (dniCorrecte) {

                    dni = dni.substring(0, dni.length() - 1);
                    System.out.println("DNI: " + dni);

                    int dniInte = Integer.parseInt(dni);
                    int reste = dniInte % 23;
                    System.out.println("Reste: " + reste);
                    System.out.println("Ult: " + ult);
                    System.out.println("Lletra DNI es: " + lletraDni[reste]);
                    if (ult == lletraDni[reste]) {
                        System.out.println("El dni es correcte.");
                    } else {
                        //System.out.println("Aquest dni no existeix/no es correcte.");
                        dniCorrecte = false;
                        return false;
                    }
                }
            } else {
                System.out.println("La ultima posicio ha de ser una lletra.");
            }
        }
        return true;
     }*/
    
}
