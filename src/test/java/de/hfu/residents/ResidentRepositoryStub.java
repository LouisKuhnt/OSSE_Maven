package de.hfu.residents;

import java.util.List;
import de.hfu.residents.domain.Resident;
import de.hfu.residents.repository.ResidentRepository;

public class ResidentRepositoryStub implements ResidentRepository{
	
	List<Resident> resident;
	
	public ResidentRepositoryStub(List<Resident> residents) {
		this.resident = residents;
	}

	@Override
	public List<Resident> getResidents() {
		return resident;
	}
	
}
