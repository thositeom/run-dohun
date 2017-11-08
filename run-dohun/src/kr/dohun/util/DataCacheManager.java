package com.saltware.enface.util;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * 데이터를 캐시하는 캐시매니저
 * 
 * @author smna
 * 
 */
public class DataCacheManager {

	private class DataCacheEntry {
		// 데이터 생성시간(캐시시간)
		private long createTime = 0;
		// 데이터
		private Object data = null;

		public DataCacheEntry() {
		}

		public DataCacheEntry(Object data) {
			this.createTime = System.currentTimeMillis();
			this.data = data;
		}

		public long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(long createTime) {
			this.createTime = createTime;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}
	}

	private int maxCacheSize = 100;
	private int maxCacheTime = 60;
	private Hashtable<String, DataCacheEntry> dataCache = new Hashtable<String, DataCacheEntry>();
	private ArrayList keyList = new ArrayList<String>();

	/**
	 * 데이터 캐시매니저 생성자
	 * 
	 * @param expireTime
	 *            폐기시간
	 */
	public DataCacheManager(  int maxCacheTime, int maxCacheSize) {
		this.maxCacheTime =maxCacheTime;
		this.maxCacheSize = maxCacheSize;
	}

	
	/**
	 * 캐시에서 데이터를 가져온다. 데이터가 존재하지 않거나 폐기사간이 지났으면 null을 리턴한다.
	 * 
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		if( maxCacheSize <= 0) {
			return null;
		}
		DataCacheEntry dataCacheEntry = dataCache.get(key);
		if (dataCacheEntry == null) {
			return null;
		} else {
			if ( maxCacheTime > 0 && System.currentTimeMillis() - dataCacheEntry.getCreateTime() > (maxCacheTime * 1000)) {
				remove( key);
				return null;
			}
			// 키를 뒤로 옮긴다.
			keyList.remove(key);
			keyList.add( key);
			return dataCacheEntry.getData();
		}
	}
	
	/**
	 * 캐시에 데이터를 넣는다.
	 * 
	 * @param key
	 * @return
	 */
	public void put(String key, Object value) {
		if( maxCacheSize <= 0) {
			return;
		}
		
		DataCacheEntry entry = new DataCacheEntry(value);
		remove(key);
		if( maxCacheSize > 0) {
			if( dataCache.size() >= maxCacheSize) {
				// 캐시가 모두 찼으면 폐기된 데이터를 찾아 지운다.
				clearExpired();
			}
			if( dataCache.size() >= maxCacheSize) {
				// 캐시가 풀이면 가장 오래된 캐시를 지운다.
				remove( (String)keyList.get(0));
			}
		}
		// 캐시를 추가한다.
		dataCache.put(key, entry);
		keyList.add( key);
	}
	
	/**
	 * 캐시에서 데이터를 삭제한다.
	 * @param key
	 */
	public void remove( String key) {
		dataCache.remove(key);
		keyList.remove(key);
	}

	public void clearExpired() {
		Iterator it = dataCache.keySet().iterator();
		String key;
		DataCacheEntry entry;
		while( it.hasNext()) {
			key = (String)it.next();
			entry = dataCache.get(key);
		}
	}


	public int getMaxCacheSize() {
		return maxCacheSize;
	}


	public void setMaxCacheSize(int maxCacheSize) {
		this.maxCacheSize = maxCacheSize;
	}


	public int getMaxCacheTime() {
		return maxCacheTime;
	}


	public void setMaxCacheTime(int maxCacheTime) {
		this.maxCacheTime = maxCacheTime;
	}
}
