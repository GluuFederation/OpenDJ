package org.opends.sdk.schema.syntaxes;

import static org.opends.sdk.schema.SchemaConstants.*;

import org.opends.messages.MessageBuilder;
import org.opends.sdk.schema.Schema;
import org.opends.server.types.ByteSequence;

/**
 * This class implements the protocol information attribute syntax, which is
 * being deprecated.  As such, this implementation behaves exactly like the
 * directory string syntax.
 */
public class ProtocolInformationSyntax extends AbstractSyntaxImplementation
{

  public String getName() {
    return SYNTAX_PROTOCOL_INFORMATION_NAME;
  }

  public boolean isHumanReadable() {
    return true;
  }



  /**
   * Indicates whether the provided value is acceptable for use in an attribute
   * with this syntax.  If it is not, then the reason may be appended to the
   * provided buffer.
   *
   * @param schema
   *@param  value          The value for which to make the determination.
   * @param  invalidReason  The buffer to which the invalid reason should be
 *                        appended.
 * @return  <CODE>true</CODE> if the provided value is acceptable for use with
   *          this syntax, or <CODE>false</CODE> if not.
   */
  public boolean valueIsAcceptable(Schema schema, ByteSequence value,
                                   MessageBuilder invalidReason)
  {
    // We will accept any value for this syntax.
    return true;
  }

  @Override
  public String getEqualityMatchingRule() {
    return EMR_CASE_IGNORE_OID;
  }

  @Override
  public String getOrderingMatchingRule() {
    return OMR_CASE_IGNORE_OID;
  }

  @Override
  public String getSubstringMatchingRule() {
    return SMR_CASE_IGNORE_OID;
  }

  @Override
  public String getApproximateMatchingRule() {
    return AMR_DOUBLE_METAPHONE_OID;
  }
}
