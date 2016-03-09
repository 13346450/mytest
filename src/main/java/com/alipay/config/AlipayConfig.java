package com.alipay.config;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
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
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	public static String partner = "2088911185026223";
	// 商户的私钥
	public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJ93K2shuwTVViPo0vrR4Xd8PJMIshD4OgSf84F4EEM4nf0frc3U2VAbEm6lI+7x+QXrunjo85QNedagRFe+WnEESNDfyAqHfpoOlZWLmr977Y3AXJKCZcEVelhgNOQdg085w7Q7OBxKyg3APip2LtQouzeuLoOkge2SrM5jlghpAgMBAAECgYEAnrkvLmNUk2sFYpjL53wgv52ZbYCqPNnWZeQPrD6lcUNZfimaBdZmt6U6o4ccTSnshEguQ0sbp7ICI+9CF1QRD/p6YDifdDF94rhpjmON23rspVwzWl7/FAooyvvPavoNr/7oHtG3azM4F6vz2C/CAzXJk/1RKT4856K/AplpL0ECQQDSOCUf1q9y7VhQQSpizPF8lA60redZV2JRmlxn7V4QxdIbDrkZSRCYEqCg3C1hgEVXf2WwxWG3awFzU2ubzjzDAkEAwjF3/30kQoW/3c2EKWI/PQqhG0h7Pc2Ow6LeQGK/l5m4mL3H2zTRoyGgWCnmVhIB8iYecU2arRTDr64iEoTDYwJBANF3LFa7PntP0A23wRpNh9pQVQ7nsXwuPEN/gc/0jyQ7WQ2o7/sn+Kbu+KsQeQ+MFowG3zbyllmDACD/4FuRdq0CQF8G4het3z6GQLuDRDcUEqoGN4woTdaSblghrOyI5gSDfqePxurnz+YqIFLEPijEKxuZt6/uL9CeppujdJznRs0CQFlVZeAu0MaCGMjDf9HtLvz2bHgNXKmia68AlbRwFy7xkK7QTWSVr5m+cMwfSO8mPMKHCNhIiYtPEQW8LgNh2v4=";
	
	// 支付宝的公钥，无需修改该值
	public static String ali_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
	

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	// 字符编码格式 目前支持 gbk 或 utf-8
	public static String input_charset = "utf-8";
	
	// 签名方式 不需修改
	public static String sign_type = "RSA";

}
