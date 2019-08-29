package com.ares.web.controller;

import com.ares.service.IDictionaryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dictionaryDetail")
@CrossOrigin
public class DictionaryDetailController {
    @Autowired
    private IDictionaryDetailService dictionaryDetailService;

}
