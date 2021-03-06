package edu.ncsu.csc.itrust.serverutils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc.itrust.datagenerators.TestDataGenerator;
import edu.ncsu.csc.itrust.server.FindExpertServlet;

public class FindExpertServletTest {
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	private PrintWriter out;
	
	private TestDataGenerator gen;
	private Delegator test;
	
	@Before
	public void setUp() throws Exception{
		gen = new TestDataGenerator();
		gen.clearAllTables();
		gen.standardData();

		
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		session = mock(HttpSession.class);
		test = new Delegator();
	}

	@Test
	public void testDoGetHttpServletRequestHttpServletResponse() throws Exception {
		Writer t = new StringWriter();
		PrintWriter temp = new PrintWriter(t);
		when(request.getParameter("query")).thenReturn("Kelly Doctor");
		when(response.getWriter()).thenReturn(temp);
		test.testDoGet(response, request);
		//System.out.println(t.toString());
		assertTrue(t.toString().contains("fTable"));
		assertTrue(t.toString().contains("Kelly Doctor"));
	}

	private class Delegator extends FindExpertServlet {
		private static final long serialVersionUID = 1L;
        
		public Delegator(){
			super(true);
		}
		
		public void testDoGet(HttpServletResponse r, HttpServletRequest req) throws ServletException, IOException{
			super.doGet(req, r);
		}
	}
}
