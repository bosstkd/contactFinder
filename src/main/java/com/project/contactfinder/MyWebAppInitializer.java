package com.project.contactfinder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 *
 * @author Amine
 */
public class MyWebAppInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
                AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(SpringConfiguration.class);
		sc.addListener(new ContextLoaderListener(rootContext));    }
        }
