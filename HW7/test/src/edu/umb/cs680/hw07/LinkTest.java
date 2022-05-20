package edu.umb.cs680.hw07;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw07.Directory;
import edu.umb.cs680.hw07.File;
import edu.umb.cs680.hw07.Link;

public class LinkTest {
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
	
	private String[] linkToStringArray(Link l) {
		String[] linkInfo = { String.valueOf(l.isLink()), l.getName(), String.valueOf(l.getSize()),
				String.valueOf(l.getCreationTime()), l.getParent().getName(), l.getTarget().getName() };
		return linkInfo;
	}
	
	@Test
	public void setTargetTestingWithU() {
		u.setTarget(apps);
		assertSame(apps, u.getTarget());
	}


	@Test
	public void isDirectoyTestingWithU() {
		assertFalse(u.isDirectory());
	}
	
	@Test
	public void getTargetTestingWithU() {
		assertSame(bin, u.getTarget());
	}
	
	
	@Test
	public void verifyLinkEqualityV() {
		String[] expected = { "true", "v", "0", String.valueOf(v.getCreationTime()), "pictures", "y" };
		Link actual = v;
		assertArrayEquals(expected, linkToStringArray(actual));
	}

	@Test
	public void verifyLinkEqualityU() {
		String[] expected = { "true", "u", "0", String.valueOf(u.getCreationTime()), "home", "bin" };
		Link actual = u;
		assertArrayEquals(expected, linkToStringArray(actual));
	}
	@Test
	public void isFileTestingWithU() {
		assertFalse(u.isFile());
	}
	
	@Test
	public void isLinkTestingWithU() {
		assertTrue(u.isLink());
	}
	


}