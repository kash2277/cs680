package edu.umb.cs680.hw07;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw07.Directory;
import edu.umb.cs680.hw07.File;
import edu.umb.cs680.hw07.Link;

public class FileTest {
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
	private static Link u;
	private static Link v;
	

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
		u = new Link(home, "u", 0, LocalDateTime.now(), bin);
		v = new Link(pictures, "v", 0, LocalDateTime.now(), y);
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
	
	private String[] fileToStringArray(File f) {
		String[] fileInfo = { String.valueOf(f.isFile()), f.getName(), String.valueOf(f.getSize()),
				String.valueOf(f.getCreationTime()), f.getParent().getName() };
		return fileInfo;
	}
	
	@Test
	public void TestingWithBGETFile() {
		assertTrue(b.isFile());
	}
	@Test
	public void DirectoyTestingWithBGETDir() {
		assertFalse(b.isDirectory());
	}
	
	@Test
	public void LinkTestingWithBGETLink() {
		assertFalse(b.isLink());
	}
	
	@Test
	public void verifyingFileEqualityWithX() {
		String[] expected = { "true", "x", "17", String.valueOf(x.getCreationTime()), "apps" };
		File actual = x;
		assertArrayEquals(expected, fileToStringArray(actual));
	}

	
	@Test
	public void verifyingFileEqualityWithA() {
		String[] expected = { "true", "a", "51", String.valueOf(a.getCreationTime()), "pictures" };
		File actual = a;
		assertArrayEquals(expected, fileToStringArray(actual));
	}

	

}