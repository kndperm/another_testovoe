package ru.knd.another_testovoe.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponse {
    private String status;
    private String description;
    private Object object;
}
