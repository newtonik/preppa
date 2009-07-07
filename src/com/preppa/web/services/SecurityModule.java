package com.preppa.web.services;

import nu.localhost.tapestry5.springsecurity.services.RequestInvocationDefinition;

import nu.localhost.tapestry5.springsecurity.services.SpringSecurityServices;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.security.userdetails.UserDetailsService;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.services.AliasContribution;

import org.slf4j.Logger;
import org.springframework.security.AuthenticationManager;
import org.springframework.security.providers.AuthenticationProvider;
import org.springframework.security.providers.dao.SaltSource;
import org.springframework.security.providers.encoding.ShaPasswordEncoder;
import org.springframework.security.ui.rememberme.RememberMeServices;
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter;

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
  public static void contributeAlias(
      Configuration<AliasContribution<PasswordEncoder>> configuration ) {

      configuration.add( AliasContribution.create(
          PasswordEncoder.class,
          new ShaPasswordEncoder() ) );
  }

    /**
     * Sets application defaults.
     * @param configuration configuration for the application.
     */
    public static void contributeApplicationDefaults(
            MappedConfiguration<String, String> configuration) {
            configuration.add( "spring-security.rememberme.key", "REMEMBERMEKEYZ" );
           configuration.add("spring-security.loginform.url", "/loginpage");
           configuration.add( "spring-security.force.ssl.login", "false" );
           configuration.add( "spring-security.anonymous.key","acegi_anonymous" );
         configuration.add("spring-security.anonymous.attribute","anonymous,ROLE_ANONYMOUS" );

        configuration.add("spring-security.password.salt", "CEDEBEEF");
        configuration.add( "spring-security.accessDenied.url", "/forbidden" );
        configuration.add("spring-security.openidcheck.url", "/j_spring_openid_security_check");
        configuration.add("spring-security.check.url", "/j_spring_security_check");
        configuration.add("spring-security.logout", "/j_spring_security_logout");
        configuration.add( "spring-security.target.url", "/" );
        configuration.add( "spring-security.afterlogout.url", "/" );
        configuration.add( "spring-security.force.ssl.login", "false" );
        configuration.add( "spring-security.anonymous.key", "acegi_anonymous" );
        configuration.add("spring-security.anonymous.attribute", "anonymous,ROLE_ANONYMOUS" );

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
        return  new  UserDetailsWithPasswordServiceImpl(session, logger, encoder, salt);
       
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
    public static void contributeProviderManager(
        OrderedConfiguration<AuthenticationProvider> configuration,
        @InjectService( "DaoAuthenticationProvider" )
       AuthenticationProvider daoAuthenticationProvider
      //  @InjectService("RememberMeAuthenticationProvider")
      //  AuthenticationProvider rememberAuthenticationProvider
       )
    {

        configuration.add(
            "daoAuthenticationProvider",
            daoAuthenticationProvider );
        //configuration.add("rememberAuthenticationProvider", rememberAuthenticationProvider);
    }

//  public static void contributeProviderManager( OrderedConfiguration<AuthenticationProvider> configuration,
//       //@InjectService("OpenIDAuthenticationProvider") AuthenticationProvider openIdAuthenticationProvider,
//          // @Inject
//         // Logger logger,
//           //  @InjectService("HibernateSessionManager") HibernateSessionManager session,
//       @InjectService( "DaoAuthenticationProvider" )  AuthenticationProvider daoAuthenticationProvider)
//        {
//            configuration.add( "daoAuthenticationProvider", daoAuthenticationProvider );
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
      // }
    public static void contributeFilterSecurityInterceptor(
      Configuration<RequestInvocationDefinition> configuration ) {

      configuration.add( new RequestInvocationDefinition(
          "/ltd.pdf",
          "ROLE_ADMIN" ) );
  }
 public static AuthenticationProcessingFilter buildMyAuthenticationProcessingFilter(
                @SpringSecurityServices final AuthenticationManager manager,
                @SpringSecurityServices final RememberMeServices rememberMeServices,
                @Inject @Value("${spring.security.check.url}") final String authUrl,
                @Inject @Value("${spring.security.target.url}") final String targetUrl,
                @Inject @Value("${spring.security.failure.url}") final String failureUrl)
    throws Exception {
        AuthenticationProcessingFilter filter = new AuthenticationProcessingFilter();
        filter.setAuthenticationManager(manager);
        filter.setAuthenticationFailureUrl(failureUrl);
        filter.setDefaultTargetUrl(targetUrl);
        filter.setFilterProcessesUrl(authUrl);
       filter.setRememberMeServices(rememberMeServices);
        filter.afterPropertiesSet();
        return filter;
    }
//  public static SaltSourceService buildMySaltSource() throws Exception {
//        SaltSourceImpl saltSource = new SaltSourceImpl();
//        saltSource.setSystemWideSalt("CEDEBEEF");
//        saltSource.afterPropertiesSet();
//        return saltSource;
//    }
//   public static void contributeAliasOverrides(
//                @InjectService("MySaltSource")
//                SaltSourceService saltSource,
//                @InjectService("MyAuthenticationProcessingFilter")
//                AuthenticationProcessingFilter authenticationProcessingFilter,
//                Configuration<AliasContribution> configuration) {
//        configuration.add(AliasContribution.create(SaltSourceService.class,
//                saltSource));
//        configuration.add(AliasContribution.create(AuthenticationProcessingFilter.class,
//                authenticationProcessingFilter));
//    }

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
 //           AuthenticationProcessing
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
