package com.quizzl.app.util;

import javafx.fxml.FXMLLoader;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringFxmlLoader {

    private static ApplicationContext applicationContext;

    public SpringFxmlLoader(ApplicationContext applicationContext) {
        SpringFxmlLoader.applicationContext = applicationContext;
    }

    /**
     * <p>Since we are using the Dependency Injector from Java Spring,
     * we need to have a wrapper class for FXML Loader to Inject the Services into the Constructor</p>
     * @param url URL for the Path to the FXML file
     * @return Returns a FXML Loader casted into Object
     */
    public static Object getLoader(String url) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(clazz -> applicationContext.getBean(clazz));
        loader.setLocation(SpringFxmlLoader.class.getResource(url));
        return loader;
    }
}