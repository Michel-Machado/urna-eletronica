package com.grupo04pj01.urna.DTO;

import com.grupo04pj01.urna.models.enums.Role;

public record RegisterDTO(String login, String password, Role role) {
}
