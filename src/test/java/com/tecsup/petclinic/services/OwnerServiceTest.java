package com.tecsup.petclinic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;
import com.tecsup.petclinic.entities.Pet;
import com.tecsup.petclinic.exception.OwnerNotFoundException;
import com.tecsup.petclinic.exception.PetNotFoundException;

@SpringBootTest
public class OwnerServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceTest.class);

	@Autowired
	private OwnerService ownerService;

	/**
	 * 
	 */
	@Test
	public void testFindOwnerById() {

		long ID = 1;
		String FIRST_NAME = "George";
		Owner owner = null;
		
		try {
			owner = ownerService.findById(ID);
			
		} catch (OwnerNotFoundException e) {
			assertThat(e.getMessage(), false);
		}
		logger.info("" + owner);

		assertThat(owner.getFirstName(), is(FIRST_NAME));
	}
	
	@Test
	public void testFindOwnerByName() {

		String FIND_NAME = "George";
		int SIZE_EXPECTED = 1;

		List<Owner> owners = ownerService.findByFirstName(FIND_NAME);

		assertThat(owners.size(), is(SIZE_EXPECTED));
	}
	
	@Test
	public void testFindOwnerByTelephone() {

		String FIND_TELEPHONE = "6085551023";
		int SIZE_EXPECTED = 1;

		List<Owner> owners = ownerService.findByTelephone(FIND_TELEPHONE);

		assertThat(owners.size(), is(SIZE_EXPECTED));
	}

}
