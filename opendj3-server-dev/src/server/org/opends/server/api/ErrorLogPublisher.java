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
 *      Copyright 2008 Sun Microsystems, Inc.
 *      Portions Copyright 2013-2014 ForgeRock AS
 */
package org.opends.server.api;

import java.util.HashMap;
import org.forgerock.i18n.slf4j.LocalizedLogger;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.forgerock.i18n.LocalizableMessage;
import org.opends.messages.Severity;
import org.opends.server.admin.std.server.ErrorLogPublisherCfg;

/**
 * This class defines the set of methods and structures that must be
 * implemented for a Directory Server error log publisher.
 *
 * @param  <T>  The type of error log publisher configuration handled
 *              by this log publisher implementation.
 */
@org.opends.server.types.PublicAPI(
     stability=org.opends.server.types.StabilityLevel.VOLATILE,
     mayInstantiate=false,
     mayExtend=true,
     mayInvoke=false)
public abstract class ErrorLogPublisher<T extends ErrorLogPublisherCfg>
    implements LogPublisher<T>
{

  private static final LocalizedLogger logger = LocalizedLogger.getLoggerForThisClass();

  /**
   * The hash map that will be used to define specific log severities
   * for the various categories.
   */
  protected Map<String, Set<Severity>> definedSeverities =
      new HashMap<String, Set<Severity>>();



  /**
   * The set of default log severities that will be used if no custom
   * severities have been defined for the associated category.
   */
  protected Set<Severity> defaultSeverities = new HashSet<Severity>();



  /** {@inheritDoc} */
  @Override
  public boolean isConfigurationAcceptable(T configuration,
                      List<LocalizableMessage> unacceptableReasons)
  {
    // This default implementation does not perform any special
    // validation. It should be overridden by error log publisher
    // implementations that wish to perform more detailed validation.
    return true;
  }

  /**
   * Writes a message to the error log using the provided information. The
   * message's category and severity information will be used to determine
   * whether to actually log this message.
   *
   * @param message
   *          The message to be logged.
   */
  public void logError(LocalizableMessage message) {
    // TODO : to remove
  }

  /**
   * Writes a message to the error log using the provided information.
   * <p>
   * The category and severity information are used to determine whether to
   * actually log this message.
   *
   * @param category
   *          The category of the message.
   * @param severity
   *          The severity of the message.
   * @param message
   *          The message to be logged.
   * @param exception
   *          The exception to be logged.
   */
  public abstract void log(String category, Severity severity,
      LocalizableMessage message, Throwable exception);

  /**
   * Check if a message should be logged for the provided category and severity.
   *
   * @param category
   *          The category of the message.
   * @param severity
   *          The severity of the message.
   * @return {@code true} if the message should be logged, {@code false}
   *         otherwise
   */
  public abstract boolean isEnabledFor(String category, Severity severity);

}