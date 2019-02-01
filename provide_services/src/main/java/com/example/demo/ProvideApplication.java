package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**启动类
 * @author zhoulk
 * @date  2019-01-03
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ProvideApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProvideApplication.class, args);
	}

}

