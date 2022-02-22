package com.coderhouse.model.request;

import lombok.*;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewCartRequest {

    @NonNull
    private String cartCode;
    @NonNull
    private String email;
    private String deliverAddress;

}
