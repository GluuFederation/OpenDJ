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
 *      Copyright 2008-2009 Sun Microsystems, Inc.
 */

package org.forgerock.opendj.config.client;

import org.forgerock.i18n.LocalizableMessage;

/**
 * This exception is thrown when a security related problem occurs whilst
 * interacting with the Directory Server. These fall broadly into two
 * categories: authentication problems and authorization problems.
 */
public abstract class AdminSecurityException extends AdminClientException {

    /**
     * Fake serialization ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Create a security exception with a message and cause.
     *
     * @param message
     *            The message.
     * @param cause
     *            The cause.
     */
    protected AdminSecurityException(LocalizableMessage message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Create a security exception with a message.
     *
     * @param message
     *            The message.
     */
    protected AdminSecurityException(LocalizableMessage message) {
        super(message);
    }
}
