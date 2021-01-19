// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
package com.rbs.oidcapiclient.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * A {@link Converter} that takes a {@link Jwt} and converts it into a {@link BearerTokenAuthentication}.
 */
public class AADJwtBearerTokenAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private static final String DEFAULT_AUTHORITY_PREFIX = "SCOPE_";

    private Converter<Jwt, Collection<GrantedAuthority>> converter
        = new JwtGrantedAuthoritiesConverter();

    public AADJwtBearerTokenAuthenticationConverter() {
    }

    public AADJwtBearerTokenAuthenticationConverter(String authoritiesClaimName) {
        this(authoritiesClaimName, DEFAULT_AUTHORITY_PREFIX);
    }

    public AADJwtBearerTokenAuthenticationConverter(String authoritiesClaimName, String authorityPrefix) {
        Assert.notNull(authoritiesClaimName, "authoritiesClaimName cannot be null");
        Assert.notNull(authorityPrefix, "authorityPrefix cannot be null");
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(authoritiesClaimName);
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(authorityPrefix);
        this.converter = jwtGrantedAuthoritiesConverter;
    }

    protected Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        return this.converter.convert(jwt);
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        OAuth2AccessToken accessToken = new OAuth2AccessToken(
            OAuth2AccessToken.TokenType.BEARER, jwt.getTokenValue(), jwt.getIssuedAt(), jwt.getExpiresAt());
        Collection<GrantedAuthority> authorities = extractAuthorities(jwt);
        AADOAuth2AuthenticatedPrincipal principal = new AADOAuth2AuthenticatedPrincipal(
            jwt.getHeaders(), jwt.getClaims(), authorities, jwt.getTokenValue());
        return new BearerTokenAuthentication(principal, accessToken, authorities);
    }
    
    

    public void setJwtGrantedAuthoritiesConverter(
        Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter) {
        this.converter = jwtGrantedAuthoritiesConverter;
    }
    
    
    
    /*  private static final Collection<String> GROUPS_AUTHORITIES_CLAIM_NAMES =
			Arrays.asList("groups");

private Collection<String> getGroupsAsAuthorities(Jwt jwt) {
		String claimName = getAuthoritiesClaimName(jwt);

		if (claimName == null) {
			return Collections.emptyList();
		}

		Object authorities = jwt.getClaim(claimName);
		if (authorities instanceof String) {
			if (StringUtils.hasText((String) authorities)) {
				return Arrays.asList(((String) authorities).split(" "));
			} else {
				return Collections.emptyList();
			}
		} else if (authorities instanceof Collection) {
			return (Collection<String>) authorities;
		}

		return Collections.emptyList();
	}
	
	private String getAuthoritiesClaimName(Jwt jwt) {
		
		 // if (this.authoritiesClaimName != null) { return this.authoritiesClaimName; }
		 
		for (String claimName : GROUPS_AUTHORITIES_CLAIM_NAMES) {
			//if (jwt.containsClaim(claimName)) {
				return claimName;
			//}
		}
		return null;
	}
	
	private Boolean containsClaim(String claim) {
		Assert.notNull(claim, "claim cannot be null");
		return getClaims().containsKey(claim);
	}*/
}
