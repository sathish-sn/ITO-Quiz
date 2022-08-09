//package com.example.demo;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//
//
//@RestController
//@RequestMapping("/api/test")
//public class TestController {
//	@Autowired
//	TestService testService ;
//	
//	@PostMapping("/")
//	public ResponseEntity<TestDto> createUser( @RequestBody TestDto testDto) {
//		TestDto createUserDto = this.testService.createUser(testDto);
//		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
//	}
//	
//	@GetMapping("/{testid}")
//	public ResponseEntity<TestDto> getUserById(@PathVariable Integer testid) {
//		return ResponseEntity.ok(this.testService.getUserById(testid));
//	}
//
//}
