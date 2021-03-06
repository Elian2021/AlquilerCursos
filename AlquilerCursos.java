/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alquilercursos;

import static conexion.Conexion.obtener_connexio_BD;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;
/**
 *
 * @author elian
 */
public class AlquilerCursos extends Application{
             

    
    Scene Escena, EscenaAlquiler;
    BorderPane contenedor;
    Pane PaneCursos, PaneAlquilados, PaneClientes, PaneAlquilarCurso;
    VBox vbox;
    
    HashMap <CursoAlquilado, Integer> listaAlquilados = new HashMap <CursoAlquilado, Integer>();
    
    TableView<Curso> tblCursos;
    TableView<CursoAlquilado> tblalquiler;
    TableView<Cliente> tblcliente;
    
    ObservableList<Curso> cursos;
    ObservableList<CursoAlquilado> alquilado;
    ObservableList<Cliente> clientes;
    ObservableList<Cliente> filtrarcliente;

    
    
    CursoAlquilado curalquilado;
    
    Label lblDni, lblCurso, lblFiltrar, lblID;
    TextField txtDni, txtCurso, txtFiltrar, txtID;
    CheckBox unahora, treshoras, dia;
    Button btnAlquilar;
    Label lblMensaje;

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
        txtDni.setStyle("-fx-base: #ECEBEB");
        txtDni.setTranslateX(250);
        txtDni.setTranslateY(100);
        
        txtDni.setOnAction(e -> SeleccionarDni());       
        
        lblCurso = new Label("Curso");
        lblCurso.setStyle("-fx-base: #7AC4FE; -fx-font: 20 Arial");
        lblCurso.setTranslateX(200);
        lblCurso.setTranslateY(155);
        txtCurso = new TextField();
        txtCurso.setStyle("-fx-base: #ECEBEB");
        txtCurso.setTranslateX(300);
        txtCurso.setTranslateY(150);
        txtCurso.setOnAction(e -> SeleccionarCarnet());
        
        lblID = new Label("ID Curso");
        lblID.setStyle("-fx-base: #7AC4FE; -fx-font: 20 Arial");
        lblID.setTranslateX(200);
        lblID.setTranslateY(200);
        txtID = new TextField();
        txtID.setStyle("-fx-base: #ECEBEB");
        txtID.setTranslateX(300);
        txtID.setTranslateY(200);
        
        
        
        ///********************** CHECKBOX ****************//
        
        unahora = new CheckBox("1");
        unahora.setStyle("-fx-font: 20 Arial ");
        unahora.setTranslateX(250);
        unahora.setTranslateY(250);
        
        treshoras = new CheckBox("3");
        treshoras.setStyle("-fx-font: 20 Arial ");
        treshoras.setTranslateX(250);
        treshoras.setTranslateY(300);
        
        dia = new CheckBox("6");
        dia.setStyle("-fx-font: 20 Arial ");
        dia.setTranslateX(250);
        dia.setTranslateY(350);
        
        ///*******************BOTONEA*************///////
        
        btnAlquilar = new Button ("Alquilar");
        btnAlquilar.setStyle("-fx-font: 20 Arial; -fx-base: #CBDDF8");
        btnAlquilar.setMinWidth(100);
        btnAlquilar.setTranslateX(250);
        btnAlquilar.setTranslateY(400);
        btnAlquilar.setOnAction(e-> CursosAlqui());
        
        
        
        PaneAlquilarCurso = new Pane();        
        contenedor.setCenter(PaneAlquilarCurso);
        PaneAlquilarCurso.getChildren().addAll(lblDni, lblCurso, lblID, txtDni, txtCurso, txtID, unahora, treshoras, dia, btnAlquilar);       
        
        
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
            String alquiler = "SELECT * FROM realiza WHERE Fecha = CURDATE()";

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
    public Curso SeleccionarID(){
        Curso id = this.tblCursos.getSelectionModel().getSelectedItem();
        
        if(id != null){
            this.txtID.setText(String.valueOf(id.getId()));
        }
        return id;
    }
    
    
    //*****************VALIDACIONES ***************************************\\
    //Metodo validar DNI
   public boolean ValidarDni (String Dni){
      boolean nie = true;
       try{
           Connection conexion = obtener_connexio_BD();
            String cliente = "SELECT DNI FROM cliente WHERE DNI = '"+Dni+"'";

            Statement stmtpa = conexion.createStatement();
            ResultSet rs = stmtpa.executeQuery(cliente);
            
            while(rs.next()){
                 Dni = rs.getString("DNI");
            }
            if (Dni == null) {
                nie = false;    
            }   
       }catch(SQLException ex){
           ex.printStackTrace();
       }
        return nie;  
   }
   
   //Metodo validar nombre curso
      public boolean ValidarNombre (String nombre){
          boolean nom = true;
      
       try{
           Connection conexion = obtener_connexio_BD();
            String curso = "SELECT Nombre FROM curso WHERE Nombre = '"+nombre+"'";

            Statement stmtpa = conexion.createStatement();
            ResultSet rs = stmtpa.executeQuery(curso);
            
            while(rs.next()){         
            
                 nombre = rs.getString("Nombre");
            }    
                if (nombre == null) {
                    nom = false;                  
                }        
            
       }catch(SQLException ex){
           ex.printStackTrace();
       }
        return nom;        
   }
      
