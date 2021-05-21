package com.store.security.controller;

import com.store.security.dto.JwtDto;
import com.store.security.dto.LoginUser;
import com.store.security.dto.NewUser;
import com.store.security.entity.Rol;
import com.store.security.entity.User;
import com.store.security.enums.RolName;
import com.store.security.jwt.JwtProvider;
import com.store.security.service.RolService;
import com.store.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/new")
    public ResponseEntity<?> newUser(@RequestBody NewUser newUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ResponseEntity<>("Invalid fields.", HttpStatus.BAD_REQUEST);

        if (userService.existsByUsername(newUser.getUsername()))
            return new ResponseEntity<>("This username already exists.", HttpStatus.BAD_REQUEST);

        if (newUser.getUsername() == null || newUser.getUsername() == "")
            return new ResponseEntity<>("User must have username.", HttpStatus.BAD_REQUEST);

        if (newUser.getPassword() == null || newUser.getPassword() == "")
            return new ResponseEntity<>("User must have password.", HttpStatus.BAD_REQUEST);

        User user = new User(newUser.getUsername(), passwordEncoder.encode(newUser.getPassword()));
        Set<Rol> roles = new HashSet<>();
        //roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        roles.add(rolService.getByRolName(RolName.ROLE_USER).get());
        if (newUser.getRoles().contains("admin"))
            //roles.add(rolService.getByRolName(RolName.ROL_ADMIN).get());
            roles.add(rolService.getByRolName(RolName.ROLE_ADMIN).get());
        user.setRoles(roles);
        userService.save(user);

        return new ResponseEntity<>("User created!", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@RequestBody LoginUser loginUser, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return new ResponseEntity("Invalid fields.", HttpStatus.BAD_REQUEST);

        Authentication authentication = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(),
                                                                     loginUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());

        return new ResponseEntity(jwtDto, HttpStatus.OK);

    }
}
