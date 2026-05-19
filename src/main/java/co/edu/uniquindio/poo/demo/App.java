package co.edu.uniquindio.poo.demo;

import co.edu.uniquindio.poo.demo.Back.*;
import co.edu.uniquindio.poo.demo.VC.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;

import java.io.IOException;

public class App extends Application {

    public static Taller taller = new Taller("tallersito", "Uniquindio");
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Taller Mecanico");
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
            loader.setLocation(App.class.getResource("InicioSesion.fxml"));
            Pane rootLayout = (Pane) loader.load();
            rootLayout.setStyle("-fx-background-color: rgb(52,72,92);");
            InicioSesionVC primaryController = loader.getController();
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

            UserAgentBuilder.builder()
                    .themes(JavaFXThemes.MODENA)
                    .themes(MaterialFXStylesheets.forAssemble(true))
                    .setDeploy(true)
                    .setResolveAssets(true)
                    .build()
                    .setGlobal();
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

    public void MenuAdmin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("hello-view.fxml"));
            Pane rootLayout = (Pane) loader.load();
            MenuPrincipalVC arbitroVC = loader.getController();
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

    public void MenuEmpleado() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("MenuEmpleado.fxml"));
            Pane rootLayout = (Pane) loader.load();
            MenuEmpleadoVC arbitroVC = loader.getController();
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

        Cliente c1 = new Cliente("10245",  "Jose Ramirez",     "3211456789", "jose@gmail.com",    "Calle 10 #5-20",   Estado.Activo);
        Cliente c2 = new Cliente("20356",  "Maria Lopez",      "3157894561", "maria@gmail.com",   "Carrera 8 #12-45", Estado.Activo);
        Cliente c3 = new Cliente("30467",  "Carlos Perez",     "3004561237", "carlos@gmail.com",  "Av 30 #20-10",     Estado.Activo);
        Cliente c4 = new Cliente("40578",  "Ana Gutierrez",    "3128976543", "ana@gmail.com",     "Calle 5 #3-18",    Estado.Inactivo);
        Cliente c5 = new Cliente("50689",  "Luis Hernandez",   "3216549870", "luis@gmail.com",    "Carrera 15 #8-30", Estado.Activo);
        Cliente c6 = new Cliente("60790",  "Laura Torres",     "3009871234", "laura@gmail.com",   "Calle 22 #7-11",   Estado.Activo);
        Cliente c7 = new Cliente("70801",  "Pedro Sanchez",    "3145678901", "pedro@gmail.com",   "Av 6 #14-60",      Estado.Inactivo);
        Cliente c8 = new Cliente("80912",  "Valentina Rios",   "3187654321", "valentina@gmail.com","Calle 40 #9-25",  Estado.Activo);
        taller.agregarCliente(c1); taller.agregarCliente(c2);
        taller.agregarCliente(c3); taller.agregarCliente(c4);
        taller.agregarCliente(c5); taller.agregarCliente(c6);
        taller.agregarCliente(c7); taller.agregarCliente(c8);


        Mecanico m1 = new Mecanico("1235", "Andres Molina",   "3101234567", "Admin@gmail.com", "Barrio Centro",    "Motor",        Estado.Activo);
        Mecanico m2 = new Mecanico("11002", "Ricardo Vargas",  "3112345678", "ricardo@taller.com","Barrio Norte",     "Chasis",       Estado.Activo);
        Mecanico m3 = new Mecanico("11003", "Diana Castro",    "3123456789", "diana@taller.com",  "Barrio Sur",       "Electrico",    Estado.Activo);
        Mecanico m4 = new Mecanico("11004", "Camilo Reyes",    "3134567890", "camilo@taller.com", "Barrio Oriente",   "Frenos",       Estado.Activo);
        Mecanico m5 = new Mecanico("11005", "Natalia Ruiz",    "3145670123", "natalia@taller.com","Barrio Occidente", "Suspension",   Estado.Inactivo);
        Mecanico m6 = new Mecanico("11006", "Jorge Mendez",    "3156781234", "jorge@taller.com",  "Barrio La Flora",  "Transmision",  Estado.Activo);

        taller.agregarMecanico(m1); taller.agregarMecanico(m2);
        taller.agregarMecanico(m3); taller.agregarMecanico(m4);
        taller.agregarMecanico(m5); taller.agregarMecanico(m6);

        Vehiculo v1 = new Vehiculo("ABC123", "Toyota",    "2022", "45000",  c1, Vehiculo.Tipo.Camioneta);
        Vehiculo v2 = new Vehiculo("XYZ456", "Chevrolet", "2020", "80000",  c2, Vehiculo.Tipo.Automovil);
        Vehiculo v3 = new Vehiculo("DEF789", "Mazda",     "2019", "120000", c3, Vehiculo.Tipo.Automovil);
        Vehiculo v4 = new Vehiculo("GHI012", "Yamaha",    "2021", "15000",  c4, Vehiculo.Tipo.Moto);
        Vehiculo v5 = new Vehiculo("JKL345", "Ford",      "2023", "5000",   c5, Vehiculo.Tipo.Camion);
        Vehiculo v6 = new Vehiculo("MNO678", "Renault",   "2018", "150000", c6, Vehiculo.Tipo.Automovil);
        Vehiculo v7 = new Vehiculo("PQR901", "Honda",     "2020", "30000",  c7, Vehiculo.Tipo.Moto);
        Vehiculo v8 = new Vehiculo("STU234", "Kia",       "2022", "60000",  c8, Vehiculo.Tipo.Camioneta);

        taller.agregarVehiculo(v1); taller.agregarVehiculo(v2);
        taller.agregarVehiculo(v3); taller.agregarVehiculo(v4);
        taller.agregarVehiculo(v5); taller.agregarVehiculo(v6);
        taller.agregarVehiculo(v7); taller.agregarVehiculo(v8);

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

        Pago p1 = new Pago("PAG001", java.time.LocalDate.of(2024, 1, 15), Pago.MetodoPago.Efectivo,     Pago.EstadoPago.Pagado);
        Pago p2 = new Pago("PAG002", java.time.LocalDate.of(2024, 2, 20), Pago.MetodoPago.Tarjeta,      Pago.EstadoPago.Pagado);
        Pago p3 = new Pago("PAG003", java.time.LocalDate.of(2024, 3, 10), Pago.MetodoPago.Transferencia,Pago.EstadoPago.No_Pagado);
        Pago p4 = new Pago("PAG004", java.time.LocalDate.of(2024, 4, 5),  Pago.MetodoPago.Efectivo,     Pago.EstadoPago.Pagado);
        Pago p5 = new Pago("PAG005", java.time.LocalDate.of(2024, 5, 18), Pago.MetodoPago.Tarjeta,      Pago.EstadoPago.No_Pagado);
        Pago p6 = new Pago("PAG006", java.time.LocalDate.of(2024, 6, 22), Pago.MetodoPago.Transferencia,Pago.EstadoPago.Pagado);
        Pago p7 = new Pago("PAG007", java.time.LocalDate.of(2024, 7, 30), Pago.MetodoPago.Efectivo,     Pago.EstadoPago.Pagado);
        Pago p8 = new Pago("PAG008", java.time.LocalDate.of(2024, 8, 12), Pago.MetodoPago.Tarjeta,      Pago.EstadoPago.No_Pagado);

        taller.agregarPago(p1); taller.agregarPago(p2);
        taller.agregarPago(p3); taller.agregarPago(p4);
        taller.agregarPago(p5); taller.agregarPago(p6);
        taller.agregarPago(p7); taller.agregarPago(p8);

    }
}