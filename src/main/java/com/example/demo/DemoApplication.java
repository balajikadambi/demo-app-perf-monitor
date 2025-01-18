package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class DemoApplication {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    
    @GetMapping("/listclusters") 
    public String getAllClusters() { 
        return "List of clusters : cluster-1, cluster-2, cluster-3."; 
    } 
    
    @GetMapping("/getpodsforcluster/{clusterid}") 
    public String geBookById(@PathVariable String clusterid) { 
        return "Pods for cluster id "+clusterid+" are pod-1,pod,pod-3."; 
    } 

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
