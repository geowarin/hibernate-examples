package com.geowarin.standalonedatajpa.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.geowarin.standalonedatajpa.config.StandaloneDataJpaConfig;
import com.geowarin.standalonedatajpa.model.User;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { StandaloneDataJpaConfig.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
	DbUnitTestExecutionListener.class })
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	@DatabaseSetup("userAdminData.xml")
	public void testFindAdmin() {
		
		User admin = userRepository.findOne(1L);
		Assert.assertNotNull(admin);
		Assert.assertEquals("admin", admin.getName());
	}
	
	@Test
	@DatabaseSetup("userAdminData.xml")
	public void testFindByName() {
		
		User admin = userRepository.findByName("admin");
		Assert.assertNotNull(admin);
		Assert.assertEquals("admin", admin.getName());
	}
	
	@Test
	@DatabaseSetup("userAdminData.xml")
	public void testFindByNameIgnoreCase() {
		
		User admin = userRepository.findByNameIgnoreCase("AdMIn");
		Assert.assertNotNull(admin);
		Assert.assertEquals("admin", admin.getName());
	}
	
	@Test
	@DatabaseSetup("userAdminData.xml")
	@ExpectedDatabase("afterInsert.xml")
	public void testInsertUser() {
		
		User newUser = new User();
		newUser.setName("inserted");
		userRepository.save(newUser);
	}
	
}
