package org.soyphea.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
@ToString
public class StackOverflowTag {

    @JsonProperty("user_id")
    String userId;
    String name;
    int count;

}
