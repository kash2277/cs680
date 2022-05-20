package edu.umb.cs680.hw09;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw09.apfs.ApfsDirectory;
import edu.umb.cs680.hw09.apfs.ApfsFile;
import edu.umb.cs680.hw09.apfs.ApfsLink;

public class ApfsFileTest {
	private static ApfsDirectory root;
	private static ApfsDirectory apps;
	private static ApfsDirectory bin;
	private static ApfsDirectory home;
	private static ApfsDirectory pictures;
	private static ApfsFile x;
	private static ApfsFile y;
	private static ApfsFile a;
	private static ApfsFile b;
	private static ApfsFile c;
	private static ApfsLink u;
	private static ApfsLink v;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		root = new ApfsDirectory(null, "root", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		apps = new ApfsDirectory(root, "apps", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		bin = new ApfsDirectory(root, "bin", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		home = new ApfsDirectory(root, "home", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		pictures = new ApfsDirectory(home, "pictures", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		x = new ApfsFile(apps, "x", 15, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		y = new ApfsFile(bin, "y", 30, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		a = new ApfsFile(pictures, "a", 45, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		b = new ApfsFile(pictures, "b", 60, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		c = new ApfsFile(home, "c", 75, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		u = new ApfsLink(home, "u", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime", bin);
		v = new ApfsLink(pictures, "v", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime", y);
		root.appendChild(apps);
		root.appendChild(bin);
		root.appendChild(home);
		apps.appendChild(x);
		apps.appendChild(y);
		home.appendChild(pictures);
		home.appendChild(c);
		home.appendChild(u);
		pictures.appendChild(a);
		pictures.appendChild(b);
		pictures.appendChild(v);
	}
	
	private String[] fileToStringArray(ApfsFile f) {
		String[] fileInfo = { String.valueOf(f.isFile()), f.getOwnerName(), String.valueOf(f.getSize()),
							f.getLastModifiedTime(), f.getParent().getName() };
		return fileInfo;
	}

	@Test
	public void verifyingEqualityForFileInX() {
		String[] expected = { "true", "defaultOwnerName", "15", "defaultLastModifiedTime", "apps" };
		ApfsFile actual = x;
		assertArrayEquals(expected, fileToStringArray(actual));
	}
	
	@Test
	public void verifyingEqualityForFileA() {
		String[] expected = { "true", "defaultOwnerName", "45", "defaultLastModifiedTime", "pictures" };
		ApfsFile actual = a;
		assertArrayEquals(expected, fileToStringArray(actual));
	}
	
	@Test
	public void DirectoyTestingWitFilehB() {
		assertFalse(b.isDirectory());
	}
	
	@Test
	public void TestingLinkWithFileB() {
		assertFalse(b.isLink());
	}
	
	@Test
	public void TestingFileWithFileB() {
		assertTrue(b.isFile());
	}
	
}