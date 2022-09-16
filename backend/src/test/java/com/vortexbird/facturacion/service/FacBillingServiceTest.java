package  com.vortexbird.facturacion.service;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.vortexbird.facturacion.entity.service.BillingService;

/**
* @author Zathura Code Generator Version 22.08 http://zathuracode.org/
* www.zathuracode.org
* @generationDate 2022-08-23T11:10:20.573413
* 
*/

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class FacBillingServiceTest{

	@Autowired
	private BillingService billingService;

	@Test
	@DisplayName("findAll")
	void findAll(){
		//Arrange
		
		//Act
		
		//Assert
		assertNotNull(billingService);
    }

	@Test
	@DisplayName("save")
    void save() throws Exception {
    	//Arrange
		
		//Act
		
		//Assert
		assertNotNull(billingService);
    }


	@Test
	@DisplayName("delete")
	void delete() throws Exception {
		//Arrange
		
		//Act
		
		//Assert
		assertNotNull(billingService);
	}

    @Test
	@DisplayName("deleteById")
    void deleteById() throws Exception {
    	//Arrange
		
		//Act
		
		//Assert
    	assertNotNull(billingService);
    }

	@Test
	@DisplayName("update")
    void update() throws Exception {
    	//Arrange
		
		//Act
		
		//Assert
		assertNotNull(billingService);
    }

	@Test
	@DisplayName("findById")
    void findById() throws Exception {
    	//Arrange
		
		//Act
		
		//Assert
    	assertNotNull(billingService);
    }

	@Test
	@DisplayName("count")
    void count() throws Exception {
    	//Arrange
		
		//Act
		
		//Assert
    	assertNotNull(billingService);
    }

}
