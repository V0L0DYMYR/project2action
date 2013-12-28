package org.project2action.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.project2action.Utils.isNotEmpty;

public class SecureTokenFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(SecureTokenFilter.class);
    private final String securityTokenName;

    public SecureTokenFilter(String securityTokenName){
        this.securityTokenName = checkNotNull(securityTokenName);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void destroy() { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String securityToken = getSecurityToken((HttpServletRequest) request);
        HttpServletResponse resp = (HttpServletResponse) response;

        if (isNotEmpty(securityToken))
            setSecurityToken(resp, securityToken);
        else{
            resp.addIntHeader("Error", 1000);
            resp.addHeader("Message", "Unauthorised");
            resp.sendError(500);
        }
        LOG.info("Response with SecureToken:" + securityToken);
        filterChain.doFilter(request, response);
    }

    private void setSecurityToken(HttpServletResponse response, String secureToken) {
        response.addCookie(new Cookie(securityTokenName, secureToken));
    }

    private String getSecurityToken(HttpServletRequest request) {
        for (Cookie cookie : request.getCookies()) {
            if (securityTokenName.equals(cookie.getName()))
                return cookie.getValue();
        }
        return "";
    }

}
