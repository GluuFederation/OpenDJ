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

package org.opends.sdk.ldif;



import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.opends.sdk.requests.AddRequest;
import org.opends.sdk.requests.DeleteRequest;
import org.opends.sdk.requests.ModifyDNRequest;
import org.opends.sdk.requests.ModifyRequest;
import org.opends.sdk.util.Validator;



/**
 * An LDIF change record writer writes change records using the LDAP
 * Data Interchange Format (LDIF) to a user defined destination.
 * 
 * @see <a href="http://tools.ietf.org/html/rfc2849">RFC 2849 - The LDAP
 *      Data Interchange Format (LDIF) - Technical Specification </a>
 */
public final class LDIFChangeRecordWriter implements
    ChangeRecordWriter, LDIFWriterOptions
{

  private boolean addUserFriendlyComments = false;

  private int wrapColumn = 0;

  private final LDIFWriter writer;



  /**
   * Creates a new LDIF change record writer which will append lines of
   * LDIF to the provided list.
   * 
   * @param ldifLines
   *          The list to which lines of LDIF should be appended.
   */
  public LDIFChangeRecordWriter(List<String> ldifLines)
  {
    Validator.ensureNotNull(ldifLines);
    this.writer =
        new LDIFWriter(this, new LDIFWriterListImpl(ldifLines));
  }



  /**
   * Creates a new LDIF change record writer whose destination is the
   * provided output stream.
   * 
   * @param out
   *          The output stream to use.
   */
  public LDIFChangeRecordWriter(OutputStream out)
  {
    Validator.ensureNotNull(out);
    this.writer =
        new LDIFWriter(this, new LDIFWriterOutputStreamImpl(out));
  }



  /**
   * {@inheritDoc}
   */
  public void close() throws IOException
  {
    writer.close();
  }



  /**
   * {@inheritDoc}
   */
  public void flush() throws IOException
  {
    writer.flush();
  }



  /**
   * {@inheritDoc}
   */
  public int getWrapColumn()
  {
    return wrapColumn;
  }



  /**
   * {@inheritDoc}
   */
  public boolean isAddUserFriendlyComments()
  {
    return addUserFriendlyComments;
  }



  /**
   * Specifies whether or not user-friendly comments should be added
   * whenever distinguished names or UTF-8 attribute values are
   * encountered which contained non-ASCII characters. The default is
   * {@code false}.
   * 
   * @param addUserFriendlyComments
   *          {@code true} if user-friendly comments should be added, or
   *          {@code false} otherwise.
   * @return A reference to this {@code LDIFEntryWriter}.
   */
  public LDIFChangeRecordWriter setAddUserFriendlyComments(
      boolean addUserFriendlyComments)
  {
    this.addUserFriendlyComments = addUserFriendlyComments;
    return this;
  }



  /**
   * Specifies the column at which long lines should be wrapped. A value
   * less than or equal to zero (the default) indicates that no wrapping
   * should be performed.
   * 
   * @param wrapColumn
   *          The column at which long lines should be wrapped.
   * @return A reference to this {@code LDIFEntryWriter}.
   */
  public LDIFChangeRecordWriter setWrapColumn(int wrapColumn)
  {
    this.wrapColumn = wrapColumn;
    return this;
  }



  /**
   * {@inheritDoc}
   */
  public LDIFChangeRecordWriter writeChangeRecord(AddRequest change)
      throws IOException, NullPointerException
  {
    writer.writeChangeRecord(change);
    return this;
  }



  /**
   * {@inheritDoc}
   */
  public LDIFChangeRecordWriter writeChangeRecord(ChangeRecord change)
      throws IOException, NullPointerException
  {
    writer.writeChangeRecord(change);
    return this;
  }



  /**
   * {@inheritDoc}
   */
  public LDIFChangeRecordWriter writeChangeRecord(DeleteRequest change)
      throws IOException, NullPointerException
  {
    writer.writeChangeRecord(change);
    return this;
  }



  /**
   * {@inheritDoc}
   */
  public LDIFChangeRecordWriter writeChangeRecord(ModifyDNRequest change)
      throws IOException, NullPointerException
  {
    writer.writeChangeRecord(change);
    return this;
  }



  /**
   * {@inheritDoc}
   */
  public LDIFChangeRecordWriter writeChangeRecord(ModifyRequest change)
      throws IOException, NullPointerException
  {
    writer.writeChangeRecord(change);
    return this;
  }



  /**
   * {@inheritDoc}
   */
  public LDIFChangeRecordWriter writeComment(CharSequence comment)
      throws IOException, NullPointerException
  {
    writer.writeComment(comment);
    return this;
  }

}
