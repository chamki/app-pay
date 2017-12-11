package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10REPOSITORY
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */

public class AlipayConfig {

	// ↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088911703248001";
	// 商户的私钥
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAK79HdZYjRQz2j5mzJkHgSaCjy+kU6zBaug24LdKZAAzSM1sXfFLYhc+YBJ20sdLeDVZwl1duJ4lfZTbNxzsaxvEIWARgsZ+EoEu/uTOnGXZXIjYYZ/TaESBoDBHdHe6InEvr90sakEIvG7RKfXkphJetNTMOuKXvix5e1QlRX+9AgMBAAECgYBFIuI3/rJ2qenvP6RUTb+8HFdAJnecyKpZZt0v7adG4PxBlTAklnkB3/YLNYEflnS+92CwjMfVS9CrEFVdCxdhsLqbdn6FmxezlTibV/Nh92wBYrjRRPihQ//zy1gerg/BXurwz16BxNnnytvQ2rS1zXldaLGDygt//APD/D8g4QJBANgOGrPgh37Eq7wN4EHUoeYv2jlIvfdkePaFNewiwfN82Ogq5dfLyjwjdCzEX/cUSdlB8EWNOOHS77a8e4k0GNkCQQDPV1cIVItd967chWxvcuNA6kEbjP5Q28fb97vAE6t96jmbMExD7UfVSpR2yGXIqAOGLcPZeLbQpmotAHmY0u+FAkEAluLawS6YUe715KJoMHFYE9Ltez4S9Rgk5j1H2D655tsU/Kcg12FKPk+gTbdjMcHohSLosxoaVJOkt6DQQZSxmQJBALu97BKg6ykE8xK+xXcM4nWPJ7EYs9bni0rEQP8lUG1NPygkTYpcOmUJiPEdd/x/FeHTYrlyMqcvaIijG3ej2DkCQDeyi7ArJJ/u/wG7A1J3vUk2O9bAIoYpEyoemN0ssZjeObb2d4lBiymqwdD2FNKYEoAu3yAM407cEejq5ytS/EI=";

	// 支付宝的公钥，无需修改该值
	public static String ali_public_key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	// ↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	// public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";

	// 签名方式 不需修改
	public static String sign_type = "RSA";
	
//	public static String ALIPAY_PUBLIC_KEY = "PUBLIC_KEY";
	
	public static String CHARSET = "UTF-8";
	
	//public static Object SIGNTYPE = "SIGNTYPE";
	
