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
 *      Portions Copyright 2007 Sun Microsystems, Inc.
 */

package org.opends.server.admin.client;



import org.opends.server.admin.std.client.RootCfgClient;



/**
 * Client management connection context.
 */
public abstract class ManagementContext {

  /**
   * Creates a new management context.
   */
  protected ManagementContext() {
    // No implementation required.
  }



  /**
   * Gets the root configuration client associated with this
   * management context.
   *
   * @return Returns the root configuration client associated with
   *         this management context.
   */
  public final RootCfgClient getRootConfiguration() {
    return getRootConfigurationManagedObject().getConfiguration();
  }



  /**
   * Gets the root configuration managed object associated with this
   * management context.
   *
   * @return Returns the root configuration managed object associated
   *         with this management context.
   */
  public abstract
  ManagedObject<RootCfgClient> getRootConfigurationManagedObject();

}
