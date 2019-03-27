package com.example.demo;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**启动类
 * @author zhoulk
 * @date  2019-01-03
 */
@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
public class CustomerApplication {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate(){
		return new RestTemplate();
	}
	/*@Bean
	public ServletRegistrationBean getServlet(){

		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();

		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);

		registrationBean.setLoadOnStartup(1);

		registrationBean.addUrlMappings("/hystrix.stream");

		registrationBean.setName("HystrixMetricsStreamServlet");


		return registrationBean;
	}*/

	@Bean
	public ServletRegistrationBean hystrixMetricsStreamServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
		registration.addUrlMappings("/hystrix.stream");
		return registration;
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
}