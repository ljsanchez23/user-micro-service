package com.emazon.UserMicroservice.domain.util;

import com.emazon.UserMicroservice.domain.model.Role;

public class DefaultRoles {

    public static final Long ADMIN_ID = 1L;
    public static final String ADMIN_ROLE_NAME = "ROLE_ADMIN";
    public static final String ADMIN_DESCRIPTION = "Administrator of the system.";

    public static final Long ASSISTANT_ID = 2L;
    public static final String ASSISTANT_ROLE_NAME = "ROLE_ASSISTANT";
    public static final String ASSISTANT_DESCRIPTION = "Warehouse assistant capable of managing the stock";

    public static final Long CLIENT_ID = 3L;
    public static final String CLIENT_ROLE_NAME = "ROLE_CLIENT";
    public static final String CLIENT_DESCRIPTION = "Client, give us your money";



    public static final Role ADMIN = new Role(ADMIN_ID, ADMIN_ROLE_NAME, ADMIN_DESCRIPTION);
    public static final Role ASSISTANT = new Role (ASSISTANT_ID, ASSISTANT_ROLE_NAME, ASSISTANT_DESCRIPTION);
    public static final Role CLIENT = new Role(CLIENT_ID, CLIENT_ROLE_NAME, CLIENT_DESCRIPTION);

}
