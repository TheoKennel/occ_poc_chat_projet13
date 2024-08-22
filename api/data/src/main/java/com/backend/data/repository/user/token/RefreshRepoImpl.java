package com.backend.data.repository.user.token;


import com.backend.data.mapper.RefreshMapper;
import com.backend.domain.models.RefreshToken;
import com.backend.data.models.RefreshTokenEntity;
import com.backend.domain.repository.IRefreshTokenRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Manages operations for refresh token data.
 */
@Repository
public class RefreshRepoImpl implements IRefreshTokenRepository {

    private final TokenJpaRepo repository;
    private final RefreshMapper refreshMapper;

    public RefreshRepoImpl(TokenJpaRepo repository, RefreshMapper refreshMapper) {
        this.repository = repository;
        this.refreshMapper = refreshMapper;
    }

    /**
     * Finds a refresh token by its token value.
     * @param token the token value to search for
     * @return an {@link Optional} containing the found refresh token
     */
    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return repository.findByToken(token).map(refreshMapper::toDomain);
    }

    /**
     * Deletes all refresh tokens associated with a user ID.
     * @param id the ID of the user
     */
    @Override
    public void deleteByUserId(Long id) {
        repository.deleteByUserId(id);
    }


    /**
     * Deletes a specific refresh token.
     * @param refreshToken the refresh token to delete
     */
    @Override
    public void delete(RefreshToken refreshToken) {
        RefreshTokenEntity refreshTokenEntity = refreshMapper.toEntity(refreshToken);
        repository.delete(refreshTokenEntity);
    }

    /**
     * Saves a refresh token and returns its token value.
     * @param refreshToken the refresh token to save
     * @return the token value of the saved refresh token
     */
    @Override
    public String save(RefreshToken refreshToken) {
        RefreshTokenEntity refreshTokenEntity = refreshMapper.toEntity(refreshToken);
        System.out.println("Refresh token entity" +  refreshTokenEntity.toString());
        repository.save(refreshTokenEntity);
        return refreshTokenEntity.getToken();
    }
}
