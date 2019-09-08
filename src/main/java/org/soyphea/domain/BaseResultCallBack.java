package org.soyphea.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResultCallBack<T> {

    String callBackId;
    String status;
    T  result;


}
