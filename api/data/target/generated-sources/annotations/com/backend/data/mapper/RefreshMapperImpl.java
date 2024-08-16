package com.backend.data.mapper;

import com.backend.data.models.RefreshTokenEntity;
import com.backend.domain.models.RefreshToken;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-16T17:25:15+0200",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class RefreshMapperImpl implements RefreshMapper {

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Override
    public RefreshToken toDomain(RefreshTokenEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setId( entity.getId() );
        refreshToken.setUser( userDetailsMapper.toDomain( entity.getUser() ) );
        refreshToken.setToken( entity.getToken() );
        refreshToken.setExpirationDate( entity.getExpirationDate() );

        return refreshToken;
    }

    @Override
    public RefreshTokenEntity toEntity(RefreshToken domain) {
        if ( domain == null ) {
            return null;
        }

        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();

        refreshTokenEntity.setId( domain.getId() );
        refreshTokenEntity.setUser( userDetailsMapper.toEntity( domain.getUser() ) );
        refreshTokenEntity.setToken( domain.getToken() );
        refreshTokenEntity.setExpirationDate( domain.getExpirationDate() );

        return refreshTokenEntity;
    }

    @Override
    public List<RefreshToken> toDomain(List<RefreshTokenEntity> dto) {
        if ( dto == null ) {
            return null;
        }

        List<RefreshToken> list = new ArrayList<RefreshToken>( dto.size() );
        for ( RefreshTokenEntity refreshTokenEntity : dto ) {
            list.add( toDomain( refreshTokenEntity ) );
        }

        return list;
    }

    @Override
    public List<RefreshTokenEntity> toEntity(List<RefreshToken> entity) {
        if ( entity == null ) {
            return null;
        }

        List<RefreshTokenEntity> list = new ArrayList<RefreshTokenEntity>( entity.size() );
        for ( RefreshToken refreshToken : entity ) {
            list.add( toEntity( refreshToken ) );
        }

        return list;
    }
}
