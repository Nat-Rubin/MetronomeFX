module com.example.metronomefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;


    opens com.example.metronomefx to javafx.fxml;
    exports com.example.metronomefx;
}