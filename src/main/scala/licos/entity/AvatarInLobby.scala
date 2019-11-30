package licos.entity

import java.sql.Timestamp
import java.util.UUID

import licos.knowledge.Architecture

sealed abstract class AvatarInLobby()

final case class PlayerInLobby(
    id:           Long,
    token:        UUID,
    createdAt:    Timestamp,
    updatedAt:    Timestamp,
    name:         String,
    image:        String,
    isPrimary:    Boolean,
    architecture: Architecture
) extends AvatarInLobby() {}

final case class OnymousAudienceInLobby(
    id:        Long,
    token:     UUID,
    createdAt: Timestamp,
    updatedAt: Timestamp,
    name:      String,
    image:     String,
    isPrimary: Boolean
) extends AvatarInLobby() {}

final case class AnonymousAudienceInLobby() extends AvatarInLobby()
