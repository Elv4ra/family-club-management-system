package edu.kpi.iasa.mmsa.club.api.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtRequestDto {
    String login;
    String password;
}
