package edu.ycp.cs320.entrelink.model;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.entrelink.model.Post;
public class PostTest {
	private Post model;
	
	@Before
	public void setUp() {
		model = new Post();
	}
	
	@Test
	public void testSeparateTags() {
		String str = "this is a test";
		ArrayList<String> list = new ArrayList<String>();
		list = model.separateTags(str);

		assertEquals("this", list.get(0));
		assertEquals("is", list.get(1));
		assertEquals("a", list.get(2));
		assertEquals("test", list.get(3));
		
		str = "marble tulip juicy tree, it's where I want to be";
		list = model.separateTags(str);
		
		assertEquals("marble", list.get(0));
		assertEquals("tulip", list.get(1));
		assertEquals("juicy", list.get(2));
		assertEquals("tree,", list.get(3));
		assertEquals("it's", list.get(4));
		assertEquals("where", list.get(5));
		assertEquals("I", list.get(6));
		assertEquals("want", list.get(7));
		assertEquals("to", list.get(8));
		assertEquals("be", list.get(9));
	}
	
}
