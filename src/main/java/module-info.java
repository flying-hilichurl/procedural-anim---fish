module hilichurl.proceduralanim {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens hilichurl.proceduralanim to javafx.fxml;
    exports hilichurl.proceduralanim;
    exports hilichurl.proceduralanim.controller;
    opens hilichurl.proceduralanim.controller to javafx.fxml;
}