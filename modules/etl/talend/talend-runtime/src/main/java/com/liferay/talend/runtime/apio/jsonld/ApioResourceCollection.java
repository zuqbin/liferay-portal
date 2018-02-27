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

package com.liferay.talend.runtime.apio.jsonld;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.MissingNode;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represent the Json-LD+Hydra response of the Apio Architect for a given
 * resource collection Note: It must NOT be the entry point or a single
 * resource, it's for easier traversing the ResourceCollections.
 *
 * @author Zoltán Takács
 */
public class ApioResourceCollection extends ApioSingleModel {

	public ApioResourceCollection(JsonNode responseJsonNode)
		throws IOException {

		super(responseJsonNode);

		_validateResourceCollection();
	}

	public JsonNode getFirstEntry() {
		JsonNode memberJsonNode = getMemberNode();

		if (!memberJsonNode.isArray() || (memberJsonNode.size() == 0)) {
			if (_log.isDebugEnabled()) {
				_log.debug("Unable to fetch the member node");
			}

			return MissingNode.getInstance();
		}

		return memberJsonNode.get(0);
	}

	/**
	 * Parses the actual jsonNode (Resource Collection) e.g people,
	 * blog-postings and looks for the members array node.
	 *
	 * @return <code>JsonNode</code> The ArrayNode which contains the resource
	 *         entries of a given (partial)collection (Member) or MissingNode if
	 *         it's not present
	 */
	public JsonNode getMemberNode() {
		if (_memberJsonNode == null) {
			_memberJsonNode = findJsonNode(JSONLDConstants.COLLECTION_MEMBER);
		}

		return _memberJsonNode;
	}

	public int getNumberOfItems() {
		JsonNode jsonNode = responseJsonNode.path(
			JSONLDConstants.COLLECTION_NUMBER_OF_ITEMS);

		return jsonNode.asInt();
	}

	/**
	 * Parses the view JsonNode of the resource
	 *
	 * @return actual collection page or empty string if not present in the
	 *         JsonNode
	 */
	public String getResourceActualPage() {
		JsonNode viewJsonNode = getViewNode();

		JsonNode jsonNode = viewJsonNode.path(JSONLDConstants.ID);

		return jsonNode.asText();
	}

	/**
	 * Determines the resource collection type based on the member node in the
	 * Apio architect response
	 *
	 * @return String the type of the resource collection. E.g. Person,
	 *         BlogPosting. <code>null</code> if the resource type cannot be
	 *         determined
	 */
	public String getResourceCollectionType() {
		JsonNode firstEntryJsonNode = getFirstEntry();

		JsonNode typeJsonNode = firstEntryJsonNode.path(JSONLDConstants.TYPE);

		if (typeJsonNode.isArray()) {
			JsonNode jsonNode = typeJsonNode.get(0);

			return jsonNode.asText();
		}

		return null;
	}

	/**
	 * Parses the given jsonNode (Resource Collection) e.g people, blog-postings
	 * and returns the name of the resource element fields based on the first
	 * entry
	 *
	 * @return <code>List<String></code> Name of the resource fields, empty
	 *         collection otherwise
	 */
	public List<String> getResourceElementFieldNames() {
		JsonNode firstEntryJsonNode = getFirstEntry();

		if (firstEntryJsonNode.isMissingNode() || firstEntryJsonNode.isNull()) {
			return Collections.emptyList();
		}

		List<String> fieldNames = new ArrayList<>();

		Iterator<String> iterator = firstEntryJsonNode.fieldNames();

		while (iterator.hasNext()) {
			fieldNames.add(iterator.next());
		}

		return fieldNames;
	}

	/**
	 * Parses the view JsonNode of the resource
	 *
	 * @return first collection page or empty string if not present in the
	 *         JsonNode
	 */
	public String getResourceFirstPage() {
		JsonNode viewJsonNode = getViewNode();

		JsonNode jsonNode = viewJsonNode.path(JSONLDConstants.VIEW_FIRST);

		return jsonNode.asText();
	}

	/**
	 * Parses the view JsonNode of the resource
	 *
	 * @return last collection page or empty string if not present in the
	 *         JsonNode
	 */
	public String getResourceLastPage() {
		JsonNode viewJsonNode = getViewNode();

		JsonNode jsonNode = viewJsonNode.path(JSONLDConstants.VIEW_LAST);

		return jsonNode.asText();
	}

	/**
	 * Parses the view JsonNode of the resource
	 *
	 * @return relative upcoming collection page or empty string if not present
	 *         in the JsonNode
	 */
	public String getResourceNextPage() {
		JsonNode viewJsonNode = getViewNode();

		JsonNode jsonNode = viewJsonNode.path(JSONLDConstants.VIEW_NEXT);

		return jsonNode.asText();
	}

	/**
	 * Parses the view JsonNode of the resource
	 *
	 * @return relative previous collection page or empty string if not present
	 *         in the JsonNode
	 */
	public String getResourcePreviousPage() {
		JsonNode viewJsonNode = getViewNode();

		JsonNode jsonNode = viewJsonNode.path(JSONLDConstants.VIEW_PREVIOUS);

		return jsonNode.asText();
	}

	public int getTotalItems() {
		JsonNode jsonNode = responseJsonNode.path(
			JSONLDConstants.COLLECTION_TOTAL_ITEMS);

		return jsonNode.asInt();
	}

	/**
	 * Parses the actual jsonNode (Resource Collection) e.g people,
	 * blog-postings and looks for the view node.
	 *
	 * @return <code>JsonNode</code> The JsonNode for the view section or
	 *         MissingNode if it's not present
	 */
	public JsonNode getViewNode() {
		return findJsonNode(JSONLDConstants.COLLECTION_VIEW);
	}

	/**
	 * Parses the given JsonNode which is a <code>@context</code> node and find
	 * the value of the <code>@vocab</code> node.
	 *
	 * @param  contextJsonNode
	 * @return <code>String</code> the Vocab's value e.g "@vocab":
	 *         "http://schema.org" otherwise empty String
	 */
	public String getVocabulary(JsonNode contextJsonNode) {
		JsonNode jsonNode = contextJsonNode.path(JSONLDConstants.VOCAB);

		return jsonNode.asText();
	}

	@Override
	public boolean isSingleModel() {
		return false;
	}

	private void _validateResourceCollection() throws IOException {
		JsonNode typeJsonNode = getTypeNode();

		boolean collection = false;

		if (typeJsonNode.isArray()) {
			Iterator<JsonNode> iterator = typeJsonNode.elements();

			while (iterator.hasNext() && (collection == false)) {
				JsonNode jsonNode = iterator.next();

				if (HydraConstants.COLLECTION.equals(jsonNode.asText())) {
					collection = true;
				}
			}
		}
		else {
			throw new IOException(
				"The given resource does not contain ArrayNode for its type");
		}

		if (!collection) {
			throw new IOException(
				"The given resource's type is not a collection");
		}
	}

	private static final Logger _log = LoggerFactory.getLogger(
		ApioResourceCollection.class);

	/**
	 * Store the 'member' JsonNode as its the most resource intensive task to
	 * collect and determine, so do it only once.
	 *
	 * @see #getMemberNode()
	 */
	private JsonNode _memberJsonNode;

}