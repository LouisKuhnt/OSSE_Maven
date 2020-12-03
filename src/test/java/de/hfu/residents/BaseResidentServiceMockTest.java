package de.hfu.residents;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;
import de.hfu.residents.service.BaseResidentService;
import de.hfu.residents.service.ResidentServiceException;

public class BaseResidentServiceMockTest {
	ResidentRepository residentRepositoryMock = createMock(ResidentRepository.class);
	List<Resident> residentList = new ArrayList<Resident>();
	BaseResidentService baseResidentService = new BaseResidentService();
	
	@Before
	public void setTestList() {
		residentList.add(new Resident("Hubert", "coo", "Straße1", "City1", null));
		residentList.add(new Resident("Helga", "ceo", "Straße34", "City2", null));
		residentList.add(new Resident("Günter", "foo", "Straße21", "City12", null));
		residentList.add(new Resident("Josef", "hoo", "Straße2", "City12", null));
		residentList.add(new Resident("Bernd", "boo", "Straße76", "City1", null));
		
		baseResidentService.setResidentRepository(residentRepositoryMock);
	}
	
	@Test 
	public void TestUniqueResidentsRightMock() throws ResidentServiceException {
		expect(residentRepositoryMock.getResidents()).andReturn(residentList).times(5);
		replay(residentRepositoryMock);
		
		Resident resident1 = new Resident("Hubert", "coo", "Straße1", "City1", null);
		assertThat(baseResidentService.getUniqueResident(resident1).getGivenName(), equalTo(resident1.getGivenName()));
		assertThat(baseResidentService.getUniqueResident(resident1).getFamilyName(), equalTo(resident1.getFamilyName()));
		assertThat(baseResidentService.getUniqueResident(resident1).getStreet(), equalTo(resident1.getStreet()));
		assertThat(baseResidentService.getUniqueResident(resident1).getCity(), equalTo(resident1.getCity()));
		assertThat(baseResidentService.getUniqueResident(resident1).getDateOfBirth(), equalTo(resident1.getDateOfBirth()));
		
		verify(residentRepositoryMock);
	}
	
	@Test(expected=ResidentServiceException.class, timeout=1000)
    public void TestUniqueResidentNotUniqueMock() throws ResidentServiceException
    {
	   expect(residentRepositoryMock.getResidents()).andReturn(residentList).times(1);
	   replay(residentRepositoryMock);
       baseResidentService.getUniqueResident(new Resident(null, null,null, "City1", null));
       
       verify(residentRepositoryMock);
    }
	
	@Test(expected=ResidentServiceException.class, timeout=1000)
    public void TestUniqueResidentWildcardErrorMock() throws ResidentServiceException
    {
		expect(residentRepositoryMock.getResidents()).andReturn(residentList).times(1);
		replay(residentRepositoryMock);
    	baseResidentService.getUniqueResident(new Resident("H*", "c*",null, null, null));
    	
    	verify(residentRepositoryMock);
    }
	
	@Test
    public void TestGetFilteredResidentCompletlyEquals() throws ResidentServiceException
    {
		expect(residentRepositoryMock.getResidents()).andReturn(residentList).times(1);
		replay(residentRepositoryMock);
    	assertThat(baseResidentService.getFilteredResidentsList(new Resident("Helga", "ceo", "Straße34", "City2", null)).size(), equalTo(1));
    	
    	verify(residentRepositoryMock);
    }
    
    @Test
    public void TestGetFilteredResidentWildcard() throws ResidentServiceException
    {
    	expect(residentRepositoryMock.getResidents()).andReturn(residentList).times(1);
		replay(residentRepositoryMock);
		
    	assertThat(baseResidentService.getFilteredResidentsList(new Resident("H*", "", "", "", null)).size(), equalTo(2));
    	
    	verify(residentRepositoryMock);
    }
    
    @Test
    public void TestGetFilteredResidentNothing() throws ResidentServiceException
    {
    	expect(residentRepositoryMock.getResidents()).andReturn(residentList).times(1);
		replay(residentRepositoryMock);
		
    	assertThat(baseResidentService.getFilteredResidentsList(new Resident("Z*", null,null, null,null)).size(), equalTo(0));
    	
    	verify(residentRepositoryMock);
    }
}