   //Metodo coger carnet familiar del cliente
      public String ValidarCarnetClienteFamiliar(String dni){
          
          String CarnetCliente = null;
          try{
              Connection conexion = obtener_connexio_BD();
              String carnet = "SELECT Numfamilia from cliente WHERE DNI = '"+dni+"'";
              
              Statement stmtpa = conexion.createStatement();
              ResultSet rs = stmtpa.executeQuery(carnet);
              
              while(rs.next()){
                  
               CarnetCliente = rs.getString("Numfamilia");
               
              }
              
          }catch(SQLException ex){
              ex.printStackTrace();              
          }      
        return CarnetCliente;  
     }
      
      //Metodo coger carnet competicion cliente
      public String ValidarCarnetClienteCompeticion(String dni){
          
          String CarnetCliente = null;
          try{
              Connection conexion = obtener_connexio_BD();
              String carnet = "SELECT Numfederacion from cliente WHERE DNI = '"+dni+"'";
              
              Statement stmtpa = conexion.createStatement();
              ResultSet rs = stmtpa.executeQuery(carnet);
              
              while(rs.next()){
                  
               CarnetCliente = rs.getString("Numfedereacion");
               
              }
              
          }catch(SQLException ex){
              ex.printStackTrace();              
          }      
        return CarnetCliente;  
     }
      
      //Metodo validar carnet familiar cliente
      public String ValidarCarnetCursoFamiliar(int id){
          
           String CarnetCliente = null;
          try{
              Connection conexion = obtener_connexio_BD();
              String carnet = "SELECT tipo_carnet from familiar WHERE id = '"+id+"'";
              
              Statement stmtpa = conexion.createStatement();
              ResultSet rs = stmtpa.executeQuery(carnet);
              
              while(rs.next()){
                    CarnetCliente = rs.getString("tipo_carnet");
              }
          }catch(SQLException ex){
              ex.printStackTrace();              
          }      
          
        return CarnetCliente;
          
      }
      
      // Metodo validar carnet competicion cliente
      public String ValidarCarnetCursoCompeticion(int id){
          
           String CarnetCliente = null;
          try{
              Connection conexion = obtener_connexio_BD();
              String carnet = "SELECT carnet_federacion from competicion WHERE id = '"+id+"'";
              
              Statement stmtpa = conexion.createStatement();
              ResultSet rs = stmtpa.executeQuery(carnet);
              
              while(rs.next()){
                    CarnetCliente = rs.getString("tipo_carnet");
              }  
          }catch(SQLException ex){
              ex.printStackTrace();              
          }      
          
        return CarnetCliente;
          
      }
      
    //Metodo coger precio hora curso 
    public double PrecioHora (int id){

        double precio = 0;
        try{
                  Connection conexion = obtener_connexio_BD();
                  String carnet = "SELECT una_hora from curso WHERE id = '"+id+"'";

                  Statement stmtpa = conexion.createStatement();
                  ResultSet rs = stmtpa.executeQuery(carnet);

                  while(rs.next()){

                        precio = rs.getDouble("una_hora");
                  }
        }catch(SQLException ex){
                  ex.printStackTrace();              
        }      
        return precio;

    }
   
