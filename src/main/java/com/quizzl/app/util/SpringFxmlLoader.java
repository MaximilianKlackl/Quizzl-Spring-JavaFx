package com.quizzl.app.util;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SpringFxmlLoader {

    private static ApplicationContext applicationContext;

    public SpringFxmlLoader(ApplicationContext applicationContext) {
        SpringFxmlLoader.applicationContext = applicationContext;
    }

    public static Object getLoader(String url) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(clazz -> applicationContext.getBean(clazz));
        loader.setLocation(SpringFxmlLoader.class.getResource(url));
        return loader;
    }
}