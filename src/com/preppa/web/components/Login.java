package com.preppa.web.components;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Value;
import org.apache.tapestry5.services.Request;

/**
 * The login page (adapted from the tapestry-spring-security project).
 *
 * @author newtonik
 */
public class Login
{
    @Inject
    @Value("${spring-security.check.url}")
    private String checkUrl;

    @Inject
    @Value("${spring-security.openidcheck.url}")
    private String openidCheckUrl;

    @Inject
    private Request request;

    private boolean failed = false;

    public boolean isFailed()
    {
        return failed;
    }

    public String getLoginCheckUrl()
    {
        return request.getContextPath() + checkUrl;
    }

    public String getOpenIdCheckUrl()
    {
        return request.getContextPath() + openidCheckUrl;
    }

    void onActivate(String extra)
    {
        if (extra.equals("failed"))
        {
            failed = true;
        }
    }


}
