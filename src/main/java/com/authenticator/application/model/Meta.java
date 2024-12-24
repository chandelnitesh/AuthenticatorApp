package com.authenticator.application.model;

import lombok.*;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Meta {

    private Boolean success = true;
    private String messageDescription;
    private final String transactionId = "TXN-" + UUID.randomUUID().toString();
    private String unixTimestamp = String.valueOf(System.currentTimeMillis());
}
