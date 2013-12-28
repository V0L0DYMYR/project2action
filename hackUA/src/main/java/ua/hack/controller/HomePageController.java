package ua.hack.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: cas
 * Date: 28.12.13
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class HomePageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(HomePageController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        LOGGER.info("HOME!");
        return "index";
    }
}
