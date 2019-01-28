package com.vinodh.web;

import com.vinodh.dto.Homepage;
import com.vinodh.service.HomeService;
import com.vinodh.utility.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/library")
@Api(value = "library", tags = {"Home"}, description = "Home page for Library Application")
@Slf4j
public class HomeController {

    private HomeService homeService;
    private Utils utils;

    public HomeController(HomeService homeService, Utils utils) {
        this.homeService = homeService;
        this.utils = utils;
    }

    @GetMapping("/home")
    @ApiOperation(value = "home", httpMethod = "GET")
    public ResponseEntity<Homepage> home() {
        return ResponseEntity.ok(homeService.testHomePage());
    }

    @GetMapping("/home/{userName}")
    @ApiOperation(value = "Find home based on userName", httpMethod = "GET")
    public ResponseEntity<Resource<Homepage>> getHomeByUserName(@PathVariable String userName) {
        Resource<Homepage> resource = new Resource<>(homeService.testHomePage(userName));
        resource.add(linkTo(methodOn(this.getClass()).home()).withRel("library-home"));
        return ResponseEntity.ok(resource);
    }
}
