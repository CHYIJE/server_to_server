package com.tenco.bank.controller;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.tenco.bank.dto.Todo;

import lombok.extern.slf4j.Slf4j;



//@Controller + @Responsbody
@RestController // IoC 대상
@Slf4j
public class HomeController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	// 주소 설계 : http://localhost.:8080/m-todos/${id}
//	// 테스트 주소 : http://localhost.:8080/m-todos/10
//	@GetMapping("/m-todos/{id}")
//	public ResponseEntity<?> rsetTest1(@PathVariable(name="id") Integer id) {
//		
//		//1. 데이터 추출 확인
//		System.out.println("id : " + id);
//		
//		// RestTemplate 사용법
//		// 1. URL 객체를 설정한다.
//		URI uri = UriComponentsBuilder
//				.fromUriString("https://jsonplaceholder.typicode.com/")
//				.path("/todos")
//				.path("/" + id)
//				.build()
//				.toUri();
//				
//		
//		
//		// 2.
//		RestTemplate restTemplate1 = new RestTemplate();
//		
//		ResponseEntity<String> response = restTemplate1.getForEntity(uri, String.class);
//		System.out.println(response.getStatusCodeValue());
//		System.out.println("-----------------------");
//		System.out.println(response.getHeaders());
//		System.out.println("-----------------------");
//		System.out.println(response.getBody());
//		
//		
//		return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
//	}
	
	// 주소 설계 : http://localhost.:8080/m-todos/${id}
		// 테스트 주소 : http://localhost.:8080/m-todos/10
		@GetMapping("/m-todos/{id}")
		public ResponseEntity<?> rsetTest1(@PathVariable(name="id") Integer id) {
			
			//1. 데이터 추출 확인
			System.out.println("id : " + id);
			
			// RestTemplate 사용법
			// 1. URL 객체를 설정한다.
			URI uri = UriComponentsBuilder
					.fromUriString("https://jsonplaceholder.typicode.com/")
					.path("/todos")
					.path("/" + id)
					.build()
					.toUri();
					
			
			
			// 2.
			RestTemplate restTemplate1 = new RestTemplate();
			
			ResponseEntity<Todo> response = restTemplate1.getForEntity(uri, Todo.class);
			System.out.println(response.getBody().toString());
			
			return ResponseEntity.status(HttpStatus.OK).body(response.getBody());
		}
	
		//주소설계 : http://localhost.:8080/exchange-test
		@GetMapping("/exchange-test")
		public ResponseEntity<?> restChangeTest() {
			
			// 여기 주소는 리소스 서버 주소 설정을 해야한다.
			URI uri = UriComponentsBuilder
					.fromUriString("https://jsonplaceholder.typicode.com/")
					.path("/posts")
					.build()
					.toUri();
			
			// 2. 객체 생성
			RestTemplate restTemplate1 = new RestTemplate();
			// HTTP 메세지 Header 생성하기
			// exchange 메서드 활용
			
			//1. 헤더 구성
			HttpHeaders headers = new HttpHeaders();
			// 'Content-type': 'application/json; charset=UTF-8',
			headers.add("Content-type", "application/json; charset=UTF-8");
			
			// 2. 바디 구성
			MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("title", "안녕 반가워");
			params.add("body", "후미진 프로젝트 실에서 도시락 먹기");
			params.add("useId", "11");
			
			// 3. 헤더와 바디 결함 -- > HttpEntity Objecty
			HttpEntity<MultiValueMap<String, String>> requestEntity
				= new HttpEntity<>(params, headers);

			
			// 4. RestTemplate을 활용해서 Http 통신 요청
			ResponseEntity<String> response = 
					restTemplate1.exchange(uri, HttpMethod.POST, requestEntity, String.class);
			
			System.out.println("response Headr : " + response.getHeaders());
			System.out.println("response Headr : " + response.getBody());
			
			return ResponseEntity.status(HttpStatus.CREATED).body(response.getBody());
		}
		
		
	
}
