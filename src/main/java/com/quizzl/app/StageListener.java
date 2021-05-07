package com.quizzl.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class StageListener implements ApplicationListener<JavafxApplication.StageReadyEvent> {

    private final String applicationTitle = "Title";
    private final Resource fxml;
    private final ApplicationContext applicationContext;

    public StageListener(@Value("classpath:/view/ui.fxml") Resource fxml, ApplicationContext applicationContext) {
        this.fxml = fxml;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(JavafxApplication.StageReadyEvent stageReadyEvent) {
        try
        {
            Stage stage = stageReadyEvent.getStage();

            URL url = fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Parent root = fxmlLoader.load();

            Scene scene = new Scene(root, 600, 600);

            stage.setScene(scene);
            stage.setTitle(this.applicationTitle);
            stage.show();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
