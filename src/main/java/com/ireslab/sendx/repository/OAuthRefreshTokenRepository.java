package com.ireslab.sendx.repository;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import com.ireslab.sendx.entity.OAuthRefreshToken;

/**
 * @author Nitin
 *
 */
public interface OAuthRefreshTokenRepository extends CrudRepository<OAuthRefreshToken, Serializable> {

	public OAuthRefreshToken findByTokenId(String tokenId);

}
