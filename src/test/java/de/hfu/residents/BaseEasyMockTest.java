package de.hfu.residents;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.fail;

import org.junit.Test;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class BaseEasyMockTest {

	
	@Test 
	public void TestUniqueResidentsMock() throws ResidentServiceException {
		Resident expectedResident = new Resident("Hubert", "coo", "Straße1", "City1", null);
		Resident zurückgebendeResident = new Resident("Hubert", "coo", "Straße1", "City1", null);
		
		BaseResidentService baseResidentServiceMock = createMock(BaseResidentService.class);
		expect(baseResidentServiceMock.getUniqueResident(expectedResident)).andReturn(zurückgebendeResident);
		expect(baseResidentServiceMock.getUniqueResident(zurückgebendeResident)).andReturn(expectedResident);
		
		replay(baseResidentServiceMock);
	}
}
