module kz.append.sortapplication {
    requires javafx.controls;
    requires javafx.fxml;

    opens kz.append.sortapplication to javafx.fxml;
    exports kz.append;
    opens kz.append to javafx.fxml;
    exports kz.append.controllers;
    opens kz.append.controllers to javafx.fxml;
}