      public static  CursoAlquilado[] CursoMasAlquilado (){
       
       CursoAlquilado[] alquilado = new CursoAlquilado [100];
       
       try{
            Connection conexion = obtener_connexio_BD();
            String cursos = "SELECT * FROM realiza";

            Statement stmtpa = conexion.createStatement();
            ResultSet rs = stmtpa.executeQuery(cursos);
            int i = 0;
            while(rs.next()){
                
               String dni = rs.getString("Dni"); 
               int curso = rs.getInt("id_curso");
               int descuento = rs.getInt("descuento");
               int horas = rs.getInt("horas");
               double precio = rs.getDouble("precio");
               String fecha = rs.getString("Fecha");
            
                
              alquilado[i] = new CursoAlquilado(dni, curso, descuento, horas, precio, fecha);
              
              i++;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            
        }        
        return alquilado;
             
       
   }
     //Metodo guardar curso alquilado 
    public CursoAlquilado CursosAlqui(){
        //variable para crear el curso
        CursoAlquilado nCurso = new CursoAlquilado();
        
        int hora = 0;
        double precio_total;
        int descuento=0;
        //datos que introduce cliente  
        String dni = txtDni.getText();
        String curso = txtCurso.getText();
        int id = Integer.parseInt(txtID.getText());
        //horas seleccionadas
        if(unahora.isSelected()){
            hora=1;
            descuento=60;
        }
        if(treshoras.isSelected()){
            hora=3;
            descuento=70;
        }
        if(dia.isSelected()){
            hora=6;
            descuento=90;
        }
        
        if(ValidarDni(dni)){     
            System.out.println(dni);
            
            if(ValidarNombre(curso)){
                
             System.out.println(curso);
             
                
                if(id==14 || id==15){
                    System.out.println("El curso elegido es un curso familiar.");
                    //cpgemos carnet cliente y carnet necesario
                    String carcli = ValidarCarnetClienteFamiliar(dni);
                    String carcur = ValidarCarnetCursoFamiliar(id); 
                    //cogemos precio hora segun el id
                    double precio = PrecioHora(id);
                    
                    if(carcli.equals(null) || (!(carcli.equals(carcur)))){
                        double totalP = TotalPrecio(hora, precio);
                        nCurso = new CursoAlquilado(dni, id, 0, hora, totalP, String.valueOf(LocalDate.now()));
                        //guardamos en el hashMap
                        listaAlquilados.put(nCurso, listaAlquilados.containsKey(nCurso) ? listaAlquilados.get(nCurso)+1 :1);
                        //a??andimos el curso en alquilados y mostramos
                        alquilado.add(nCurso);
                        tblalquiler.setItems(alquilado);
                        //limpiamos campos
                        this.txtDni.clear();
                        this.txtCurso.clear();
                        this.txtID.clear();
                        this.unahora.setSelected(false);;
                        this.treshoras.setSelected(false);
                        this.dia.setSelected(false);
                        
                        
                    }else if(carcli.equals(carcur)){
                        
                        double totalP = TotalPrecio(hora, precio);
                        double descFam = 0.60;
                        totalP = totalP * descFam;
                        
                        nCurso = new CursoAlquilado(dni, id, descuento, hora, totalP, String.valueOf(LocalDate.now()));
                        //guardamos en el hashMap
                        listaAlquilados.put(nCurso, listaAlquilados.containsKey(nCurso) ? listaAlquilados.get(nCurso)+1 :1);
                        //a??andimos el curso en alquilados y mostramos
                        alquilado.add(nCurso);
                        tblalquiler.setItems(alquilado);
                        //limpiamos campos
                        this.txtDni.clear();
                        this.txtCurso.clear();
                        this.txtID.clear();
                        this.unahora.setSelected(false);;
                        this.treshoras.setSelected(false);
                        this.dia.setSelected(false);
                    }
                    
        
                }else if(id>=10 || id <=13){
                    
                    System.out.println("El curso elegido es un curso de competicion.");
                    //cogemos carnet cliente y carnet necesario
                    String carcli = ValidarCarnetClienteCompeticion(dni);
                    String carcur = ValidarCarnetCursoCompeticion(id); 
                    //cogemos precio hora segun el id
                    double precio = PrecioHora(id);
                    
                    if(carcli.equals(null) || (!(carcli.equals(carcur)))){
                        Alert alert = new Alert (Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("ERROR");
                        alert.setContentText("Este cliente no dispone del carnet obligatorio para realizar la actividad.");
                        alert.showAndWait();
                        
                    }else if(carcli.equals(carcur)){
                        
                        double totalP = TotalPrecio(hora, precio);
                        
                        nCurso = new CursoAlquilado(dni, id, descuento, hora, totalP, String.valueOf(LocalDate.now()));
                        //guardamos en el hashMap
                        listaAlquilados.put(nCurso, listaAlquilados.containsKey(nCurso) ? listaAlquilados.get(nCurso)+1 :1);
                        //a??andimos el curso en alquilados y mostramos
                        alquilado.add(nCurso);
                        tblalquiler.setItems(alquilado);
                        //limpiamos campos
                        this.txtDni.clear();
                        this.txtCurso.clear();
                        this.txtID.clear();
                        this.unahora.setSelected(false);;
                        this.treshoras.setSelected(false);
                        this.dia.setSelected(false);
                    }
                    
                    }else{
                        Alert alert = new Alert (Alert.AlertType.ERROR);
                        alert.setHeaderText(null);
                        alert.setTitle("ERROR");
                        alert.setContentText("El ID introducido no corresponde a ningun curso.");
                        alert.showAndWait();
                    }
            }else{
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("ERROR");
                alert.setContentText("El curso introducido no se encuentra en la base de datos.");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("El dni introducido no corresponde a ningun cliente.");
            alert.showAndWait();
        }
          
       return nCurso;   
      }
    
    public  double  TotalPrecio (int hora, double precio) {

         double precioTotal = 0;
         double descuento = 0;
         double calcularDesc = 0;
         
         if(hora==1){
             descuento = 0.80;
         }else if(hora==3){
             descuento = 0.70;
            } else if(hora==6){
                descuento = 0.50;
               } 
         
         precioTotal = (hora * precio)*descuento;
         
         return precioTotal;
    }
    
    public String Guarda(String nCurso){
        
        Connection conexion = obtener_connexio_BD();
        System.out.println(nCurso);
        
        //String Guardar = "INSERT INTO realiza (Dni, id_curso, descuento, horas, precio, fecha") VALUES ('+nCurso+')";
        
        
        
        return null;

        
    }
    
}
