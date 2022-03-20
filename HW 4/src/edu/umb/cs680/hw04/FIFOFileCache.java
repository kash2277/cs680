package edu.umb.cs680.hw04;

import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedHashMap;

public class FIFOFileCache extends FileCache{
	private int CacheSize;
	
	public FIFOFileCache(int CacheSize) {
		this.CacheSize = CacheSize;
		cache = new LinkedHashMap<>();
	}

	protected boolean isCached(Path path) {
		if(cache.containsKey(path)) {
			return true;
		}else {
			return false;
		}
	}

	protected boolean isCacheFull() {
		if(cache.size() == CacheSize) {
			return true;
		}else {
			return false;
		}
	}

	protected void cacheFile(Path path) {
		try {
			String StringContent = Files.readString(path);
			cache.put(path, StringContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void replace(Path path) {
		try {
			String StringContent = Files.readString(path);
			Path KeyRM = cache.keySet().iterator().next();
			cache.remove(KeyRM);
			cache.put(path, StringContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}