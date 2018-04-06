/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.relationship;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.ClassedModel;

import java.util.Collection;

/**
 * @author Mate Thurzo
 */
@ProviderType
public class RelationshipManagerUtil {

	public static <T extends ClassedModel> Collection<? extends ClassedModel>
		getInboundRelationships(Class<T> relationshipBaseClass, long primKey) {

		return _relationshipManager.getInboundRelationships(
			relationshipBaseClass, primKey);
	}

	public static <T extends ClassedModel> Collection<? extends ClassedModel>
		getOutboundRelationships(Class<T> relationshipBaseClass, long primKey) {

		return _relationshipManager.getOutboundRelationships(
			relationshipBaseClass, primKey);
	}

	public static <T extends ClassedModel> Collection<? extends ClassedModel>
		getRelationships(Class<T> relationshipBaseClass, long primKey) {

		return _relationshipManager.getRelationships(
			relationshipBaseClass, primKey);
	}

	public static void setRelationshipManager(
		RelationshipManager relationshipManager) {

		if (_relationshipManager != null) {
			relationshipManager = _relationshipManager;

			return;
		}

		_relationshipManager = relationshipManager;
	}

	private static RelationshipManager _relationshipManager;

}