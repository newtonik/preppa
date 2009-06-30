package com.preppa.web.services;

import nu.localhost.tapestry5.springsecurity.services.RequestInvocationDefinition;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.userdetails.UserDetailsService;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.services.AliasContribution;
import org.slf4j.Logger;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.security.providers.dao.SaltSource;
import org.springframework.security.providers.encoding.ShaPasswordEncoder;

/**
 * This module will contain all the security services need for the site. It will be
 * cleaner than throwing everything into the AppModule class
 * @author nwt
 */
public class SecurityModule {
     /**
     * Tapestry method to bind a service binder.
     * @param binder ServiceBinder.
     */
    public static void bind(ServiceBinder binder) {
      binder.bind(EmailService.class, EmailServiceImpl.class);
    }
    
    /**
     * This changes the alias of the password encoder from plain text to Shapassword encodeds
     * @param configuration
     */
    public static void contibuteAlias( Configuration<AliasContribution<PasswordEncoder>> configuration) {
        configuration.add(AliasContribution.create(PasswordEncoder.class, new ShaPasswordEncoder()));

    }
    /**
     * This function builds the UserDetailService used by Spring Security to
     * represent the user object
     * @param logger
     * @param session
     * @param encoder
     * @param salt
     * @return
     */
    public static UserDetailsService buildUserDetailsService(Logger logger,
             @InjectService("HibernateSessionManager") HibernateSessionManager session,
             @Inject
             PasswordEncoder encoder,
             @Inject
            SaltSource salt)
    {
        return  new UserDetailsWithPasswordServiceImpl(session, logger, encoder, salt);
    }

    //OpenID configuration
    //    public static HttpServletRequestFilter buildOpenIDAuthenticationProcessingFilter(
//        final OpenIDAuthenticationProcessingFilter filter)
//{
//    return new HttpServletRequestFilterWrapper(filter);
//}
    /**
     * 
     * @param userDetailsService
     * @return
     * @throws java.lang.Exception
     */
// public static OpenIDAuthenticationProvider buildOpenIDAuthenticationProvider(
//        @InjectService("UserDetailsWithOpenIDService")
//        UserDetailsService userDetailsService) throws Exception
//{
//    OpenIDAuthenticationProvider provider = new OpenIDAuthenticationProvider();
//
//    provider.setUserDetailsService(userDetailsService);
//
//    provider.afterPropertiesSet();
//
//    return provider;
//}

  public static void contributeProviderManager( OrderedConfiguration<AuthenticationProvider> configuration,
       //@InjectService("OpenIDAuthenticationProvider") AuthenticationProvider openIdAuthenticationProvider,
          // @Inject
         // Logger logger,
           //  @InjectService("HibernateSessionManager") HibernateSessionManager session,
       @InjectService( "DaoAuthenticationProvider" )  AuthenticationProvider daoAuthenticationProvider)
        {
            configuration.add( "daoAuthenticationProvider", daoAuthenticationProvider );
//           OpenIDAuthenticationProvider provider = new  OpenIDAuthenticationProvider();
//
//
//        UserDetailsService userDetailService = new UserDetailsWithOpenIDServiceImpl();
//        provider.setUserDetailsService(userDetailService);
//       try {
//           provider.afterPropertiesSet();
//      } catch (Exception e) {
//         // TODO Auto-generated catch block
//          e.printStackTrace();
//       }
//           configuration.add("openIDAuthenticationProvider", provider);
       }
    public static void contributeFilterSecurityInterceptor(
      Configuration<RequestInvocationDefinition> configuration ) {

      configuration.add( new RequestInvocationDefinition(
          "/ltd.pdf",
          "ROLE_ADMIN" ) );
  }

    //    public static OpenIDAuthenticationProcessingFilter buildRealOpenIDAuthenticationProcessingFilter(
//        @SpringSecurityServices final AuthenticationManager manager,
//
//        @SpringSecurityServices final RememberMeServices rememberMeServices,
//
//        @Inject @Value("${spring-security.openidcheck.url}") final String authUrl,
//
//        @Inject @Value("${spring-security.target.url}") final String targetUrl,
//
//        @Inject @Value("${spring-security.failure.url}") final String failureUrl) throws Exception
//        {
//            OpenIDAuthenticationProcessingFilter filter = new OpenIDAuthenticationProcessingFilter();
//
//            filter.setAuthenticationManager(manager);
//
//            filter.setAuthenticationFailureUrl(failureUrl);
//
//            filter.setDefaultTargetUrl(targetUrl);
//
//            filter.setFilterProcessesUrl(authUrl);
//
//            filter.setRememberMeServices(rememberMeServices);
//
//            filter.afterPropertiesSet();
//
//            return filter;
//        }
//


}
