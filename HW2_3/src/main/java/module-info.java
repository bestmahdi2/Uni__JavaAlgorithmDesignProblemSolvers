module com.ali.badiee.hw2_3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ali.badiee.hw2_3 to javafx.fxml;
    exports com.ali.badiee.hw2_3;
}