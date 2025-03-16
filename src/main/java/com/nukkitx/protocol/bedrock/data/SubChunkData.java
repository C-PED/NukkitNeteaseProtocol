package com.nukkitx.protocol.bedrock.data;

import io.netty.buffer.ByteBuf;
import io.netty.util.AbstractReferenceCounted;
import org.cloudburstmc.math.vector.Vector3i;

public class SubChunkData extends AbstractReferenceCounted {
   private Vector3i position;
   private ByteBuf data;
   private SubChunkRequestResult result;
   private HeightMapDataType heightMapType;
   private ByteBuf heightMapData;
   private boolean cacheEnabled;
   private long blobId;

   public SubChunkData touch(Object o) {
      if (this.data != null) {
         this.data.touch(o);
      }

      if (this.heightMapData != null) {
         this.heightMapData.touch(o);
      }

      return this;
   }

   protected void deallocate() {
      if (this.data != null) {
         this.data.release();
      }

      if (this.heightMapData != null) {
         this.heightMapData.release();
      }

   }

   public Vector3i getPosition() {
      return this.position;
   }

   public ByteBuf getData() {
      return this.data;
   }

   public SubChunkRequestResult getResult() {
      return this.result;
   }

   public HeightMapDataType getHeightMapType() {
      return this.heightMapType;
   }

   public ByteBuf getHeightMapData() {
      return this.heightMapData;
   }

   public boolean isCacheEnabled() {
      return this.cacheEnabled;
   }

   public long getBlobId() {
      return this.blobId;
   }

   public void setPosition(Vector3i position) {
      this.position = position;
   }

   public void setData(ByteBuf data) {
      this.data = data;
   }

   public void setResult(SubChunkRequestResult result) {
      this.result = result;
   }

   public void setHeightMapType(HeightMapDataType heightMapType) {
      this.heightMapType = heightMapType;
   }

   public void setHeightMapData(ByteBuf heightMapData) {
      this.heightMapData = heightMapData;
   }

   public void setCacheEnabled(boolean cacheEnabled) {
      this.cacheEnabled = cacheEnabled;
   }

   public void setBlobId(long blobId) {
      this.blobId = blobId;
   }

   public String toString() {
      return "SubChunkData(position=" + this.getPosition() + ", data=" + this.getData() + ", result=" + this.getResult() + ", heightMapType=" + this.getHeightMapType() + ", heightMapData=" + this.getHeightMapData() + ", cacheEnabled=" + this.isCacheEnabled() + ", blobId=" + this.getBlobId() + ")";
   }

   public boolean equals(Object o) {
      if (o == this) {
         return true;
      } else if (!(o instanceof SubChunkData)) {
         return false;
      } else {
         SubChunkData other = (SubChunkData)o;
         if (!other.canEqual(this)) {
            return false;
         } else if (this.isCacheEnabled() != other.isCacheEnabled()) {
            return false;
         } else if (this.getBlobId() != other.getBlobId()) {
            return false;
         } else {
            Object this$position = this.getPosition();
            Object other$position = other.getPosition();
            if (this$position == null) {
               if (other$position != null) {
                  return false;
               }
            } else if (!this$position.equals(other$position)) {
               return false;
            }

            Object this$data = this.getData();
            Object other$data = other.getData();
            if (this$data == null) {
               if (other$data != null) {
                  return false;
               }
            } else if (!this$data.equals(other$data)) {
               return false;
            }

            Object this$result = this.getResult();
            Object other$result = other.getResult();
            if (this$result == null) {
               if (other$result != null) {
                  return false;
               }
            } else if (!this$result.equals(other$result)) {
               return false;
            }

            Object this$heightMapType = this.getHeightMapType();
            Object other$heightMapType = other.getHeightMapType();
            if (this$heightMapType == null) {
               if (other$heightMapType != null) {
                  return false;
               }
            } else if (!this$heightMapType.equals(other$heightMapType)) {
               return false;
            }

            Object this$heightMapData = this.getHeightMapData();
            Object other$heightMapData = other.getHeightMapData();
            if (this$heightMapData == null) {
               if (other$heightMapData != null) {
                  return false;
               }
            } else if (!this$heightMapData.equals(other$heightMapData)) {
               return false;
            }

            return true;
         }
      }
   }

   protected boolean canEqual(Object other) {
      return other instanceof SubChunkData;
   }

   public int hashCode() {
      int PRIME = 59;
      int result = 1;
      result = result * 59 + (this.isCacheEnabled() ? 79 : 97);
      long $blobId = this.getBlobId();
      result = result * 59 + (int)($blobId >>> 32 ^ $blobId);
      Object $position = this.getPosition();
      result = result * 59 + ($position == null ? 43 : $position.hashCode());
      Object $data = this.getData();
      result = result * 59 + ($data == null ? 43 : $data.hashCode());
      Object $result = this.getResult();
      result = result * 59 + ($result == null ? 43 : $result.hashCode());
      Object $heightMapType = this.getHeightMapType();
      result = result * 59 + ($heightMapType == null ? 43 : $heightMapType.hashCode());
      Object $heightMapData = this.getHeightMapData();
      result = result * 59 + ($heightMapData == null ? 43 : $heightMapData.hashCode());
      return result;
   }
}
