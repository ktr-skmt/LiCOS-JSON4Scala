package licos.entity

import java.net.URL
import java.time.OffsetDateTime
import java.util.UUID

import licos.knowledge.Architecture

final case class ServerOnly(
    id:         Long,
    token:      UUID,
    createdAt:  OffsetDateTime,
    updatedAt:  OffsetDateTime,
    name:       String,
    image:      URL,
    isPrimary:  Boolean,
    playerType: Architecture,
    isHost:     Boolean
)
