package com.geowarin.hibernate.jpa.standalone;


import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.geowarin.hibernate.jpa.standalone.model.User;

/**
 * 
 * 
 * @author Geoffroy Warin (https://github.com/geowarin)
 *
 */
public class AppTest extends AbstractDbUnitJpaTest {

	@Test
	public void testFind() {
		
		User user = entityManager.find(User.class, 1L);
		Assert.assertNotNull(user);
		Assert.assertEquals("userTest", user.getName());
	}
	
	@Test
	public void testInsert() {
		
		User newUser = new User();
		newUser.setName("insert");
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(newUser);
		long id = newUser.getId();
		
		entityManager.getTransaction().commit();
		
		User user = entityManager.find(User.class, id);
		Assert.assertNotNull(user);
		Assert.assertEquals("insert", user.getName());
	}
	
	@Test
	public void testFindAll() {
		
		List<User> allUsers = entityManager.createQuery("from User").getResultList();
		Assert.assertEquals(2, allUsers.size());
	}
}
