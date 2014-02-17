/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at legal-notices/CDDLv1_0.txt
 * or http://forgerock.org/license/CDDLv1.0.html.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at legal-notices/CDDLv1_0.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information:
 *      Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 *      Copyright 2006-2008 Sun Microsystems, Inc.
 *      Portions Copyright 2014 ForgeRock AS
 */
package org.opends.server.types;

import org.forgerock.opendj.ldap.ModificationType;

/**
 * This class defines a data structure for storing and interacting
 * with a modification that may be requested of an entry in the
 * Directory Server.
 */
@org.opends.server.types.PublicAPI(
     stability=org.opends.server.types.StabilityLevel.UNCOMMITTED,
     mayInstantiate=true,
     mayExtend=false,
     mayInvoke=true)
public final class Modification
{
  // The attribute for this modification.
  private Attribute attribute;

  // Indicates whether this modification was generated by internal
  // processing and therefore should not be subject to
  // no-user-modification and related checks.
  private boolean isInternal;

  // The modification type for this modification.
  private ModificationType modificationType;



  /**
   * Creates a new modification with the provided information.
   *
   * @param  modificationType  The modification type for this
   *                           modification.
   * @param  attribute         The attribute for this modification.
   */
  public Modification(ModificationType modificationType,
                      Attribute attribute)
  {
    this.modificationType = modificationType;
    this.attribute        = attribute;

    isInternal = false;
  }



  /**
   * Creates a new modification with the provided information.
   *
   * @param  modificationType  The modification type for this
   *                           modification.
   * @param  attribute         The attribute for this modification.
   * @param  isInternal        Indicates whether this is an internal
   *                           modification and therefore should not
   *                           be subject to no-user-modification and
   *                           related checks.
   */
  public Modification(ModificationType modificationType,
                      Attribute attribute, boolean isInternal)
  {
    this.modificationType = modificationType;
    this.attribute        = attribute;
    this.isInternal       = isInternal;
  }



  /**
   * Retrieves the modification type for this modification.
   *
   * @return  The modification type for this modification.
   */
  public ModificationType getModificationType()
  {
    return modificationType;
  }



  /**
   * Specifies the modification type for this modification.
   *
   * @param  modificationType  The modification type for this
   *                           modification.
   */
  @org.opends.server.types.PublicAPI(
       stability=org.opends.server.types.StabilityLevel.PRIVATE,
       mayInstantiate=false,
       mayExtend=false,
       mayInvoke=false)
  public void setModificationType(ModificationType modificationType)
  {
    this.modificationType = modificationType;
  }



  /**
   * Retrieves the attribute for this modification.
   *
   * @return  The attribute for this modification.
   */
  public Attribute getAttribute()
  {
    return attribute;
  }



  /**
   * Specifies the attribute for this modification.
   *
   * @param  attribute  The attribute for this modification.
   */
  @org.opends.server.types.PublicAPI(
       stability=org.opends.server.types.StabilityLevel.PRIVATE,
       mayInstantiate=false,
       mayExtend=false,
       mayInvoke=false)
  public void setAttribute(Attribute attribute)
  {
    this.attribute = attribute;
  }



  /**
   * Indicates whether this is modification was created by internal
   * processing and should not be subject to no-user-modification and
   * related checks.
   *
   * @return  <CODE>true</CODE> if this is an internal modification,
   *          or <CODE>false</CODE> if not.
   */
  public boolean isInternal()
  {
    return isInternal;
  }



  /**
   * Specifies whether this modification was created by internal
   * processing and should not be subject to no-user-modification and
   * related checks.
   *
   * @param  isInternal  Specifies whether this modification was
   *                     created by internal processing and should
   *                     not be subject to no-user-modification and
   *                     related checks.
   */
  public void setInternal(boolean isInternal)
  {
    this.isInternal = isInternal;
  }



  /**
   * Indicates whether the provided object is equal to this
   * modification.  It will only be considered equal if the object is
   * a modification with the same modification type and an attribute
   * that is equal to this modification.
   *
   * @param  o  The object for which to make the determination.
   *
   * @return  <CODE>true</CODE> if the provided object is a
   *          modification that is equal to this modification, or
   *          <CODE>false</CODE> if not.
   */
  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }

    if ((o == null) || (! (o instanceof Modification)))
    {
      return false;
    }

    Modification m = (Modification) o;
    if (modificationType != m.modificationType)
    {
      return false;
    }

    return attribute.equals(m.attribute);
  }



  /**
   * Retrieves the hash code for this modification.  The hash code
   * returned will be the hash code for the attribute included in this
   * modification.
   *
   * @return  The hash code for this modification.
   */
  @Override
  public int hashCode()
  {
    return attribute.hashCode();
  }



  /**
   * Retrieves a one-line string representation of this modification.
   *
   * @return  A one-line string representation of this modification.
   */
  @Override
  public String toString()
  {
    StringBuilder buffer = new StringBuilder();
    toString(buffer);
    return buffer.toString();
  }



  /**
   * Appends a one-line representation of this modification to the
   * provided buffer.
   *
   * @param  buffer  The buffer to which the information should be
   *                 appended.
   */
  public void toString(StringBuilder buffer)
  {
    buffer.append("Modification(");
    buffer.append(modificationType.toString());
    buffer.append(", ");
    buffer.append(attribute.toString());
  }
}

