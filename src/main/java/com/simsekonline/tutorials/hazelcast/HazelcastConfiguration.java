package com.simsekonline.tutorials.hazelcast;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hazelcast.config.ClasspathXmlConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

@Configuration
public class HazelcastConfiguration {
	@Bean
	public Config config() {
		// return new Config().setManagementCenterConfig(new ManagementCenterConfig()
		// .setEnabled(true)
		// .setUrl("http://localhost:8080/hazelcast-mancenter"));

		return new ClasspathXmlConfig("hazelcast.xml");

	}

	@Bean
	public HazelcastInstance instance(Config config) {
		return Hazelcast.newHazelcastInstance(config);
	}

	@Bean
	public IMap<String, String> nameMap(HazelcastInstance instance) {
		return instance.getMap("nameMap");
	}

}
