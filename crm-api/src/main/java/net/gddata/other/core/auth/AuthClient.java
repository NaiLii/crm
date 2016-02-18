package net.gddata.other.core.auth;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthClient implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String username;

    private String password;

    private String token;


    private String role;

}
