package org.acme.keycloak.admin.client;

import org.jboss.logging.Logger;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Arrays;

import java.util.List;

@Path("/api/admin")
public class RolesResource {

    private static final Logger LOG = Logger.getLogger(RolesResource.class);

    @Inject
    Keycloak keycloak;

    @GET
    @Path("/roles")
    public List<RoleRepresentation> getRoles() {
        return keycloak.realm("pos-virtual").roles().list();
    }

    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(User2Create user) {

        UserRepresentation u = new UserRepresentation();
        u.setEnabled(user.getEnabled());
        u.setEmail(user.getEmail());
        u.setEmailVerified(true);
        u.setFirstName(user.getFirstName());
        u.setLastName(user.getLastName());
        u.setUsername(user.getUsername());
        RealmResource realmResource = keycloak.realm("pos-virtual");
        UsersResource usersResource = realmResource.users();
        Response response = null;
        try {
            response = usersResource.create(u);
            LOG.info("status: " + response.getStatus());
            LOG.info("statusInfo: " + response.getStatusInfo());
            String userId = CreatedResponseUtil.getCreatedId(response);
            LOG.info("userId: " + userId);
            CredentialRepresentation pass = new CredentialRepresentation();
            pass.setTemporary(false);
            pass.setType(CredentialRepresentation.PASSWORD);
            pass.setValue(user.getPassword());
            UserResource userResource = usersResource.get(userId);
            userResource.resetPassword(pass);
            RoleRepresentation userRol = realmResource.roles()
                    .get("user").toRepresentation();
            userResource.roles().realmLevel() //
                    .add(Arrays.asList(userRol));
            return Response.ok(response.getEntity()).build();
        } catch (Exception ex) {
            LOG.errorf("Error creando usuario", ex);
            return Response.status(response.getStatus()).entity(ex).build();
        }

        
    }

}