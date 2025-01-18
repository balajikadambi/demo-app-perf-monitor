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
        return "<div>List of clusters in the environment:</div><table border='1'><tr><td>Cluster1</td></tr><tr><td>Cluster2</td></tr><tr><td>Cluster3</td></tr></div>"; 
    } 
    
    @GetMapping("/getpodsforcluster/{clusterid}") 
    public String geBookById(@PathVariable String clusterid) { 
        return "<div>List of pods for cluster "+clusterid+":</div><table border='1'><tr><td>Pod1</td></tr><tr><td>Pod2</td></tr><tr><td>Pod3</td></tr></div>"; 
    } 

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}