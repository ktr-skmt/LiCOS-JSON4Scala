package licos.entity

import java.net.URL
import java.sql.Timestamp
import java.util.UUID

import licos.knowledge.Architecture

final case class ServerOnly(
    id:           Long,
    token:        UUID,
    createdAt:    Timestamp,
    updatedAt:    Timestamp,
    name:         String,
    image:        URL,
    isPrimary:    Boolean,
    architecture: Architecture,
    isHost:       Boolean
)
