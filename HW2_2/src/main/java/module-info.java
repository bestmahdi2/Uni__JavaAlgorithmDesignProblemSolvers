module com.ali.badiee.hw2_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.ali.badiee.hw2_2 to javafx.fxml;
    exports com.ali.badiee.hw2_2;
}