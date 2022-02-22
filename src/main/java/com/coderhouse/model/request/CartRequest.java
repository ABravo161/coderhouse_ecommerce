package com.coderhouse.model.request;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartRequest {
    @NonNull
    private String email;
    @NonNull
    private String productCode;
    @NonNull
    private int quantity;

}
