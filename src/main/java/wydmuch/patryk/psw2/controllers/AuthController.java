//package wydmuch.patryk.psw2.controllers;
//
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import wydmuch.patryk.psw2.configuration.JwtTokenUtil;
//import wydmuch.patryk.psw2.dto.UserDto;
//import wydmuch.patryk.psw2.exceptions.MyAuthenticationException;
//import wydmuch.patryk.psw2.exceptions.UserAlreadyExistsException;
//import wydmuch.patryk.psw2.model.JwtRequest;
//import wydmuch.patryk.psw2.model.JwtResponse;
//import wydmuch.patryk.psw2.services.JwtUserDetailsService;
//
//import javax.validation.Valid;
//
//
//@CrossOrigin("${allowedOrigin:*}")
//@RestController
//public class AuthController {
//
//    private JwtUserDetailsService userService;
//    private JwtTokenUtil jwtTokenUtil;
//
//
//    @Autowired
//    public AuthController(JwtUserDetailsService userService, JwtTokenUtil jwtTokenUtil) {
//        this.userService = userService;
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
//
//    @ApiOperation(value = "Register new user",
//                  response= UserDto.class)
//    @PostMapping("/registration")
//    public ResponseEntity<?> register(@RequestBody
//                                      @ApiParam(required = true,
//                                                defaultValue = "{\"email\" : \"jan@kowalski.pl\"}",
//                                                value = "JSON containing email and password")
//                                      @Valid UserDto user) throws UserAlreadyExistsException {
//
//        userService.registerNewUser(user);
//        return new ResponseEntity<>(user, HttpStatus.CREATED);
//    }
//
//    @ApiOperation(value = "Login a user",
//                  response = JwtResponse.class)
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody
//                                   @ApiParam(required = true,
//                                             defaultValue = "dfgdfsg",
//                                             value = "JSON containing email and password")
//                                           JwtRequest authenticationRequest) throws MyAuthenticationException {
//
//        userService.authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
//        final UserDetails userDetails = userService
//                .loadUserByUsername(authenticationRequest.getEmail());
//        final String token = jwtTokenUtil.generateToken(userDetails);
//        return new ResponseEntity<>(new JwtResponse(token), HttpStatus.OK);
//
//    }
//
//}
//
