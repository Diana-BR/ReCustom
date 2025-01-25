package com.challenge.recustom.controller;

import com.challenge.recustom.model.User;
import com.challenge.recustom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.Valid;

@Slf4j
@Validated
@RestController
@CrossOrigin(origins = "ChallengeController",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.OPTIONS},
        maxAge = 3600)
@RequestMapping("v1/ReCustom")
public class ChallengeController {

    @Autowired
    private UserService userService;

    @GetMapping("/getUser/{email}")
    @Operation(summary = "Get user details from ReCustom API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned."),
            @ApiResponse(responseCode = "204", description = "No user found."),
            @ApiResponse(responseCode = "400", description = "Invalid input or parameters missing."),
            @ApiResponse(responseCode = "404", description = "The given URL / Service endpoint cannot be found."),
            @ApiResponse(responseCode = "500", description = "There is a problem internally to process the request.")})
    public ResponseEntity getUser(@PathVariable("email") String email) {
        log.info("getUser: Get user details from e-mail: " + email);

        ResponseEntity responseEntity = userService.getUser(email);

        return responseEntity;
    }

    @GetMapping("/getUsers")
    @Operation(summary = "Get user details from ReCustom API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned."),
            @ApiResponse(responseCode = "204", description = "No user found."),
            @ApiResponse(responseCode = "400", description = "Invalid input or parameters missing."),
            @ApiResponse(responseCode = "404", description = "The given URL / Service endpoint cannot be found."),
            @ApiResponse(responseCode = "500", description = "There is a problem internally to process the request.")})
    public ResponseEntity getUsers() {
        log.info("getUsers: Get all users details: ");

        ResponseEntity responseEntity = userService.getUsers();

        return responseEntity;
    }

    @PostMapping("/createUser")
    @Operation(summary = "Create a user from ReCustom API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully returned."),
            @ApiResponse(responseCode = "400", description = "Invalid input or parameters missing."),
            @ApiResponse(responseCode = "404", description = "The given URL / Service endpoint cannot be found."),
            @ApiResponse(responseCode = "500", description = "There is a problem internally to process the request.")})
    public ResponseEntity createUser(@RequestBody @Valid User user) {
        log.info("createUser: " + user);

        ResponseEntity responseEntity = userService.save(user);

        return responseEntity;
    }

    @PutMapping("/updateUser")
    @Operation(summary = "Update a user from ReCustom API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully returned."),
            @ApiResponse(responseCode = "204", description = "No users found."),
            @ApiResponse(responseCode = "400", description = "Invalid input or parameters missing."),
            @ApiResponse(responseCode = "404", description = "The given URL / Service endpoint cannot be found."),
            @ApiResponse(responseCode = "500", description = "There is a problem internally to process the request.")})
    public ResponseEntity updateUser(@RequestBody User user) {
        log.info("updateUser: " + user);

        ResponseEntity responseEntity = userService.update(user);

        return responseEntity;
    }

    @DeleteMapping("/deleteUser/{email}")
    @Operation(summary = "Delete a user from ReCustom API")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Successfully returned."),
            @ApiResponse(responseCode = "204", description = "No users found."),
            @ApiResponse(responseCode = "400", description = "Invalid input or parameters missing."),
            @ApiResponse(responseCode = "404", description = "The given URL / Service endpoint cannot be found."),
            @ApiResponse(responseCode = "500", description = "There is a problem internally to process the request.")})
    public ResponseEntity<String> deleteUser(@PathVariable("email") String email){
        log.info("deleteUser: " + email);

        ResponseEntity responseEntity = userService.delete(email);

        return responseEntity;
    }
}
