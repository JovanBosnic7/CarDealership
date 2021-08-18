package rs.ac.uns.ftn.carDealership.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.carDealership.model.auth.AuthRequest;
import rs.ac.uns.ftn.carDealership.model.auth.AuthResponse;
import rs.ac.uns.ftn.carDealership.model.users.User;
import rs.ac.uns.ftn.carDealership.security.TokenUtils;
import rs.ac.uns.ftn.carDealership.service.IAuthenticationService;
import java.util.Collection;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtils tokenUtils;


    @Override
    public AuthResponse login(AuthRequest dto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User data = (User) authentication.getPrincipal();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
        String jwt = tokenUtils.generateToken(data.getUsername(), authorities);
        int expiresIn = tokenUtils.getExpiredIn();
        String role = null;
        for (GrantedAuthority grantedAuthority : authorities) {
            role = grantedAuthority.getAuthority();
            break;

        }
        AuthResponse responseDTO = new AuthResponse(data.getUserId(), data.getUsername(), jwt, role, expiresIn);
        return responseDTO;
    }
}
