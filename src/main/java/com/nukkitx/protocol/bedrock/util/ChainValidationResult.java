package com.nukkitx.protocol.bedrock.util;

import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.jose4j.json.JsonUtil;
import org.jose4j.lang.JoseException;

public final class ChainValidationResult {
   private final boolean signed;
   private final Map<String, Object> parsedPayload;
   private IdentityClaims identityClaims;

   public ChainValidationResult(boolean signed, String rawPayload) throws JoseException {
      this(signed, JsonUtil.parseJson(rawPayload));
   }

   public ChainValidationResult(boolean signed, Map<String, Object> parsedPayload) {
      this.signed = signed;
      this.parsedPayload = (Map)Objects.requireNonNull(parsedPayload);
   }

   public boolean signed() {
      return this.signed;
   }

   public Map<String, Object> rawIdentityClaims() {
      return new HashMap(this.parsedPayload);
   }

   public IdentityClaims identityClaims() throws IllegalStateException {
      if (this.identityClaims == null) {
         String identityPublicKey = (String)JsonUtils.childAsType(this.parsedPayload, "identityPublicKey", String.class);
         Map<?, ?> extraData = (Map)JsonUtils.childAsType(this.parsedPayload, "extraData", Map.class);
         String displayName = (String)JsonUtils.childAsType(extraData, "displayName", String.class);
         String identityString = (String)JsonUtils.childAsType(extraData, "identity", String.class);
         String xuid = (String)JsonUtils.childAsType(extraData, "XUID", String.class);
         Object titleId = extraData.get("titleId");

         UUID identity;
         try {
            identity = UUID.fromString(identityString);
         } catch (Exception var9) {
            throw new IllegalStateException("identity node is an invalid UUID");
         }

         this.identityClaims = new IdentityClaims(new IdentityData(displayName, identity, xuid, (String)titleId), identityPublicKey);
      }

      return this.identityClaims;
   }

   public static final class IdentityClaims {
      public final IdentityData extraData;
      public final String identityPublicKey;
      private PublicKey parsedIdentityPublicKey;

      private IdentityClaims(IdentityData extraData, String identityPublicKey) {
         this.extraData = extraData;
         this.identityPublicKey = identityPublicKey;
      }

      public PublicKey parsedIdentityPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
         if (this.parsedIdentityPublicKey == null) {
            this.parsedIdentityPublicKey = EncryptionUtils.parseKey(this.identityPublicKey);
         }

         return this.parsedIdentityPublicKey;
      }
   }

   public static final class IdentityData {
      public final String displayName;
      public final UUID identity;
      public final String xuid;
      public final @Nullable String titleId;

      private IdentityData(String displayName, UUID identity, String xuid, @Nullable String titleId) {
         this.displayName = displayName;
         this.identity = identity;
         this.xuid = xuid;
         this.titleId = titleId;
      }
   }
}
