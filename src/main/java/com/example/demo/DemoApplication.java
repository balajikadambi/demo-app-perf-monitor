package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cluster;
import com.example.demo.model.Environment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;


@RestController
@SpringBootApplication
public class DemoApplication {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    
  
    @GetMapping(value = "/listclusters",produces = "application/json") 
    public List<String> getAllClusters() { 
    	List<String> clusters = new ArrayList<String>();
    	clusters.add("cluster-1");
    	clusters.add("cluster-2");
    	clusters.add("cluster-3");
    	Environment env = new Environment();
    	env.setClusters(clusters);
        return clusters; 
    } 
    
    @GetMapping(value = "/getpodsforcluster/{clusterid}", produces = "application/json") 
    public List<String> getPodsForCluster(@PathVariable String clusterid) { 
    	List<String> pods = new ArrayList<String>();
    	pods.add("pod-1");
    	pods.add("pod-2");
    	pods.add("pod-3");
    	Cluster clstr = new Cluster();
    	clstr.setName(clusterid);
    	clstr.setPods(pods);
        return pods; 
    } 

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
