package com.coderhouse.model.request;

import lombok.*;


@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String email;
    private String phone;
}
