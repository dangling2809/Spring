/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.piyush.test.springaop.controller;

import com.piyush.test.springaop.aspects.Loggable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author piyush
 */
@Controller
@RequestMapping(value = "/hello")
public class HelloController {

    @Loggable
    @RequestMapping(value = "/say")
    public ModelAndView hello() {
        return new ModelAndView("hello");
    }
}
