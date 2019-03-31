package com.example.kin.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
// import the Prometheus packages.
//import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
//import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
// Prometheus counter package.
//import io.prometheus.client.Counter;
// Prometheus Histogram package.
//import io.prometheus.client.Histogram;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

import java.util.ArrayList;

@Controller

// Add a Prometheus metrics enpoint to the route `/prometheus`. `/metrics` is already taken by Actuator.
//@EnablePrometheusEndpoint
// Pull all metrics from Actuator and expose them as Prometheus metrics. Need to disable security feature in properties file.
//@EnableSpringBootMetricsCollector

public class DemoController {

    private Counter count = Metrics.counter("kin_custom_metric");
	
    @Autowired
    private ItemRepository repository;

    // Define a counter metric for /prometheus
	//static final Counter requests = Counter.build()
    	//.name("http_requests_total").help("Total number of requests.").register();
    @Timed
    @RequestMapping("/")
    public String index(Model model) {
        // Increase the counter metric
	//	requests.inc();
	count.increment();
        ArrayList<Item> List = (ArrayList<Item>) repository.findAll();
        model.addAttribute("newitem", new Item());
        model.addAttribute("items", new ListViewModel(List));
        return "index";
    }

    @RequestMapping("/add")
    public String Todo(@ModelAttribute Item requestItem) {
        count.increment();
	Item item = new Item(requestItem.getCategory(), requestItem.getName());
        repository.save(item);
        return "redirect:/";
    }

    @RequestMapping("/update")
    public String updateitem(@ModelAttribute ListViewModel requestItems) {
        count.increment();
	for (Item requestItem : requestItems.getList() ) {
             Item item = new Item(requestItem.getCategory(), requestItem.getName());
             item.setComplete(requestItem.isComplete());
             item.setId(requestItem.getId());
             repository.save(item);
        }
        return "redirect:/";
    }
}
