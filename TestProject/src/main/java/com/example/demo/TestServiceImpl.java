//package com.example.demo;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TestServiceImpl implements TestService {
//	
//	@Autowired
//	TestRepo testRepo;
//	
//	@Autowired
//	ModelMapper modelMapper;
//	
//
//	@Override
//	public TestDto createUser(TestDto testdto) {
//		Test test = this.modelMapper.map(testdto, Test.class);
//		Test newUser = this.testRepo.save(test);
//		return this.modelMapper.map(newUser, TestDto.class);
//	}
//
//	@Override
//	public TestDto getUserById(Integer id) {
//		Test test = this.testRepo.findById(id).orElse(null);
//		
//		 return this.modelMapper.map(test, TestDto.class);
//	}
//
//}
