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
 */
package org.forgerock.opendj.config;

import java.util.Locale;

import org.forgerock.i18n.LocalizableMessage;

/**
 * A default behavior provider which indicates special behavior. It should be
 * used by properties which have a default behavior which cannot be directly
 * represented using real values of the property. For example, a property
 * containing a set of user names might default to "all users" when no values
 * are provided. This meaning cannot be represented using a finite set of
 * values.
 *
 * @param <T>
 *            The type of values represented by this provider.
 */
public final class AliasDefaultBehaviorProvider<T> extends DefaultBehaviorProvider<T> {

    /**
     * The managed object definition associated with this default
     * behavior.
     */
    private final AbstractManagedObjectDefinition<?, ?> definition;

    /**
     * The name of the property definition associated with this default
     * behavior.
     */
    private final String propertyName;

    /**
     * Create an alias default behavior provider.
     *
     * @param d
     *            The managed object definition associated with this default
     *            behavior.
     * @param propertyName
     *            The name of the property definition associated with this
     *            default behavior.
     */
    public AliasDefaultBehaviorProvider(AbstractManagedObjectDefinition<?, ?> d, String propertyName) {
        this.definition = d;
        this.propertyName = propertyName;
    }

    /** {@inheritDoc} */
    public <R, P> R accept(DefaultBehaviorProviderVisitor<T, R, P> v, P p) {
        return v.visitAlias(this, p);
    }

    /**
     * Gets the synopsis of this alias default behavior in the default locale.
     *
     * @return Returns the synopsis of this alias default behavior in the
     *         default locale.
     */
    public final LocalizableMessage getSynopsis() {
        return getSynopsis(Locale.getDefault());
    }

    /**
     * Gets the synopsis of this alias default behavior in the specified locale.
     *
     * @param locale
     *            The locale.
     * @return Returns the synopsis of this alias default behavior in the
     *         specified locale.
     */
    public final LocalizableMessage getSynopsis(Locale locale) {
        ManagedObjectDefinitionI18NResource resource = ManagedObjectDefinitionI18NResource.getInstance();
        String property = "property." + propertyName + ".default-behavior.alias.synopsis";
        return resource.getMessage(definition, property, locale);
    }

}
