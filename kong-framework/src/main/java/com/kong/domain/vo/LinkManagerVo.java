package com.kong.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkManagerVo {
    private Long id;
    private String name;
    private String status;
    private String address;
    private String description;
    private String logo;

}
