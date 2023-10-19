package dev.tber.security.jwt.models.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ERole {
    USER,
    ADMIN,
    MODERATOR
}
