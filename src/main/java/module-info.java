module co.edu.uniquindio.poo.demo {

    requires org.kordamp.bootstrapfx.core;
    requires MaterialFX;

    opens co.edu.uniquindio.poo.demo to javafx.fxml;
    exports co.edu.uniquindio.poo.demo;
    exports co.edu.uniquindio.poo.demo.Back;
    opens co.edu.uniquindio.poo.demo.Back to javafx.fxml;
    exports co.edu.uniquindio.poo.demo.Controller;
    opens co.edu.uniquindio.poo.demo.Controller to javafx.fxml;
    opens co.edu.uniquindio.poo.demo.VC to javafx.fxml;
    exports co.edu.uniquindio.poo.demo.VC to javafx.fxml;
}