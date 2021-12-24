package edu.kpi.iasa.mmsa.club.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegistrationDto {
    private String login;
    private String password;
    private String name;
    private String alias;
    private String phone;
}
