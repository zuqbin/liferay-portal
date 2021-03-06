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

package com.liferay.dynamic.data.mapping.form.evaluator.internal.functions;

import com.liferay.dynamic.data.mapping.form.evaluator.DDMFormFieldEvaluationResult;
import com.liferay.portal.json.JSONFactoryImpl;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.util.KeyValuePair;
import com.liferay.portal.util.PortalImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Leonardo Barros
 */
@RunWith(MockitoJUnitRunner.class)
public class CallFunctionTest {

	@Test
	public void testAutoSelectOption() {
		Map<String, List<DDMFormFieldEvaluationResult>>
			ddmFormFieldEvaluationResults = new HashMap<>();

		DDMFormFieldEvaluationResult ddmFormFieldEvaluationResult =
			new DDMFormFieldEvaluationResult("field0", "1");

		ddmFormFieldEvaluationResults.put(
			"field0", Arrays.asList(ddmFormFieldEvaluationResult));

		CallFunction callFunction = new CallFunction(
			null, ddmFormFieldEvaluationResults, null, _jsonFactory,
			new PortalImpl());

		List<KeyValuePair> keyValuePairs = new ArrayList<>();

		keyValuePairs.add(new KeyValuePair("key_1", "value_1"));

		callFunction.setDDMFormFieldOptions("field0", keyValuePairs);

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		jsonArray.put("value_1");

		Object value = ddmFormFieldEvaluationResult.getValue();

		Assert.assertEquals(jsonArray.toString(), value.toString());
	}

	@Test
	public void testGetFieldValueFromJSONArray() {
		Map<String, List<DDMFormFieldEvaluationResult>>
			ddmFormFieldEvaluationResults = new HashMap<>();

		DDMFormFieldEvaluationResult ddmFormFieldEvaluationResult =
			new DDMFormFieldEvaluationResult("field0", "1");

		JSONArray jsonArray = _jsonFactory.createJSONArray();

		jsonArray.put("test");

		ddmFormFieldEvaluationResult.setValue(jsonArray);

		ddmFormFieldEvaluationResults.put(
			"field0", Arrays.asList(ddmFormFieldEvaluationResult));

		CallFunction callFunction = new CallFunction(
			null, ddmFormFieldEvaluationResults, null, _jsonFactory,
			new PortalImpl());

		Assert.assertEquals(
			"test", callFunction.getDDMFormFieldValue("field0"));
	}

	@Test
	public void testGetFieldValueFromString() {
		Map<String, List<DDMFormFieldEvaluationResult>>
			ddmFormFieldEvaluationResults = new HashMap<>();

		DDMFormFieldEvaluationResult ddmFormFieldEvaluationResult =
			new DDMFormFieldEvaluationResult("field0", "1");

		ddmFormFieldEvaluationResult.setValue("test");

		ddmFormFieldEvaluationResults.put(
			"field0", Arrays.asList(ddmFormFieldEvaluationResult));

		CallFunction callFunction = new CallFunction(
			null, ddmFormFieldEvaluationResults, null, _jsonFactory,
			new PortalImpl());

		Assert.assertEquals(
			"test", callFunction.getDDMFormFieldValue("field0"));
	}

	@Test
	public void testNotAutoSelectOption() throws Exception {
		Map<String, List<DDMFormFieldEvaluationResult>>
			ddmFormFieldEvaluationResults = new HashMap<>();

		DDMFormFieldEvaluationResult ddmFormFieldEvaluationResult =
			new DDMFormFieldEvaluationResult("field0", "1");

		ddmFormFieldEvaluationResults.put(
			"field0", Arrays.asList(ddmFormFieldEvaluationResult));

		CallFunction callFunction = new CallFunction(
			null, ddmFormFieldEvaluationResults, null, _jsonFactory,
			new PortalImpl());

		List<KeyValuePair> keyValuePairs = new ArrayList<>();

		keyValuePairs.add(new KeyValuePair("key_1", "value_1"));
		keyValuePairs.add(new KeyValuePair("key_2", "value_2"));

		callFunction.setDDMFormFieldOptions("field0", keyValuePairs);

		Assert.assertNull(ddmFormFieldEvaluationResult.getValue());
	}

	@Test
	public void testSetDDMFormFieldOptionsRepeatableFields() {
		Map<String, List<DDMFormFieldEvaluationResult>>
			ddmFormFieldEvaluationResults = new HashMap<>();

		DDMFormFieldEvaluationResult ddmFormFieldEvaluationResult1 =
			new DDMFormFieldEvaluationResult("field0", "1");

		DDMFormFieldEvaluationResult ddmFormFieldEvaluationResult2 =
			new DDMFormFieldEvaluationResult("field0", "2");

		ddmFormFieldEvaluationResults.put(
			"field0",
			Arrays.asList(
				ddmFormFieldEvaluationResult1, ddmFormFieldEvaluationResult2));

		CallFunction callFunction = new CallFunction(
			null, ddmFormFieldEvaluationResults, null, _jsonFactory,
			new PortalImpl());

		List<KeyValuePair> keyValuePairs = new ArrayList<>();

		keyValuePairs.add(new KeyValuePair("key_1", "value_1"));
		keyValuePairs.add(new KeyValuePair("key_2", "value_2"));

		callFunction.setDDMFormFieldOptions("field0", keyValuePairs);

		Assert.assertEquals(
			keyValuePairs,
			ddmFormFieldEvaluationResult1.getProperty("options"));
		Assert.assertEquals(
			keyValuePairs,
			ddmFormFieldEvaluationResult2.getProperty("options"));
	}

	private final JSONFactory _jsonFactory = new JSONFactoryImpl();

}