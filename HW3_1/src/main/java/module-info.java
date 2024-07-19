module com.ali.badiee.hw2_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ali.badiee.hw2_1 to javafx.fxml;
    exports com.ali.badiee.hw2_1;
}