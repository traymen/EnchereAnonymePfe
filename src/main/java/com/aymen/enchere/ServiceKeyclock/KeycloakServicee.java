package com.aymen.enchere.ServiceKeyclock;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakServicee {
    /*
    @Autowired
    private Keycloak keycloak;

    public UserRepresentation getUserDetails(String userId) {
        return keycloak.realm("enchere").users().get(userId).toRepresentation();
    }

    public List<UserRepresentation> getUsersDetails(List<String> userIds) {
        return userIds.stream()
                .map(this::getUserDetails)
                .collect(Collectors.toList());
    }
     */
}
