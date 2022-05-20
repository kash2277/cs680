package edu.umb.cs680.hw12;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw12.ApfsDirectory;
import edu.umb.cs680.hw12.ApfsFSElement;
import edu.umb.cs680.hw12.ApfsFile;
import edu.umb.cs680.hw12.ApfsLink;
import edu.umb.cs680.hw12.ElementKindComparator;
import edu.umb.cs680.hw12.ReverseAlphabeticalComparator;

public class ApfsDirectoryTest {
	private static ApfsFile x;
	private static ApfsFile y;
	private static ApfsLink m;
	private static ApfsLink n;
	private static ApfsFile a;
	private static ApfsFile b;
	private static ApfsFile c;
	private static ApfsDirectory root;
	private static ApfsDirectory apps;
	private static ApfsDirectory bin;
	private static ApfsDirectory home;
	private static ApfsDirectory pictures;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		apps = new ApfsDirectory(root, "apps", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		root = new ApfsDirectory(null, "root", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		home = new ApfsDirectory(root, "home", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		pictures = new ApfsDirectory(home, "pictures", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		bin = new ApfsDirectory(root, "bin", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		a = new ApfsFile(pictures, "a", 30, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		b = new ApfsFile(pictures, "b", 40, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		c = new ApfsFile(home, "c", 50, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		x = new ApfsFile(apps, "x", 10, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		y = new ApfsFile(bin, "y", 20, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime");
		n = new ApfsLink(pictures, "n", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime", y);
		m = new ApfsLink(home, "m", 0, LocalDateTime.now(), "defaultOwnerName", "defaultLastModifiedTime", bin);		root.appendChild(apps);
		root.appendChild(bin);
		root.appendChild(home);
		pictures.appendChild(a);
		pictures.appendChild(b);
		pictures.appendChild(n);
		apps.appendChild(x);
		apps.appendChild(y);
		home.appendChild(pictures);
		home.appendChild(c);
		home.appendChild(m);

	}

	private String[] dirToStringArray(ApfsDirectory d) {
		String[] dirInfo = { String.valueOf(d.isDirectory()), d.getOwnerName(), String.valueOf(d.getTotalSize()),
							d.getLastModifiedTime(), String.valueOf(d.countChildren()) };
		return dirInfo;
	}

	@Test
	public void FirstLinkTestingWithHome() {
		assertSame(m, home.getLinks().get(0));
	}
	
	@Test
	public void SecondLinksTestingWithPictures() {
		assertSame(n, pictures.getLinks().get(0));
	}
	
	@Test
	public void SizeTestingForAllWithRoot() {
		assertEquals(150, root.getTotalSize());
	}
	
	@Test
	public void getChildrenOfHome() {
		ApfsFSElement[] expected = new ApfsFSElement[3];
		expected[0] = c;
		expected[1] = m;
		expected[2] = pictures;
		ApfsFSElement[] actual = new ApfsFSElement[3];
		LinkedList<ApfsFSElement> children = home.getChildren();
		actual[0] = children.get(0);
		actual[1] = children.get(1);
		actual[2] = children.get(2);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void ChildrenGetForHomeBasedReverseAlphabetical() {
		ApfsFSElement[] expected = new ApfsFSElement[3];
		expected[0] = pictures;
		expected[1] = m;
		expected[2] = c;
		ApfsFSElement[] actual = new ApfsFSElement[3];
		LinkedList<ApfsFSElement> children = home.getChildren(new ReverseAlphabeticalComparator());
		actual[0] = children.get(0);
		actual[1] = children.get(1);
		actual[2] = children.get(2);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void ChildrenGetForHomeBasedElementKind() {
		ApfsFSElement[] expected = new ApfsFSElement[3];
		expected[0] = pictures;
		expected[1] = c;
		expected[2] = m;
		ApfsFSElement[] actual = new ApfsFSElement[3];
		LinkedList<ApfsFSElement> children = home.getChildren(new ElementKindComparator());
		actual[0] = children.get(0);
		actual[1] = children.get(1);
		actual[2] = children.get(2);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	public void SubDirectoriesGetForRoot() {
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
	public void SubDirectoriesGetForRootBasedElementKind() {
		ApfsDirectory[] expected = new ApfsDirectory[3];
		expected[0] = apps;
		expected[1] = bin;
		expected[2] = home;
		ApfsDirectory[] actual = new ApfsDirectory[3];
		LinkedList<ApfsDirectory> subDirectories = root.getSubDirectories(new ElementKindComparator());
		actual[0] = subDirectories.get(0);
		actual[1] = subDirectories.get(1);
		actual[2] = subDirectories.get(2);
		assertArrayEquals(expected, actual);
	}
	

	@Test
	public void FilesGetForPictures() {
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
	public void FilesGetForPicturesBasedReverseAlphabetical() {
		ApfsFile[] expected = new ApfsFile[2];
		expected[0] = b;
		expected[1] = a;
		ApfsFile[] actual = new ApfsFile[2];
		LinkedList<ApfsFile> files = pictures.getFiles(new ReverseAlphabeticalComparator());
		actual[0] = files.get(0);
		actual[1] = files.get(1);
		assertArrayEquals(expected, actual);
	}
	


}