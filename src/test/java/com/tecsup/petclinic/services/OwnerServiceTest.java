package com.tecsup.petclinic.services;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tecsup.petclinic.entities.Owner;

import com.tecsup.petclinic.exception.OwnerNotFoundException;


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
	@Test
	public void testUpdateOwnerPet() {
		
		String OWNER_NAME ="Maria";
		String OWNER_LASTNAME="Escobito";
		String ADDRESS = "345 Maple St.";
		String CITY= "Madison";
		String TELEPHONE= "6085557683";
		long create_id = -1;
		
		String UP_OWNER_NAME ="Chris";
		String UP_OWNER_LASTNAME="Jhonson";
		String UP_ADDRESS = "350 Maple St.";
		String UP_CITY= "Monona";
		String UP_TELEPHONE= "6085557143";
		
		Owner owner = new Owner(OWNER_NAME, OWNER_LASTNAME, ADDRESS, CITY, TELEPHONE);

		logger.info(">"+owner);
		Owner ownerCreated = ownerService.create(owner);
		logger.info(">>"+ownerCreated);
		
		create_id = ownerCreated.getId();
		
		
		ownerCreated.setFirstName(UP_OWNER_NAME);
		ownerCreated.setLastName(UP_OWNER_LASTNAME);
		ownerCreated.setAddress(UP_ADDRESS);
		ownerCreated.setCity(UP_CITY);
		ownerCreated.setTelephone(UP_TELEPHONE);
		
		
		Owner upgradeOwner = ownerService.update(ownerCreated);
		logger.info(">>>>" + upgradeOwner);
		
		assertThat(create_id, notNullValue());
		assertThat(upgradeOwner.getId(), is(create_id));
		assertThat(upgradeOwner.getFirstName(), is(UP_OWNER_NAME));
		assertThat(upgradeOwner.getLastName(), is(UP_OWNER_LASTNAME));
		assertThat(upgradeOwner.getAddress(), is(UP_ADDRESS));
		assertThat(upgradeOwner.getCity(), is(UP_CITY));
		assertThat(upgradeOwner.getTelephone(), is(UP_TELEPHONE));
		
		
	}
	@Test
    public void testDeleteOwner() {

        String OWNER_NAME = "Luis";
        String OWNER_LAST_NAME = "Jr";
        String OWNER_ADDRESS = "Av. Los incas 1130";
        String OWNER_CITY = "Lima Cercado";
        String OWNER_TELEPHONE = "981545182";

        Owner owner = new Owner(OWNER_NAME, OWNER_LAST_NAME, OWNER_ADDRESS, OWNER_CITY, OWNER_TELEPHONE);
        owner = ownerService.create(owner);
        logger.info("" + owner);

        try {
            ownerService.delete(owner.getId());
        } catch (OwnerNotFoundException e) {
            fail(e.getMessage());
        }
            
        try {
            ownerService.findById(owner.getId());
            fail("Owner id = " + owner.getId() + "has not delete");
        } catch (OwnerNotFoundException e) {
            assertThat(true, is(true));
        }                 

    }

}
