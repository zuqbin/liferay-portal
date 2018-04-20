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

package com.liferay.layout.page.template.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;

import com.liferay.layout.page.template.model.LayoutPageTemplateEntry;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntryModel;
import com.liferay.layout.page.template.model.LayoutPageTemplateEntrySoap;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The base model implementation for the LayoutPageTemplateEntry service. Represents a row in the &quot;LayoutPageTemplateEntry&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link LayoutPageTemplateEntryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LayoutPageTemplateEntryImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutPageTemplateEntryImpl
 * @see LayoutPageTemplateEntry
 * @see LayoutPageTemplateEntryModel
 * @generated
 */
@JSON(strict = true)
@ProviderType
public class LayoutPageTemplateEntryModelImpl extends BaseModelImpl<LayoutPageTemplateEntry>
	implements LayoutPageTemplateEntryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a layout page template entry model instance should use the {@link LayoutPageTemplateEntry} interface instead.
	 */
	public static final String TABLE_NAME = "LayoutPageTemplateEntry";
	public static final Object[][] TABLE_COLUMNS = {
			{ "layoutPageTemplateEntryId", Types.BIGINT },
			{ "groupId", Types.BIGINT },
			{ "companyId", Types.BIGINT },
			{ "userId", Types.BIGINT },
			{ "userName", Types.VARCHAR },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "layoutPageTemplateCollectionId", Types.BIGINT },
			{ "classNameId", Types.BIGINT },
			{ "classTypeId", Types.BIGINT },
			{ "name", Types.VARCHAR },
			{ "type_", Types.INTEGER },
			{ "htmlPreviewEntryId", Types.BIGINT },
			{ "defaultTemplate", Types.BOOLEAN }
		};
	public static final Map<String, Integer> TABLE_COLUMNS_MAP = new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("layoutPageTemplateEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("groupId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("layoutPageTemplateCollectionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classNameId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("classTypeId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("type_", Types.INTEGER);
		TABLE_COLUMNS_MAP.put("htmlPreviewEntryId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("defaultTemplate", Types.BOOLEAN);
	}

	public static final String TABLE_SQL_CREATE = "create table LayoutPageTemplateEntry (layoutPageTemplateEntryId LONG not null primary key,groupId LONG,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,layoutPageTemplateCollectionId LONG,classNameId LONG,classTypeId LONG,name VARCHAR(75) null,type_ INTEGER,htmlPreviewEntryId LONG,defaultTemplate BOOLEAN)";
	public static final String TABLE_SQL_DROP = "drop table LayoutPageTemplateEntry";
	public static final String ORDER_BY_JPQL = " ORDER BY layoutPageTemplateEntry.name ASC";
	public static final String ORDER_BY_SQL = " ORDER BY LayoutPageTemplateEntry.name ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.layout.page.template.service.util.ServiceProps.get(
				"value.object.entity.cache.enabled.com.liferay.layout.page.template.model.LayoutPageTemplateEntry"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.layout.page.template.service.util.ServiceProps.get(
				"value.object.finder.cache.enabled.com.liferay.layout.page.template.model.LayoutPageTemplateEntry"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.layout.page.template.service.util.ServiceProps.get(
				"value.object.column.bitmask.enabled.com.liferay.layout.page.template.model.LayoutPageTemplateEntry"),
			true);
	public static final long CLASSNAMEID_COLUMN_BITMASK = 1L;
	public static final long DEFAULTTEMPLATE_COLUMN_BITMASK = 2L;
	public static final long GROUPID_COLUMN_BITMASK = 4L;
	public static final long LAYOUTPAGETEMPLATECOLLECTIONID_COLUMN_BITMASK = 8L;
	public static final long NAME_COLUMN_BITMASK = 16L;
	public static final long TYPE_COLUMN_BITMASK = 32L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static LayoutPageTemplateEntry toModel(
		LayoutPageTemplateEntrySoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		LayoutPageTemplateEntry model = new LayoutPageTemplateEntryImpl();

		model.setLayoutPageTemplateEntryId(soapModel.getLayoutPageTemplateEntryId());
		model.setGroupId(soapModel.getGroupId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setLayoutPageTemplateCollectionId(soapModel.getLayoutPageTemplateCollectionId());
		model.setClassNameId(soapModel.getClassNameId());
		model.setClassTypeId(soapModel.getClassTypeId());
		model.setName(soapModel.getName());
		model.setType(soapModel.getType());
		model.setHtmlPreviewEntryId(soapModel.getHtmlPreviewEntryId());
		model.setDefaultTemplate(soapModel.getDefaultTemplate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<LayoutPageTemplateEntry> toModels(
		LayoutPageTemplateEntrySoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<LayoutPageTemplateEntry> models = new ArrayList<LayoutPageTemplateEntry>(soapModels.length);

		for (LayoutPageTemplateEntrySoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.layout.page.template.service.util.ServiceProps.get(
				"lock.expiration.time.com.liferay.layout.page.template.model.LayoutPageTemplateEntry"));

	public LayoutPageTemplateEntryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _layoutPageTemplateEntryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLayoutPageTemplateEntryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _layoutPageTemplateEntryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LayoutPageTemplateEntry.class;
	}

	@Override
	public String getModelClassName() {
		return LayoutPageTemplateEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("layoutPageTemplateEntryId",
			getLayoutPageTemplateEntryId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("layoutPageTemplateCollectionId",
			getLayoutPageTemplateCollectionId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classTypeId", getClassTypeId());
		attributes.put("name", getName());
		attributes.put("type", getType());
		attributes.put("htmlPreviewEntryId", getHtmlPreviewEntryId());
		attributes.put("defaultTemplate", isDefaultTemplate());

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long layoutPageTemplateEntryId = (Long)attributes.get(
				"layoutPageTemplateEntryId");

		if (layoutPageTemplateEntryId != null) {
			setLayoutPageTemplateEntryId(layoutPageTemplateEntryId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long layoutPageTemplateCollectionId = (Long)attributes.get(
				"layoutPageTemplateCollectionId");

		if (layoutPageTemplateCollectionId != null) {
			setLayoutPageTemplateCollectionId(layoutPageTemplateCollectionId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classTypeId = (Long)attributes.get("classTypeId");

		if (classTypeId != null) {
			setClassTypeId(classTypeId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		Integer type = (Integer)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long htmlPreviewEntryId = (Long)attributes.get("htmlPreviewEntryId");

		if (htmlPreviewEntryId != null) {
			setHtmlPreviewEntryId(htmlPreviewEntryId);
		}

		Boolean defaultTemplate = (Boolean)attributes.get("defaultTemplate");

		if (defaultTemplate != null) {
			setDefaultTemplate(defaultTemplate);
		}
	}

	@JSON
	@Override
	public long getLayoutPageTemplateEntryId() {
		return _layoutPageTemplateEntryId;
	}

	@Override
	public void setLayoutPageTemplateEntryId(long layoutPageTemplateEntryId) {
		_layoutPageTemplateEntryId = layoutPageTemplateEntryId;
	}

	@JSON
	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_columnBitmask |= GROUPID_COLUMN_BITMASK;

		if (!_setOriginalGroupId) {
			_setOriginalGroupId = true;

			_originalGroupId = _groupId;
		}

		_groupId = groupId;
	}

	public long getOriginalGroupId() {
		return _originalGroupId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getLayoutPageTemplateCollectionId() {
		return _layoutPageTemplateCollectionId;
	}

	@Override
	public void setLayoutPageTemplateCollectionId(
		long layoutPageTemplateCollectionId) {
		_columnBitmask |= LAYOUTPAGETEMPLATECOLLECTIONID_COLUMN_BITMASK;

		if (!_setOriginalLayoutPageTemplateCollectionId) {
			_setOriginalLayoutPageTemplateCollectionId = true;

			_originalLayoutPageTemplateCollectionId = _layoutPageTemplateCollectionId;
		}

		_layoutPageTemplateCollectionId = layoutPageTemplateCollectionId;
	}

	public long getOriginalLayoutPageTemplateCollectionId() {
		return _originalLayoutPageTemplateCollectionId;
	}

	@Override
	public String getClassName() {
		if (getClassNameId() <= 0) {
			return "";
		}

		return PortalUtil.getClassName(getClassNameId());
	}

	@Override
	public void setClassName(String className) {
		long classNameId = 0;

		if (Validator.isNotNull(className)) {
			classNameId = PortalUtil.getClassNameId(className);
		}

		setClassNameId(classNameId);
	}

	@JSON
	@Override
	public long getClassNameId() {
		return _classNameId;
	}

	@Override
	public void setClassNameId(long classNameId) {
		_columnBitmask |= CLASSNAMEID_COLUMN_BITMASK;

		if (!_setOriginalClassNameId) {
			_setOriginalClassNameId = true;

			_originalClassNameId = _classNameId;
		}

		_classNameId = classNameId;
	}

	public long getOriginalClassNameId() {
		return _originalClassNameId;
	}

	@JSON
	@Override
	public long getClassTypeId() {
		return _classTypeId;
	}

	@Override
	public void setClassTypeId(long classTypeId) {
		_classTypeId = classTypeId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@JSON
	@Override
	public int getType() {
		return _type;
	}

	@Override
	public void setType(int type) {
		_columnBitmask |= TYPE_COLUMN_BITMASK;

		if (!_setOriginalType) {
			_setOriginalType = true;

			_originalType = _type;
		}

		_type = type;
	}

	public int getOriginalType() {
		return _originalType;
	}

	@JSON
	@Override
	public long getHtmlPreviewEntryId() {
		return _htmlPreviewEntryId;
	}

	@Override
	public void setHtmlPreviewEntryId(long htmlPreviewEntryId) {
		_htmlPreviewEntryId = htmlPreviewEntryId;
	}

	@JSON
	@Override
	public boolean getDefaultTemplate() {
		return _defaultTemplate;
	}

	@JSON
	@Override
	public boolean isDefaultTemplate() {
		return _defaultTemplate;
	}

	@Override
	public void setDefaultTemplate(boolean defaultTemplate) {
		_columnBitmask |= DEFAULTTEMPLATE_COLUMN_BITMASK;

		if (!_setOriginalDefaultTemplate) {
			_setOriginalDefaultTemplate = true;

			_originalDefaultTemplate = _defaultTemplate;
		}

		_defaultTemplate = defaultTemplate;
	}

	public boolean getOriginalDefaultTemplate() {
		return _originalDefaultTemplate;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(getCompanyId(),
			LayoutPageTemplateEntry.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LayoutPageTemplateEntry toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (LayoutPageTemplateEntry)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		LayoutPageTemplateEntryImpl layoutPageTemplateEntryImpl = new LayoutPageTemplateEntryImpl();

		layoutPageTemplateEntryImpl.setLayoutPageTemplateEntryId(getLayoutPageTemplateEntryId());
		layoutPageTemplateEntryImpl.setGroupId(getGroupId());
		layoutPageTemplateEntryImpl.setCompanyId(getCompanyId());
		layoutPageTemplateEntryImpl.setUserId(getUserId());
		layoutPageTemplateEntryImpl.setUserName(getUserName());
		layoutPageTemplateEntryImpl.setCreateDate(getCreateDate());
		layoutPageTemplateEntryImpl.setModifiedDate(getModifiedDate());
		layoutPageTemplateEntryImpl.setLayoutPageTemplateCollectionId(getLayoutPageTemplateCollectionId());
		layoutPageTemplateEntryImpl.setClassNameId(getClassNameId());
		layoutPageTemplateEntryImpl.setClassTypeId(getClassTypeId());
		layoutPageTemplateEntryImpl.setName(getName());
		layoutPageTemplateEntryImpl.setType(getType());
		layoutPageTemplateEntryImpl.setHtmlPreviewEntryId(getHtmlPreviewEntryId());
		layoutPageTemplateEntryImpl.setDefaultTemplate(isDefaultTemplate());

		layoutPageTemplateEntryImpl.resetOriginalValues();

		return layoutPageTemplateEntryImpl;
	}

	@Override
	public int compareTo(LayoutPageTemplateEntry layoutPageTemplateEntry) {
		int value = 0;

		value = getName().compareTo(layoutPageTemplateEntry.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof LayoutPageTemplateEntry)) {
			return false;
		}

		LayoutPageTemplateEntry layoutPageTemplateEntry = (LayoutPageTemplateEntry)obj;

		long primaryKey = layoutPageTemplateEntry.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		LayoutPageTemplateEntryModelImpl layoutPageTemplateEntryModelImpl = this;

		layoutPageTemplateEntryModelImpl._originalGroupId = layoutPageTemplateEntryModelImpl._groupId;

		layoutPageTemplateEntryModelImpl._setOriginalGroupId = false;

		layoutPageTemplateEntryModelImpl._setModifiedDate = false;

		layoutPageTemplateEntryModelImpl._originalLayoutPageTemplateCollectionId = layoutPageTemplateEntryModelImpl._layoutPageTemplateCollectionId;

		layoutPageTemplateEntryModelImpl._setOriginalLayoutPageTemplateCollectionId = false;

		layoutPageTemplateEntryModelImpl._originalClassNameId = layoutPageTemplateEntryModelImpl._classNameId;

		layoutPageTemplateEntryModelImpl._setOriginalClassNameId = false;

		layoutPageTemplateEntryModelImpl._originalName = layoutPageTemplateEntryModelImpl._name;

		layoutPageTemplateEntryModelImpl._originalType = layoutPageTemplateEntryModelImpl._type;

		layoutPageTemplateEntryModelImpl._setOriginalType = false;

		layoutPageTemplateEntryModelImpl._originalDefaultTemplate = layoutPageTemplateEntryModelImpl._defaultTemplate;

		layoutPageTemplateEntryModelImpl._setOriginalDefaultTemplate = false;

		layoutPageTemplateEntryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<LayoutPageTemplateEntry> toCacheModel() {
		LayoutPageTemplateEntryCacheModel layoutPageTemplateEntryCacheModel = new LayoutPageTemplateEntryCacheModel();

		layoutPageTemplateEntryCacheModel.layoutPageTemplateEntryId = getLayoutPageTemplateEntryId();

		layoutPageTemplateEntryCacheModel.groupId = getGroupId();

		layoutPageTemplateEntryCacheModel.companyId = getCompanyId();

		layoutPageTemplateEntryCacheModel.userId = getUserId();

		layoutPageTemplateEntryCacheModel.userName = getUserName();

		String userName = layoutPageTemplateEntryCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			layoutPageTemplateEntryCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			layoutPageTemplateEntryCacheModel.createDate = createDate.getTime();
		}
		else {
			layoutPageTemplateEntryCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			layoutPageTemplateEntryCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			layoutPageTemplateEntryCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		layoutPageTemplateEntryCacheModel.layoutPageTemplateCollectionId = getLayoutPageTemplateCollectionId();

		layoutPageTemplateEntryCacheModel.classNameId = getClassNameId();

		layoutPageTemplateEntryCacheModel.classTypeId = getClassTypeId();

		layoutPageTemplateEntryCacheModel.name = getName();

		String name = layoutPageTemplateEntryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			layoutPageTemplateEntryCacheModel.name = null;
		}

		layoutPageTemplateEntryCacheModel.type = getType();

		layoutPageTemplateEntryCacheModel.htmlPreviewEntryId = getHtmlPreviewEntryId();

		layoutPageTemplateEntryCacheModel.defaultTemplate = isDefaultTemplate();

		return layoutPageTemplateEntryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{layoutPageTemplateEntryId=");
		sb.append(getLayoutPageTemplateEntryId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", layoutPageTemplateCollectionId=");
		sb.append(getLayoutPageTemplateCollectionId());
		sb.append(", classNameId=");
		sb.append(getClassNameId());
		sb.append(", classTypeId=");
		sb.append(getClassTypeId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", htmlPreviewEntryId=");
		sb.append(getHtmlPreviewEntryId());
		sb.append(", defaultTemplate=");
		sb.append(isDefaultTemplate());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(46);

		sb.append("<model><model-name>");
		sb.append(
			"com.liferay.layout.page.template.model.LayoutPageTemplateEntry");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>layoutPageTemplateEntryId</column-name><column-value><![CDATA[");
		sb.append(getLayoutPageTemplateEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>layoutPageTemplateCollectionId</column-name><column-value><![CDATA[");
		sb.append(getLayoutPageTemplateCollectionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classNameId</column-name><column-value><![CDATA[");
		sb.append(getClassNameId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>classTypeId</column-name><column-value><![CDATA[");
		sb.append(getClassTypeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>htmlPreviewEntryId</column-name><column-value><![CDATA[");
		sb.append(getHtmlPreviewEntryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>defaultTemplate</column-name><column-value><![CDATA[");
		sb.append(isDefaultTemplate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static final ClassLoader _classLoader = LayoutPageTemplateEntry.class.getClassLoader();
	private static final Class<?>[] _escapedModelInterfaces = new Class[] {
			LayoutPageTemplateEntry.class
		};
	private long _layoutPageTemplateEntryId;
	private long _groupId;
	private long _originalGroupId;
	private boolean _setOriginalGroupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _layoutPageTemplateCollectionId;
	private long _originalLayoutPageTemplateCollectionId;
	private boolean _setOriginalLayoutPageTemplateCollectionId;
	private long _classNameId;
	private long _originalClassNameId;
	private boolean _setOriginalClassNameId;
	private long _classTypeId;
	private String _name;
	private String _originalName;
	private int _type;
	private int _originalType;
	private boolean _setOriginalType;
	private long _htmlPreviewEntryId;
	private boolean _defaultTemplate;
	private boolean _originalDefaultTemplate;
	private boolean _setOriginalDefaultTemplate;
	private long _columnBitmask;
	private LayoutPageTemplateEntry _escapedModel;
}