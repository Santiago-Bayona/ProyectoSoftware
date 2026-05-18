package co.edu.uniquindio.poo.demo;

import co.edu.uniquindio.poo.demo.Back.*;
import co.edu.uniquindio.poo.demo.VC.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static Taller taller = new Taller("tallersito", "Uniquindio");
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        //this.primaryStage.initStyle(StageStyle.TRANSPARENT); // Hacer la ventana transparente
        this.primaryStage.setTitle("Bienvenido");
        openViewPrincipal();
    }

    private boolean datosInicializados = false;
    private double xOffset = 0;
    private double yOffset = 0;


    public void openViewPrincipal() {
        if (!datosInicializados) {
            inicializarData();
            datosInicializados = true;
        }
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("hello-view.fxml"));
            Pane rootLayout = (Pane) loader.load();
            rootLayout.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
            MenuPrincipalVC primaryController = loader.getController();
            primaryController.setApp(this);

            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            rootLayout.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            Scene scene = new Scene(rootLayout);
            //scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

    public void Cliente() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("Cliente.fxml"));
            Pane rootLayout = (Pane) loader.load();
            ClienteVC arbitroVC = loader.getController();
            arbitroVC.setApp(this);

            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            rootLayout.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Mecanico() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("Mecanico.fxml"));
            Pane rootLayout = (Pane) loader.load();
            MecanicoVC arbitroVC = loader.getController();
            arbitroVC.setApp(this);

            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            rootLayout.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Vehiculo() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("Vehiculo.fxml"));
            Pane rootLayout = (Pane) loader.load();
            VehiculoVC arbitroVC = loader.getController();
            arbitroVC.setApp(this);

            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            rootLayout.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Repuesto() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("Repuesto.fxml"));
            Pane rootLayout = (Pane) loader.load();
            RepuestoVC arbitroVC = loader.getController();
            arbitroVC.setApp(this);

            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            rootLayout.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Orden() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("Orden.fxml"));
            Pane rootLayout = (Pane) loader.load();
            OrdenVC arbitroVC = loader.getController();
            arbitroVC.setApp(this);

            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            rootLayout.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Pago() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("Pago.fxml"));
            Pane rootLayout = (Pane) loader.load();
            PagoVC arbitroVC = loader.getController();
            arbitroVC.setApp(this);

            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            rootLayout.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Servicio() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("Servicio.fxml"));
            Pane rootLayout = (Pane) loader.load();
            ServicioVC arbitroVC = loader.getController();
            arbitroVC.setApp(this);

            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            rootLayout.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void Factura() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("Factura.fxml"));
            Pane rootLayout = (Pane) loader.load();
            FacturaVC arbitroVC = loader.getController();
            arbitroVC.setApp(this);

            rootLayout.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            rootLayout.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void inicializarData() {

        Cliente cliente = new Cliente("10245", "jose","321145", "hddgbhdh@gmail.com", "hdhdhdhd", Estado.Activo);
        taller.agregarCliente(cliente);

        Mecanico mecanico1 = new Mecanico("10245", "jose","321145", "hddgbhdh@gmail.com", "hdhdhdhd", "mtor ",  Estado.Activo);
        Mecanico mecanico2 = new Mecanico("10445", "Maria","322155", "maria@gmail.com", "calle 50", "Chasis ",  Estado.Activo);
        taller.agregarMecanico(mecanico1);
        taller.agregarMecanico(mecanico2);

        Vehiculo vehiculo = new Vehiculo("ABC123","Toyota","2024", "15000", cliente, Vehiculo.Tipo.Camioneta );
        taller.agregarVehiculo(vehiculo);

        Repuesto r1 = new Repuesto(
                "Filtro de aceite",
                "REP001",
                "Bosch",
                12.99,
                15
        );

        Repuesto r2 = new Repuesto(
                "Pastillas de freno",
                "REP002",
                "Brembo",
                45.50,
                10
        );

        Repuesto r3 = new Repuesto(
                "Batería 12V",
                "REP003",
                "ACDelco",
                120.00,
                6
        );

        Repuesto r4 = new Repuesto(
                "Bujía Iridium",
                "REP004",
                "NGK",
                18.75,
                20
        );

        Repuesto r5 = new Repuesto(
                "Radiador",
                "REP005",
                "Denso",
                210.40,
                4
        );

        Repuesto r6 = new Repuesto(
                "Amortiguador delantero",
                "REP006",
                "Monroe",
                89.99,
                8
        );

        Repuesto r7 = new Repuesto(
                "Alternador",
                "REP007",
                "Valeo",
                275.30,
                3
        );

        Repuesto r8 = new Repuesto(
                "Correa de distribución",
                "REP008",
                "Gates",
                65.90,
                12
        );

        Repuesto r9 = new Repuesto(
                "Bomba de gasolina",
                "REP009",
                "Delphi",
                155.80,
                5
        );

        Repuesto r10 = new Repuesto(
                "Llanta Rin 17",
                "REP010",
                "Michelin",
                198.99,
                9
        );

        taller.agregarRespuesto(r1);
        taller.agregarRespuesto(r2);
        taller.agregarRespuesto(r3);
        taller.agregarRespuesto(r4);
        taller.agregarRespuesto(r5);
        taller.agregarRespuesto(r6);
        taller.agregarRespuesto(r7);
        taller.agregarRespuesto(r8);
        taller.agregarRespuesto(r9);
        taller.agregarRespuesto(r10);

    }
}