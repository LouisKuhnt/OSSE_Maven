package de.hfu.residents;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class BaseTest 
{
	private List<Resident> residents = new ArrayList<Resident>();
	private BaseResidentService baseResidentService = new BaseResidentService();
	
	@Before
	public void setTestResidents() {
		residents.add(new Resident("Helga", "foo", "Straße 1", "City1", null));
		residents.add(new Resident("Hubert", "coo", "Straße 2", "City17", null));
		residents.add(new Resident("Jochen", "eoo", "Straße 3", "City16", null));
		residents.add(new Resident("Detlef", "goo", "Straße 4", "City14", null));
		residents.add(new Resident("Ulla", "hoo", "Straße 5", "City17", null));
		residents.add(new Resident("Olla", "joo", "Straße 6", "City1", null));
		
		ResidentRepositoryStub resiStub = new ResidentRepositoryStub(residents);
		baseResidentService.setResidentRepository(resiStub);
	}
	
    @Test
    public void TestUniqueResidentRight() throws ResidentServiceException
    {
       assertEquals(residents.get(0), baseResidentService.getUniqueResident(residents.get(0)));
    }
    
    @Test(expected=ResidentServiceException.class, timeout=1000)
    public void TestUniqueResidentError() throws ResidentServiceException
    {
    	assertEquals(residents.get(1), baseResidentService.getUniqueResident(new Resident("H*", "c*",null, null, null)));
    }
    
    @Test(expected=ResidentServiceException.class, timeout=1000)
    public void TestUniqueResidentNotUnique() throws ResidentServiceException
    {
       assertEquals(residents.get(0), baseResidentService.getUniqueResident(new Resident(null, null,null, "City1", null)));
    }
    
    @Test
    public void TestGetFilteredResidentCompletlyEquals() throws ResidentServiceException
    {
    	List<Resident> residentTest1 = baseResidentService.getFilteredResidentsList(residents.get(0));
    	for (Resident re : residentTest1) {
    		assertEquals(residents.get(0).getCity(), re.getCity());
    		assertEquals(residents.get(0).getFamilyName(), re.getFamilyName());
    		assertEquals(residents.get(0).getGivenName(), re.getGivenName());
    	}
    }
    
    @Test
    public void TestGetFilteredResidentWildcard() throws ResidentServiceException
    {
    	List<Resident> residentTest = baseResidentService.getFilteredResidentsList(new Resident("J*", null,null, null,null));
    	for (Resident re : residentTest) {
    		assertEquals(residents.get(2).getCity(), re.getCity());
    		assertEquals(residents.get(2).getFamilyName(), re.getFamilyName());
    		assertEquals(residents.get(2).getGivenName(), re.getGivenName());
    	}
    }
    
    @Test
    public void TestGetFilteredResidentNothing() throws ResidentServiceException
    {
    	List<Resident> residentTest = baseResidentService.getFilteredResidentsList(new Resident("Z*", null,null, null,null));
    	for (Resident re : residentTest) {
    		fail("Erwartete Ausnahme wurde nicht geworfen");
    	}
    }
}
