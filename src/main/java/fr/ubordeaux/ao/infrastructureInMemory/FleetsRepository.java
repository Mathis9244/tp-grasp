package fr.ubordeaux.ao.infrastructureInMemory;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import fr.ubordeaux.ao.domain.model.Company;
import fr.ubordeaux.ao.domain.model.Fleets;
import fr.ubordeaux.ao.domain.model.Plane;

public class FleetsRepository implements Fleets {

	private Map<Company, Set<Plane>> planesByCompany = new HashMap<>();
	private Map<Plane, Company> ownerByPlane = new HashMap<>();

	@Override
	public Set<Plane> planes(Company c) {
		Set<Plane> planes = planesByCompany.get(c);
		if (planes == null) {
			return Collections.emptySet();
		}
		return Collections.unmodifiableSet(planes);
	}

	@Override
	public void setCompagny(Plane p, Company company) {
		Company old = ownerByPlane.get(p);
		if (old != null) {
			Set<Plane> oldPlanes = planesByCompany.get(old);
			if (oldPlanes != null) {
				oldPlanes.remove(p);
			}
		}

		if (!planesByCompany.containsKey(company)) {
			planesByCompany.put(company, new HashSet<>());
		}
		planesByCompany.get(company).add(p);
		ownerByPlane.put(p, company);
	}

	@Override
	public Company owner(Plane p) {
		return ownerByPlane.get(p);
	}
}
