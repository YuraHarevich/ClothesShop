package com.Harevich.core.service;


import com.Harevich.core.dto.UserLoginRecord;
import com.Harevich.core.dto.UserRegistrationRecord;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class KeycloakUserService{

    @Value("${keycloak.realm}")
    private String realm;
    private Keycloak keycloak;
    @Value("${keycloak.adminClientId}")
    private String userClientId;
    @Value("${keycloak.urls.auth}")
    private String serverUrl;

    @Value("${keycloak.adminClientSecret}")
    private String adminClientSecret;
    public KeycloakUserService(Keycloak keycloak) {
        this.keycloak = keycloak;
    }


    public UserRegistrationRecord createUser(UserRegistrationRecord userRegistrationRecord) {

        UserRepresentation user=new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userRegistrationRecord.username());
        user.setEmail(userRegistrationRecord.email());
        user.setFirstName(userRegistrationRecord.firstName());
        user.setLastName(userRegistrationRecord.lastName());
        user.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation=new CredentialRepresentation();
        credentialRepresentation.setValue(userRegistrationRecord.password());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        List<CredentialRepresentation> list = new ArrayList<>();
        list.add(credentialRepresentation);
        user.setCredentials(list);

        UsersResource usersResource = getUsersResource();

        Response response = usersResource.create(user);

        if(Objects.equals(201,response.getStatus())){

            List<UserRepresentation> representationList = usersResource.searchByUsername(userRegistrationRecord.username(), true);
            if(!CollectionUtils.isEmpty(representationList)){
                UserRepresentation userRepresentation1 = representationList.stream().filter(userRepresentation -> Objects.equals(false, userRepresentation.isEmailVerified())).findFirst().orElse(null);
                assert userRepresentation1 != null;
                emailVerification(userRepresentation1.getId());
            }
            return  userRegistrationRecord;
        }
        return null;
    }

    private UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }


    public UserRepresentation getUserById(String userId) {


        return  getUsersResource().get(userId).toRepresentation();
    }

    public AccessTokenResponse getJwt(UserLoginRecord userLoginRecord) {
        Keycloak userKeycloak = KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm)
                .grantType(OAuth2Constants.PASSWORD)
                .clientId(userClientId)
                .clientSecret(adminClientSecret)
                .username(userLoginRecord.username())
                .password(userLoginRecord.password())
                .build();

        return userKeycloak.tokenManager().getAccessToken();
    }

    public void deleteUserById(String userId) {

        getUsersResource().delete(userId);
    }

    public void emailVerification(String userId){

        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }

    public UserResource getUserResource(String userId){
        UsersResource usersResource = getUsersResource();
        return usersResource.get(userId);
    }

}


