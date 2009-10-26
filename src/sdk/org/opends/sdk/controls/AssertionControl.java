/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at
 * trunk/opends/resource/legal-notices/OpenDS.LICENSE
 * or https://OpenDS.dev.java.net/OpenDS.LICENSE.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at
 * trunk/opends/resource/legal-notices/OpenDS.LICENSE.  If applicable,
 * add the following below this CDDL HEADER, with the fields enclosed
 * by brackets "[]" replaced with your own identifying information:
 *      Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 *      Copyright 2009 Sun Microsystems, Inc.
 */

package org.opends.sdk.controls;



import static org.opends.messages.ProtocolMessages.ERR_LDAPASSERT_INVALID_CONTROL_VALUE;
import static org.opends.messages.ProtocolMessages.ERR_LDAPASSERT_NO_CONTROL_VALUE;
import static org.opends.sdk.util.StaticUtils.getExceptionMessage;

import java.io.IOException;

import org.opends.messages.Message;
import org.opends.sdk.DecodeException;
import org.opends.sdk.Filter;
import org.opends.sdk.asn1.ASN1;
import org.opends.sdk.asn1.ASN1Reader;
import org.opends.sdk.asn1.ASN1Writer;
import org.opends.sdk.ldap.LDAPUtils;
import org.opends.sdk.spi.ControlDecoder;
import org.opends.sdk.util.Validator;
import org.opends.server.types.ByteString;
import org.opends.server.types.ByteStringBuilder;



/**
 * Assertion control.
 */
public class AssertionControl extends Control
{
  /**
   * The IANA-assigned OID for the LDAP assertion control.
   */
  static final String OID_LDAP_ASSERTION = "1.3.6.1.1.12";

  /**
   * Decodes a assertion control from a byte string.
   */
  private final static class Decoder implements
      ControlDecoder<AssertionControl>
  {
    /**
     * {@inheritDoc}
     */
    public AssertionControl decode(boolean isCritical, ByteString value)
        throws DecodeException
    {
      if (value == null)
      {
        Message message = ERR_LDAPASSERT_NO_CONTROL_VALUE.get();
        throw new DecodeException(message);
      }

      ASN1Reader reader = ASN1.getReader(value);
      Filter filter;
      try
      {
        filter = LDAPUtils.decodeFilter(reader);
      }
      catch (IOException e)
      {
        throw new DecodeException(ERR_LDAPASSERT_INVALID_CONTROL_VALUE
            .get(getExceptionMessage(e)), e);
      }

      return new AssertionControl(isCritical, filter);
    }



    /**
     * {@inheritDoc}
     */
    public String getOID()
    {
      return OID_LDAP_ASSERTION;
    }

  }

  /**
   * A control decoder which can be used to decode assertion controls.
   */
  public static final ControlDecoder<AssertionControl> DECODER =
      new Decoder();

  static
  {
    Controls.registerControl(DECODER.getOID(), DECODER);
  }

  // The assertion filter.
  private final Filter filter;



  /**
   * Creates a new assertion using the default OID and the provided
   * criticality and assertion filter.
   *
   * @param isCritical
   *          Indicates whether this control should be considered
   *          critical to the operation processing.
   * @param filter
   *          The assertion filter.
   */
  public AssertionControl(boolean isCritical, Filter filter)
  {
    super(OID_LDAP_ASSERTION, isCritical);

    Validator.ensureNotNull(filter);
    this.filter = filter;
  }



  /**
   * Returns the assertion filter.
   *
   * @return The assertion filter.
   */
  public Filter getFilter()
  {
    return filter;
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public ByteString getValue()
  {
    ByteStringBuilder buffer = new ByteStringBuilder();
    ASN1Writer writer = ASN1.getWriter(buffer);
    try
    {
      LDAPUtils.encodeFilter(writer, filter);
      return buffer.toByteString();
    }
    catch (IOException ioe)
    {
      // This should never happen unless there is a bug somewhere.
      throw new RuntimeException(ioe);
    }
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasValue()
  {
    return true;
  }



  /**
   * {@inheritDoc}
   */
  @Override
  public void toString(StringBuilder buffer)
  {
    buffer.append("AssertionControl(oid=");
    buffer.append(getOID());
    buffer.append(", criticality=");
    buffer.append(isCritical());
    buffer.append(", filter=\"");
    filter.toString(buffer);
    buffer.append("\")");
  }
}
