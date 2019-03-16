/**
 * 
 */
package cn.nju.game.util.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.junit.Test;

import cn.nju.game.ui.util.ModelToTableModelUtil;

/**
 * 
 * @author frank
 *
 */
public class Model2TableModelTest {

	class TestObject {
		
		String a;
		
		String b;
		
		int c;
		
		Object cc = new Object() {
			String d = "1";
			String e = "2";
			public String getD() {
				return d;
			}
			public void setD(String d) {
				this.d = d;
			}
			public String getE() {
				return e;
			}
			public void setE(String e) {
				this.e = e;
			}
			
		};

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public String getB() {
			return b;
		}

		public void setB(String b) {
			this.b = b;
		}

		public int getC() {
			return c;
		}

		public void setC(int c) {
			this.c = c;
		}

		public Object getCc() {
			return cc;
		}

		public void setCc(Object cc) {
			this.cc = cc;
		}

		public TestObject(String a, String b, int c) {
			super();
			this.a = a;
			this.b = b;
			this.c = c;
		}
		
		
	}
	
	@Test
	public void testTransform() {
		ModelToTableModelUtil util = new ModelToTableModelUtil("dd") {
			
			@Override
			protected InputStream getHeader2IndexFile() {
				return getClass().getResourceAsStream("/testobject-index.json");
			}
			
			@Override
			protected InputStream getAttr2HeaderFile() {
				return getClass().getResourceAsStream("/testobject-header.json");
			}
		};
		TestObject test = new TestObject("a val", "b val", 1000);
		try {
			Object[] values = util.transferModelToValues(test);
			Object[] headers = util.transferModelToHeader(test);
			System.out.println(Arrays.toString(headers));
			System.out.println(Arrays.toString(values));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
