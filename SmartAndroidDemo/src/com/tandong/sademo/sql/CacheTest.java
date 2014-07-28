package com.tandong.sademo.sql;

import java.util.Collection;

import android.test.AndroidTestCase;

import com.tandong.sa.sql.ActiveAndroid;
import com.tandong.sa.sql.Cache;
import com.tandong.sa.sql.Configuration;
import com.tandong.sa.sql.Model;
import com.tandong.sa.sql.TableInfo;
import com.tandong.sa.sql.annotation.Table;

public class CacheTest extends AndroidTestCase {

	@Override
	protected void setUp() {
		Configuration conf = new Configuration.Builder(getContext()).setDatabaseName("CacheTest")
				.addModelClasses(CacheTestModel.class, CacheTestModel2.class).create();
		ActiveAndroid.initialize(conf, true);
	}

	public void testGetTableInfos() {
		assertNotNull(Cache.getContext());
		Collection<TableInfo> tableInfos = Cache.getTableInfos();
		assertEquals(2, tableInfos.size());

		{
			TableInfo tableInfo = Cache.getTableInfo(CacheTestModel.class);
			assertNotNull(tableInfo);
			assertEquals("CacheTestModel", tableInfo.getTableName());
		}

		{
			TableInfo tableInfo = Cache.getTableInfo(CacheTestModel2.class);
			assertNotNull(tableInfo);
			assertEquals("CacheTestModel2", tableInfo.getTableName());
		}
	}

	@Table(name = "CacheTestModel")
	private static class CacheTestModel extends Model {
	}

	@Table(name = "CacheTestModel2")
	private static class CacheTestModel2 extends Model {
	}
}
