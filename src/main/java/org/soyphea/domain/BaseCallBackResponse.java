package org.soyphea.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BaseCallBackResponse {

    @JsonProperty("callback_id")
    private String callBackId;
}
