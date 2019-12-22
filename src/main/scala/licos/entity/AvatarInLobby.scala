package licos.entity

import java.net.URL
import java.time.OffsetDateTime
import java.util.UUID

import licos.knowledge.Architecture

sealed abstract class AvatarInLobby()

final case class PlayerInLobby(
    id:           Long,
    token:        UUID,
    createdAt:    OffsetDateTime,
    updatedAt:    OffsetDateTime,
    name:         String,
    image:        URL,
    isPrimary:    Boolean,
    architecture: Architecture
) extends AvatarInLobby()

final case class OnymousAudienceInLobby(
    id:        Long,
    token:     UUID,
    createdAt: OffsetDateTime,
    updatedAt: OffsetDateTime,
    name:      String,
    image:     URL,
    isPrimary: Boolean
) extends AvatarInLobby()

final case class AnonymousAudienceInLobby() extends AvatarInLobby()
