package org.godseop.apple.rest.github;

import org.springframework.http.HttpMethod;

public enum GitHubApi {

    GET_SINGLE_USER("https://api.github.com/users/{username}", HttpMethod.GET),
    UPDATE_USER("https://api.github.com/users/", HttpMethod.PATCH),

    ;


    public final String URL;
    public final HttpMethod METHOD;

    GitHubApi(String url, HttpMethod method) {
        this.URL = url;
        this.METHOD = method;
    }

}
