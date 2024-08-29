package org.example;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Initializes the Spring web application context and configures the DispatcherServlet.
 * This class extends {@link AbstractAnnotationConfigDispatcherServletInitializer} to set up
 * the root and servlet configuration classes and define servlet mappings.
 */
public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * Specifies the configuration classes for the root application context.
     *
     * @return an array of {@link Class} objects representing the configuration classes.
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /**
     * Specifies the configuration classes for the DispatcherServlet application context.
     * This implementation returns {@code null}, meaning no additional configuration is needed
     * for the DispatcherServlet.
     *
     * @return {@code null} since no additional configuration is required.
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    /**
     * Specifies the URL patterns to which the DispatcherServlet will be mapped.
     *
     * @return an array of URL patterns for mapping the DispatcherServlet, e.g., {@code "/"}.
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
