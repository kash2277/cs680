package edu.umb.cs680.hw04;

import java.io.IOException;
import java.nio.file.*;
import java.util.LinkedHashMap;

public class LRUFileCache extends FileCache{
	private int Size;
	
	public LRUFileCache(int Size) {
		this.Size = Size;
		cache = new LinkedHashMap<>(Size, 0.75f, true);
	}

	protected boolean isCached(Path path) {
		if(cache.containsKey(path)) {
			return true;
		}else {
			return false;
		}
	}

	protected boolean isCacheFull() {
		if(cache.size() == Size) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	protected void cacheFile(Path path) {
		try {
			String StringContent = Files.readString(path);
			cache.put(path, StringContent);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
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
