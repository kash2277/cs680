package edu.umb.cs680.hw06;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw06.Directory;
import edu.umb.cs680.hw06.File;

public class DirectoryTest {
	private static Directory root;
	private static Directory apps;
	private static Directory bin;
	private static Directory home;
	private static Directory pictures;
	private static File x;
	private static File y;
	private static File a;
	private static File b;
	private static File c;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		root = new Directory(null, "root", 0, LocalDateTime.now());
		apps = new Directory(root, "apps", 0, LocalDateTime.now());
		bin = new Directory(root, "bin", 0, LocalDateTime.now());
		home = new Directory(root, "home", 0, LocalDateTime.now());
		pictures = new Directory(home, "pictures", 0, LocalDateTime.now());
		x = new File(apps, "x", 17, LocalDateTime.now());
		y = new File(bin, "y", 34, LocalDateTime.now());
		a = new File(pictures, "a", 51, LocalDateTime.now());
		b = new File(pictures, "b", 68, LocalDateTime.now());
		c = new File(home, "c", 85, LocalDateTime.now());
		root.appendChild(apps);
		root.appendChild(bin);
		root.appendChild(home);
		apps.appendChild(x);
		apps.appendChild(y);
		home.appendChild(pictures);
		home.appendChild(c);
		pictures.appendChild(a);
		pictures.appendChild(b);
	}

	private String[] dirToStringArray(Directory d) {
		String[] dirInfo = { String.valueOf(d.isDirectory()), d.getName(), String.valueOf(d.getTotalSize()),
				String.valueOf(d.getCreationTime()), String.valueOf(d.countChildren()) };
		return dirInfo;
	}

	@Test
	public void verifyingDirectoryEqualityForRoot() {
		String[] expected = { "true", "root", "255", String.valueOf(root.getCreationTime()), "3" };
		Directory actual = root;
		assertArrayEquals(expected, dirToStringArray(actual));
	}
	
	@Test
	public void verifyingDirectoryEqualityForHome() {
		String[] expected = { "true", "home", "204", String.valueOf(home.getCreationTime()), "2" };
		Directory actual = home;
		assertArrayEquals(expected, dirToStringArray(actual));
	}
	
	@Test
	public void DirecTestingWithRoot() {
		assertTrue(root.isDirectory());
	}
	
	@Test
	public void appendChildrenTestingWithRoot() {
		assertSame(root, apps.getParent());
	}
	
	@Test
	public void appendChildrenTestingWithHome() {
		assertSame(home, c.getParent());
	}
	
	@Test
	public void ChildrenTestingWithInRoot() {
		assertEquals(3, root.countChildren());;
	}
	
	@Test
	public void ChildrenTestingWithInHome() {
		assertSame(2, home.countChildren());
	}
	
	@Test
	public void SubDirectoriesTestingWithInRoot() {
		Directory[] expected = new Directory[3];
		expected[0] = apps;
		expected[1] = bin;
		expected[2] = home;
		Directory[] actual = new Directory[3];
		LinkedList<Directory> subDirectories = root.getSubDirectories();
		actual[0] = subDirectories.get(0);
		actual[1] = subDirectories.get(1);
		actual[2] = subDirectories.get(2);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void SubDirectoriesTestingGetWithInHome() {
		assertSame(pictures, home.getSubDirectories().get(0));
	}
	
	@Test
	public void TestingFilesGetWithHome() {
		assertSame(c, home.getFiles().get(0));
	}
	
	@Test
	public void TotalSizeForTestingWithInRoot() {
		assertEquals(255, root.getTotalSize());
	}
	
	@Test
	public void TotalSizeForTestingWithInHome() {
		assertEquals(204, home.getTotalSize());
	}
	
	@Test
	public void TestingFilesGetWithPictures() {
		File[] expected = new File[2];
		expected[0] = a;
		expected[1] = b;
		File[] actual = new File[2];
		LinkedList<File> files = pictures.getFiles();
		actual[0] = files.get(0);
		actual[1] = files.get(1);
		assertArrayEquals(expected, actual);
	}
	


}