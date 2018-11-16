package com.ireslab.sendx.repository;

import org.springframework.data.repository.CrudRepository;

import com.ireslab.sendx.entity.ClientCredential;

/**
 * @author Nitin
 *
 */
public interface ClientCredentialRepository extends CrudRepository<ClientCredential, Integer> {
	
	/**
	 * @param clientApiKey
	 * @return
	 */
	public ClientCredential findByClientId(String clientId);
}
