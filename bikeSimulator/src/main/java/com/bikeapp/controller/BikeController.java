
package com.bikeapp.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bikeapp.bean.Bike;
import com.bikeapp.bean.BikeEvent;
import com.bikeapp.bean.BikeLog;
import com.bikeapp.service.BikeService;

@RestController
@RequestMapping("kafka")
public class BikeController {
	
	@Autowired
	BikeService bikeservice;

	@GetMapping("/bike/get")
	public String getController()
	{
	return "get";
	}

	@PostMapping("/bike/post")
	public String postController(@RequestBody Bike bike)
	{
	System.out.println(" post");
	//bikeservice.postBike(bike);
	return"sucessfully posted";
	}

	@Autowired
	private KafkaTemplate<String, Bike> kafkaTemplate;
	
	private static final String TOPICBIKE = "Bike";
	private static final String TOPICBIKEEVENT = "BikeEvent";
	
	 
	@PostMapping("/publish/bike")
	public String getUserId(@RequestBody Bike bike) { 
      kafkaTemplate.send(TOPICBIKE, bike);
      return "Published successfully";
  }
	@PostMapping("/publish/vehicle")
	public String publishVehicle(@RequestBody BikeLog bikeLog) {
		bikeservice.bikeRegister(bikeLog);
      return "Published data successfully";
	}
	
	@PostMapping("/publish/bikeevent")
	public String getBikeEvent(@RequestBody BikeEvent bikeEvent) {
		bikeservice.postBikeEvent(bikeEvent);
		 //kafkaTemplate.send(TOPICBIKE, bike);
      return "Published successfully";
  }
	
	@GetMapping("/publish")
	public String post() throws IOException
	{ 
			 bikeservice.post();
			 return"Published successfully";
			 }
	@GetMapping("/publish/postbikeevent")
	public String bikeEvent()
	{ 
			 
			bikeservice.bikeEvent();
			 return"Published successfully";
	}

	
}
	
	
	
	
	
	