package com.gemframework.common.constant;

public interface GemConstant {

	//公共常量
	public interface Common{
		//NULL
		public String NULL= "null";
	}

	//系统常量
	public interface System{
		public String DEF_PASSWORD= "123456";
	}

	//公共常量
	public interface Auth{
		public String CLIENT_ID_NAME = "client_id_name";
		public String CLIENT_ID_PASS = "client_id_pass";
		public String HEADER_AUTHOR = "Authorization";

		public String ROLE_PREFIX = "ROLE_";

		// 匿名的字符出
		public String ANONY_MOUS_USER = "anonymousUser";

		public String COOKIES_TOKEN_NAME = "access_token";

		public String SESSION_USERKEY = "SPRING_SECURITY_CONTEXT";
	}


	//常用的字符集编码类型
	public interface Character{
		// json编码
		public String UTF8 = "UTF-8";
		public String GBK = "GBK";
		public String GB_2312 = "GB2312";
		public String ISO_8859_1 = "iso-8859-1";
	}

	//返回结果
	public interface Result{
		// 自定义标识字段--失败标示
		public String FAILURE = "0";
		// 自定义标识字段--成功标示
		public String SUCCESS = "1";
		// 匿名的字符出
	}

	//数字
	public interface Number{
		// 数值
		public int ZERO = 0;
		public int ONE = 1;
		public int TWO = 2;
		public int THREE = 3;
		public int FOUR = 4;
		public int FIVE = 5;
		public int SIX = 6;
		public int SEVEN = 7;
		public int EIGHT = 8;
		public int NINE = 9;
		public int TEN = 10;
	}

	//媒体类型
	public interface MediaType{
		// json编码
		public String APPLICATION_XML = "application/xml";
		public String APPLICATION_XML_UTF_8 = "application/xml; charset=UTF-8";

		public String JSON = "application/json";
		public String JSON_UTF_8 = "application/json; charset=UTF-8";

		public String JAVASCRIPT = "application/javascript";
		public String JAVASCRIPT_UTF_8 = "application/javascript; charset=UTF-8";

		public String APPLICATION_XHTML_XML = "application/xhtml+xml";
		public String APPLICATION_XHTML_XML_UTF_8 = "application/xhtml+xml; charset=UTF-8";

		public String TEXT_PLAIN = "text/plain";
		public String TEXT_PLAIN_UTF_8 = "text/plain; charset=UTF-8";

		public String TEXT_XML = "text/xml";
		public String TEXT_XML_UTF_8 = "text/xml; charset=UTF-8";

		public String TEXT_HTML = "text/html";
		public String TEXT_HTML_UTF_8 = "text/html; charset=UTF-8";
	}

	//媒体类型
	public interface DBOperType{

		public Integer INSERT = 1;
		public Integer DELETE = 2;
		public Integer UPDATE = 3;
		public Integer SELECT = 4;
	}

}
