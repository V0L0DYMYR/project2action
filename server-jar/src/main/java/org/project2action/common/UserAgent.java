package org.project2action.common;

import javax.servlet.http.HttpServletRequest;

public class UserAgent {

    private final String userAgent;

    public UserAgent(HttpServletRequest req){
        userAgent = req.getHeader("User-Agent");
    }

    public boolean isMobile(){
        return userAgent.toLowerCase().indexOf("mobile") != -1;
    }
}
