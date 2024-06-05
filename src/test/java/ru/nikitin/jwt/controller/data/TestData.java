package ru.nikitin.jwt.controller.data;

import ru.nikitin.jwt.model.Role;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.model.dto.TokenCredentialRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestData {

    private static String admin = "admin";
    private static String user = "user";
    private static Map<String, User> repos;

    static {
        repos = new HashMap<>();
        repos.put(admin, new User(1L, "admin",
                "$2y$10$ownGmACmEFGsEoRzDkFa8.AiWavQNH92daelZDMj1WIt.sNn5vSPe",
                List.of(Role.ADMIN, Role.USER)))/*secret*/;
        repos.put(user, new User(2L, "user",
                "$2a$10$XfnTCU7v.n49cI63NHeMNeAwyf7mWLcFXP9MevMXOjPBFOm9Sp5Mq",
                List.of(Role.USER))/*pass*/);
    }

    public static User getAdmin() {
        return repos.get(admin);
    }

    public static User getUser() {
        return repos.get(user);
    }

    public static String getAdminJson() {
        return JsonConverter.convertTo(getAdmin());
    }

    public static String getUserJson() {
        return JsonConverter.convertTo(getUser());
    }

    public static String convertTo(Object user) {
        return JsonConverter.convertTo(user);
    }

    public static <T> T convertFrom(String user, Class<T> clazz) {
        return JsonConverter.convertFrom(user, clazz);
    }

    public static TokenCredentialRequest getTokenCreds(User user) {
        return new TokenCredentialRequest(user.getUsername(), user.getPassword());
    }
}
