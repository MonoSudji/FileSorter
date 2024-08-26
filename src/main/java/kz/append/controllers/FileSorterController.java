package kz.append.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileSorterController {

    @FXML
    private TextField directoryPathField;

    @FXML
    private TextField fileTypeField;

    @FXML
    private TextField folderNameField;

    private Map<String, File> typeFolders = new HashMap<>();

    @FXML
    private void browseDirectory() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File selectedDirectory = directoryChooser.showDialog(new Stage());
        if (selectedDirectory != null) {
            directoryPathField.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void addTypeMapping() {
        String fileType = fileTypeField.getText().trim();
        String folderName = folderNameField.getText().trim();
        if (!fileType.isEmpty() && !folderName.isEmpty()) {
            File folder = new File(directoryPathField.getText(), folderName);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            typeFolders.put(fileType, folder);
        }
    }

    @FXML
    private void sortFiles() {
        File directory = new File(directoryPathField.getText());
        if (!directory.isDirectory()) {
            System.out.println("Указанный путь не является директорией.");
            return;
        }

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String fileType = getFileExtension(fileName);

                    File targetFolder = typeFolders.get(fileType);
                    if (targetFolder != null) {
                        Path targetPath = Paths.get(targetFolder.getAbsolutePath(), fileName);
                        try {
                            Files.move(file.toPath(), targetPath);
                            System.out.println("Перемещен файл: " + fileName + " в папку: " + targetFolder.getAbsolutePath());
                        } catch (IOException e) {
                            System.out.println("Ошибка перемещения файла " + fileName + ": " + e.getMessage());
                        }
                    } else {
                        System.out.println("Не найдено соответствующей папки для файла: " + fileName);
                    }
                }
            }
        }

        System.out.println("Файлы отсортированы.");
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1).toLowerCase();
    }
}