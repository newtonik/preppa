/*
 * Copyright (c) 2008 American Power Conversion, All Rights Reserved
 */
package com.preppa.web.services;

import com.preppa.web.data.ArticleDAO;
import com.preppa.web.data.ArticleDAOHibImpl;
import com.preppa.web.data.LongDualPassageDAO;
import com.preppa.web.data.LongDualPassageDAOHibImpl;
import com.preppa.web.data.LongPassageDAO;
import com.preppa.web.data.LongPassageDAOHibImpl;
import com.preppa.web.data.PassageDAO;
import com.preppa.web.data.PassageDAOHibImpl;
import com.preppa.web.data.QuestionDAO;
import com.preppa.web.data.QuestionDAOHimpl;
import com.preppa.web.data.QuestiontypeDAO;
import com.preppa.web.data.QuestiontypeDAOHibImpl;
import com.preppa.web.data.ShortDualPassageDAO;
import com.preppa.web.data.ShortDualPassageDAOHibImpl;
import com.preppa.web.data.ShortPassageDAO;
import com.preppa.web.data.ShortPassageDAOHibImpl;
import com.preppa.web.data.TestsubjectDAO;
import com.preppa.web.data.TestsubjectDAOHibImpl;
import com.preppa.web.data.TopicDAO;
import com.preppa.web.data.TopicDAOHImpl;
import com.preppa.web.data.UserDAO;
import com.preppa.web.data.UserDAOHibImpl;
import com.preppa.web.data.VocabDAO;
import com.preppa.web.data.VocabDAOHibImpl;
import java.io.IOException;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.hibernate.HibernateTransactionDecorator;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.Match;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.RequestFilter;
import org.apache.tapestry5.services.RequestGlobals;
import org.apache.tapestry5.services.RequestHandler;
import org.apache.tapestry5.services.Response;
import org.slf4j.Logger;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 * @author gsubrama
 */
public final class AppModule {

    /**
     * Default Constructor.
     */
    private AppModule() {
    }

    /**
     * Tapestry method to bind a service binder.
     * @param binder ServiceBinder.
     */
    public static void bind(ServiceBinder binder) {
        // binder.bind(MyServiceInterface.class, MyServiceImpl.class);
        binder.bind(UserDAO.class, UserDAOHibImpl.class);
        binder.bind(ArticleDAO.class, ArticleDAOHibImpl.class);
        binder.bind(VocabDAO.class, VocabDAOHibImpl.class);
        binder.bind(TestsubjectDAO.class, TestsubjectDAOHibImpl.class);
        binder.bind(PassageDAO.class, PassageDAOHibImpl.class);
        binder.bind(ShortDualPassageDAO.class, ShortDualPassageDAOHibImpl.class);
        binder.bind(ShortPassageDAO.class, ShortPassageDAOHibImpl.class);
        binder.bind(LongDualPassageDAO.class, LongDualPassageDAOHibImpl.class);
        binder.bind(LongPassageDAO.class, LongPassageDAOHibImpl.class);
        binder.bind(QuestiontypeDAO.class, QuestiontypeDAOHibImpl.class);
        binder.bind(QuestionDAO.class, QuestionDAOHimpl.class);

        binder.bind(TopicDAO.class, TopicDAOHImpl.class);

        
    // Make bind() calls on the binder object to define most IoC services.
    // Use service builder methods (example below) when the implementation
    // is provided inline, or requires more initialization than simply
    // invoking the constructor.
    }

    /**
     * Sets application defaults.
     * @param configuration configuration for the application.
     */
    public static void contributeApplicationDefaults(
            MappedConfiguration<String, String> configuration) {
        // Contributions to ApplicationDefaults will override any contributions to
        // FactoryDefaults (with the same key). Here we're restricting the supported
        // locales to just "en" (English). As you add localised message catalogs and other assets,
        // you can extend this list of locales (it's a comma seperated series of locale names;
        // the first locale name is the default when there's no reasonable match).

        configuration.add(SymbolConstants.SUPPORTED_LOCALES, "en,fr,pt");
        configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
        configuration.add(SymbolConstants.FILE_CHECK_INTERVAL, "5 m");
         configuration.add(SymbolConstants.FORCE_ABSOLUTE_URIS, "true");

       // configuration.add("acegi.failure.url", "/loginpage/failed");
        //configuration.add("acegi.password.encoder", "org.acegisecurity.providers.encoding.Md5PasswordEncoder");
        
    }

    /**
     * This is a service definition, the service will be named "TimingFilter". The interface,
     * RequestFilter, is used within the RequestHandler service pipeline, which is built from the
     * RequestHandler service configuration. Tapestry IoC is responsible for passing in an
     * appropriate Log instance. Requests for static resources are handled at a higher level, so
     * this filter will only be invoked for Tapestry related requests.
     *
     * <p>
     * Service builder methods are useful when the implementation is inline as an inner class
     * (as here) or require some other kind of special initialization. In most cases,
     * use the static bind() method instead.
     *
     * <p>
     * If this method was named "build", then the service id would be taken from the
     * service interface and would be "RequestFilter".  Since Tapestry already defines
     * a service named "RequestFilter" we use an explicit service id that we can reference
     * inside the contribution method.
     * @param logger logger.
     * @return request filter.
     */
    public static RequestFilter buildTimingFilter(final Logger logger) {
        return new RequestFilter() {

            /**
             * Service method for Tapestry.
             * @throws IOException if something goes wrong.
             */
            public boolean service(Request request, Response response, RequestHandler handler)
                    throws IOException {
                long startTime = System.currentTimeMillis();

                try {
                    // The reponsibility of a filter is to invoke the corresponding method
                    // in the handler. When you chain multiple filters together, each filter
                    // received a handler that is a bridge to the next filter.

                    return handler.service(request, response);
                } finally {
                    long elapsed = System.currentTimeMillis() - startTime;
                    logger.debug(String.format("Request time: %d ms", elapsed));
                }
            }
        };
    }

    /**
     *
     * @param requestGlobals request globals.
     * @return service handler.
     */
    public static RequestFilter buildUtf8Filter(
            @InjectService("RequestGlobals") final RequestGlobals requestGlobals) {
        return new RequestFilter() {

            public boolean service(Request request, Response response, RequestHandler handler)
                    throws IOException {
                requestGlobals.getHTTPServletRequest().setCharacterEncoding("UTF-8");
                return handler.service(request, response);
            }
        };
    }

    /**
     * This is a contribution to the RequestHandler service configuration. This is how we extend
     * Tapestry using the timing filter. A common use for this kind of filter is transaction
     * management or security.
     * @param configuration application configuration
     * @param utf8Filter utf8 filter to enable unicode.
     */
    public static void contributeRequestHandler(OrderedConfiguration<RequestFilter> configuration,
            @InjectService("Utf8Filter") final RequestFilter utf8Filter) {
        // Each contribution to an ordered configuration has a name, When necessary, you may
        // set constraints to precisely control the invocation order of the contributed filter
        // within the pipeline.

        // Please uncomment me when you want to know timing information.
        //configuration.add("Timing", filter);

        configuration.add("Utf8Filter", utf8Filter);
    }

    public static void contributeHibernateEntityPackageManager(Configuration<String> configuration) {
        
    }
    @Match("*DAO")
    public static <T> T decorateTransactionally(HibernateTransactionDecorator decorator, Class<T> serviceInterface,
                                                T delegate,
                                                String serviceId)
    {
        return decorator.build(serviceInterface, delegate, serviceId);
    }
}