	// 商户appid
		public static String APPID = "2016032701244596";
		// 私钥 pkcs8格式的
		public static String RSA_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQChNOcSR+cPzS8LTd9QqzEl/NyI/zKhe0LkUPH7dJnaXEmZMPPeYOpC0Ae6SKGFjXn6HhEjTdmRzXs334Acnpn6bb0Cu1o06sY0FLNULaTUkzJfyKlUgbQ5w+K8RWcvfR7T+emfbtSYGq84Z5eC5CpDl/gUfijGpxTcK0fXhIFqSkH9XgJ5D0lb+ZngjRwZlJthjj/iAjAgsMtmNOMnvWsMZt7C8Mw4NHbStjeRCdD6XxEGspJp+K3vMfOOKaHCPJN/V3EZR4OHSz/aiiTfHHs33Cy83Ehv7UlBgR+6YsZrj1/SUN62doLY67CHl750B2/mTL1ODohW7i837USmR1TdAgMBAAECggEAK/iWHrJmIDVDY73177cCTO5P6T5SvSE1+bjqiEAhxyHtIU/s9PdSbq73rKQrCpawcrzIx+xwMAjwVykrdKL+NQTBzYD09gIj1gjfcVKR5znPP7koFtjaI/d+XQRBRP7H7eH5C3fbnTGKIcR/20AJFy7YiB5xWN4yHmvfyLVn52mxv64Zepmi7WRamyCPWqIgNWC4JUeWjhzB9j/xWUNNB9VyibgUqyS05rq7kG7avpFCltGiezx0LAhEEGU0a7n5s+fOXK7H+G0JKTtcI1ENb4DxZEpqeEBeWBzP6DkacYRtEKKjN3cB3bA8fOxkNXigEVxmJi5kSSGZxdQmE5M3MQKBgQDsriVEhxf3rJQmTgPxgVrqQLIA4Gp5g9gWET+R5fbVhMa83K1k4TeTQAtz/ZM+ar9doEu5EY0xIa/+loeFeseclN8/I5kXvBikCvFiRFOKnR14VXb0YB0ToTIDK37ONwVpMw1W14BdMBsc0i/RF1r6f7lF4L6tWI8oF9oJs2zJCwKBgQCuXZobeqg+96QeoN5eY/Yr1jTmMCr92VkFtwAXq3RZc6fGQf51GVTC30KloPKLuVN/RViRGTGyacVSKHpYEdzMjnNZ0m+DsTvdp4xPXoFwdAtk/cSNIp5zQaGoJKwjIV15cUT3BzMxOIJL6nAaPt/R4paRHD9+i32iBy/CQZmatwKBgCJwNHwELDido/Du6SP67n+naj82aEfGJ2sOl3FjRMdMyp4wfgpvuuZyKzh1bTdn0PCOonYLV8AOZs1kmdOSSfk+sD0/sMcDV5KHBRiHWKCSu/cGeIiWc6Snj99mNM1MtDzacNZyBPuKRGetLcf+3f4VF6BTU2tlt27eCZvfv7SBAoGAWCDvFxxzlP/Nb72XJtYXQijOK9oEoLEOEZlg7pW2BNAz6DzKw5y9/pChOt2SOZS94wHFtfCp8acMGG5lB8ZfdvQ40RlPS5x0yZ85h5QYkCHW8IBiq4Ln2xE9B6MhbdAbUpoxZTm6YhJRqNUhO+KzdX205DsGzIrsQMypEuHdJRkCgYBzUQt/UcRUO7lF97owLv1tnLejQP8t8HUEkSV/7PUtzdWwHKfZpXnY0kLtqtkfmLKo9yoV3qeYZ2b49lJZz+Zl3SwcVTQnKIn0DUCp1vV1MGtXh3dHEGJAn3C3qB1VlHUgSb98NLx//JGE/chVAbLeQn7hFeQf4BdREPNoNP2QWw==";
		// 服务器异步通知页面路径 需http:或者https:格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String NOTIFY_OLD_URL = "http://112.94.22.2:8082/shaogongWeb/api/old_student_register/alipay/notify.jspx";
		
		//新生报名支付支付宝回调接口
		public final static String NOTIFY_NEW_URL = "http://112.94.22.2:8082/shaogongWeb/api/new_student_register/alipay/notify.jspx";
		
		// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
		public static String RETURN_URL = "http://112.94.22.2:8082/shaogongWeb";
		// 请求网关地址
		public static String URL = "https://openapi.alipay.com/gateway.do";
	
		// 返回格式
		public static String FORMAT = "json";
		// 支付宝公钥
		public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoTTnEkfnD80vC03fUKsxJfzciP8yoXtC5FDx+3SZ2lxJmTDz3mDqQtAHukihhY15+h4RI03Zkc17N9+AHJ6Z+m29ArtaNOrGNBSzVC2k1JMyX8ipVIG0OcPivEVnL30e0/npn27UmBqvOGeXguQqQ5f4FH4oxqcU3CtH14SBakpB/V4CeQ9JW/mZ4I0cGZSbYY4/4gIwILDLZjTjJ71rDGbewvDMODR20rY3kQnQ+l8RBrKSafit7zHzjimhwjyTf1dxGUeDh0s/2ook3xx7N9wsvNxIb+1JQYEfumLGa49f0lDetnaC2Ouwh5e+dAdv5ky9Tg6IVu4vN+1EpkdU3QIDAQAB";
		// 日志记录目录
		public static String LOG_PATH = "D:\\logs";
		// RSA2
		public static String SIGNTYPE = "RSA2";
		// 设置未付款交易的超时时间 默认30分钟，一旦超时，该笔交易就会自动被关闭。 取值范围：1m～15d。 m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
		// 该参数数值不接受小数点，如1.5h，可转换为90m。
		public static String TIME_OUT_EXPRESS = "1d";
		
		public final static String NOTIFY_ACTIVITY_URL = "http://112.94.22.2:8082/shaogongWeb/api/activity/alipay/notify.jspx";

}
