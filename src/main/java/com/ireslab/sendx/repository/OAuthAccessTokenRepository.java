package com.ireslab.sendx.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.ireslab.sendx.entity.OAuthAccessToken;

/**
 * @author Nitin
 *
 */
public interface OAuthAccessTokenRepository extends CrudRepository<OAuthAccessToken, Serializable> {

	public OAuthAccessToken findByUserName(String userName);

}
