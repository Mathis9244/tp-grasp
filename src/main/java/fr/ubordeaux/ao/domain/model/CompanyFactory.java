package fr.ubordeaux.ao.domain.model;

import java.util.HashSet;
import java.util.Set;

import fr.ubordeaux.ao.domain.exception.EntryException;

public class CompanyFactory {

	private Set<String> names = new HashSet<>();

	public Company createCompany(String name) {
		if (!names.add(name)) {
			throw new EntryException("Company name already exists: " + name);
		}
		return new Company(name);
	}
}
