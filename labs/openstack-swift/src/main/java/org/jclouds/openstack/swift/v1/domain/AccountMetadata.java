package org.jclouds.openstack.swift.v1.domain;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;

/**
 * @author Adrian Cole
 * @see <a href="http://docs.openstack.org/api/openstack-object-storage/1.0/content/retrieve-account-metadata.html">api doc</a>
 */
public class AccountMetadata {

   public static Builder builder() {
      return new Builder();
   }

   public Builder toBuilder() {
      return builder().fromAccountMetadata(this);
   }

   public static class Builder {
      protected int containerCount;
      protected long bytesUsed;

      /**
       * @see AccountMetadata#getContainerCount()
       */
      public Builder containerCount(int containerCount) {
         this.containerCount = containerCount;
         return this;
      }

      /**
       * @see AccountMetadata#getBytesUsed()
       */
      public Builder bytesUsed(long bytesUsed) {
         this.bytesUsed = bytesUsed;
         return this;
      }

      public AccountMetadata build() {
         return new AccountMetadata(containerCount, bytesUsed);
      }

      public Builder fromAccountMetadata(AccountMetadata from) {
         return containerCount(from.getContainerCount()).bytesUsed(from.getBytesUsed());
      }
   }

   protected AccountMetadata() {
      // we want serializers like Gson to work w/o using sun.misc.Unsafe,
      // prohibited in GAE. This also implies fields are not final.
      // see http://code.google.com/p/jclouds/issues/detail?id=925
   }
  
   protected int containerCount;
   protected long bytesUsed;

   public AccountMetadata(int containerCount, long bytesUsed) {
      this.containerCount = containerCount;
      this.bytesUsed = bytesUsed;
   }

   /**
    * 
    * @return the number of containers in OpenStack Object Storage for the account
    */
   public int getContainerCount() {
      return containerCount;
   }

   /**
    * @return the total bytes stored in OpenStack Object Storage for the account
    */
   public long getBytesUsed() {
      return bytesUsed;
   }

   @Override
   public boolean equals(Object object) {
      if (this == object) {
         return true;
      }
      if (object instanceof AccountMetadata) {
         final AccountMetadata other = AccountMetadata.class.cast(object);
         return equal(getContainerCount(), other.getContainerCount()) && equal(getBytesUsed(), other.getBytesUsed());
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(getContainerCount(), getBytesUsed());
   }

   @Override
   public String toString() {
      return string().toString();
   }

   protected ToStringHelper string() {
      return toStringHelper("").add("containerCount", getContainerCount()).add("bytesUsed", getBytesUsed());
   }
}
