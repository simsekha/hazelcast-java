package com.simsekonline.tutorials.hazelcast;

import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.core.IMap;

@RestController
public class Contoller {
	@Autowired
	HazelcastInstance instance;

	@Autowired
	IMap<String, String> nameMap;

	@RequestMapping(path = "/putValue")
	public String putValue(@RequestParam String key, @RequestParam String value) {
		nameMap.put(key, value);
		return "ok";
	}

	@RequestMapping(path = "/getValue")
	public String putValue(@RequestParam String key) {
		return nameMap.get(key);
	}

	@RequestMapping(path = "/executorExample")
	public String executorExample() throws InterruptedException {

		// HazelcastInstance instance = Hazelcast.newHazelcastInstance();

		IExecutorService exec = instance.getExecutorService("exec");
		IntStream.range(1, 1000).forEach(i -> {
			exec.submit(new EchoTask(i));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		return "Hello";

	}
}
