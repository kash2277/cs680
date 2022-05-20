package edu.umb.cs680.hw08;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw08.ApfsDirectory;
import edu.umb.cs680.hw08.ApfsFile;
import edu.umb.cs680.hw08.ApfsLink;

public class ApfsDirectoryTest {
	private static ApfsDirectory root;
	private static ApfsDirectory bin;
	private static ApfsDirectory home;
	private static ApfsDirectory pictures;
	private static ApfsDirectory apps;

	private static ApfsFile x;
	private static ApfsFile y;
	private static ApfsLink u;
	private static ApfsLink v;
	private static ApfsFile a;
	private static ApfsFile b;
	private static ApfsFile c;

	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		bin = new ApfsDirectory(root, "bin", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		home = new ApfsDirectory(root, "home", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		pictures = new ApfsDirectory(home, "pictures", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		root = new ApfsDirectory(null, "root", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		apps = new ApfsDirectory(root, "apps", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		x = new ApfsFile(apps, "x", 15, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		y = new ApfsFile(bin, "y", 30, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		u = new ApfsLink(home, "u", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime", bin);
		v = new ApfsLink(pictures, "v", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime", y);
		a = new ApfsFile(pictures, "a", 45, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		b = new ApfsFile(pictures, "b", 60, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		c = new ApfsFile(home, "c", 75, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		root.appendChild(apps);
		root.appendChild(bin);
		root.appendChild(home);
		pictures.appendChild(a);
		pictures.appendChild(b);
		pictures.appendChild(v);
		apps.appendChild(x);
		apps.appendChild(y);
		home.appendChild(pictures);
		home.appendChild(c);
		home.appendChild(u);
	}

	private String[] dirToStringArray(ApfsDirectory d) {
		String[] dirInfo = { String.valueOf(d.isDirectory()), d.getOwnerName(), String.valueOf(d.getTotalSize()),
							d.getLastModifiedTime(), String.valueOf(d.countChildren()) };
		return dirInfo;
	}

	@Test
	public void verifyingDirectoryEqualityWithRoot() {
		String[] expected = { "true", "defaultOwnerName", "225", "defaultLastModifiedTime", "3" };
		ApfsDirectory actual = root;
		assertArrayEquals(expected, dirToStringArray(actual));
	}
	

	@Test
	public void countChildrenTestingWithRoot() {
		assertEquals(3, root.countChildren());;
	}
	
	@Test
	public void countChildrenTestingWithHome() {
		assertSame(3, home.countChildren());
	}
	
	@Test
	public void FileTestingWithRoot() {
		assertFalse(root.isFile());
	}
	
	@Test
	public void LinkTestingWithRoot() {
		assertFalse(root.isLink());
	}
	
	@Test
	public void verifyingDirectoryEqualityWithHome() {
		String[] expected = { "true", "defaultOwnerName", "180", "defaultLastModifiedTime", "3" };
		ApfsDirectory actual = home;
		assertArrayEquals(expected, dirToStringArray(actual));
	}
	
	@Test
	public void DirectoyTestingWithRoot() {
		assertTrue(root.isDirectory());
	}
	
	@Test
	public void appendChildrenTestingFromRoot() {
		assertSame(root, apps.getParent());
	}
	
	@Test
	public void appendChildrenTestingFromHome() {
		assertSame(home, c.getParent());
	}
	
	@Test
	public void SubDirectoriesTestingGetFromRoot() {
		ApfsDirectory[] expected = new ApfsDirectory[3];
		expected[0] = apps;
		expected[1] = bin;
		expected[2] = home;
		ApfsDirectory[] actual = new ApfsDirectory[3];
		LinkedList<ApfsDirectory> subDirectories = root.getSubDirectories();
		actual[0] = subDirectories.get(0);
		actual[1] = subDirectories.get(1);
		actual[2] = subDirectories.get(2);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void SubDirectoriesTestingGetFromHome() {
		assertSame(pictures, home.getSubDirectories().get(0));
	}
	
	@Test
	public void FilesTestingGetFromHome() {
		assertSame(c, home.getFiles().get(0));
	}
	
	@Test
	public void FilesTestingGetFromPictures() {
		ApfsFile[] expected = new ApfsFile[2];
		expected[0] = a;
		expected[1] = b;
		ApfsFile[] actual = new ApfsFile[2];
		LinkedList<ApfsFile> files = pictures.getFiles();
		actual[0] = files.get(0);
		actual[1] = files.get(1);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void getTotalSizeTestingWithRoot() {
		assertEquals(225, root.getTotalSize());
	}
	
	@Test
	public void getTotalSizeTestingWithHome() {
		assertEquals(180, home.getTotalSize());
	}
	@Test
	public void getLinksTestingWithHome() {
		assertSame(u, home.getLinks().get(0));
	}
	
	@Test
	public void getLinksTestingWithPictures() {
		assertSame(v, pictures.getLinks().get(0));
	}
	


}