package org.com.sandes.model.dtos;

import org.com.sandes.model.UserRole;

public record RegisterDto(String login, String password, UserRole role) {
}
