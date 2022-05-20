package edu.umb.cs680.hw09;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw09.apfs.ApfsDirectory;
import edu.umb.cs680.hw09.apfs.ApfsFile;
import edu.umb.cs680.hw09.apfs.ApfsLink;

public class ApfsLinkTest {
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
		x = new ApfsFile(apps, "x", 10, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		y = new ApfsFile(bin, "y", 20, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		a = new ApfsFile(pictures, "a", 30, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		b = new ApfsFile(pictures, "b", 40, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		c = new ApfsFile(home, "c", 50, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
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
	
	private String[] linkToStringArray(ApfsLink l) {
		String[] linkInfo = { String.valueOf(l.isLink()), l.getOwnerName(), String.valueOf(l.getSize()),
						l.getLastModifiedTime(), l.getParent().getName(), l.getTarget().getName() };
		return linkInfo;
	}

	@Test
	public void verifyingLinkEqualityU() {
		String[] expected = { "true", "defaultOwnerName", "0", "defaultLastModifiedTime", "home", "bin" };
		ApfsLink actual = u;
		assertArrayEquals(expected, linkToStringArray(actual));
	}
	
	@Test
	public void verifyingLinkEqualityN() {
		String[] expected = { "true", "defaultOwnerName", "0", "defaultLastModifiedTime", "pictures", "y" };
		ApfsLink actual = v;
		assertArrayEquals(expected, linkToStringArray(actual));
	}
	
	@Test
	public void isFileTestingWithU() {
		assertFalse(u.isFile());
	}

	@Test
	public void DirectoyTestingWithU() {
		assertFalse(u.isDirectory());
	}
	
	@Test
	public void isLinkTestingWithU() {
		assertTrue(u.isLink());
	}
	
	@Test
	public void setTargetTestingWithU() {
		u.setTarget(apps);
		assertSame(apps, u.getTarget());
	}
	
	@Test
	public void getTargetTestingWithU() {
		assertSame(bin, u.getTarget());
	}
	

}