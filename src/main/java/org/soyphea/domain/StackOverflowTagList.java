package org.soyphea.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
public class StackOverflowTagList {

    @JsonProperty("items")
    List<StackOverflowTag> stackOverflowTags;

    public StackOverflowTagList() {
        stackOverflowTags = new ArrayList<>();
    }
}
