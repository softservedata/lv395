package com.softserve.edu;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpRequest {

	public static void main(String[] args) throws Exception {
//		OkHttpClient client = new OkHttpClient();
//
//		MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
//		RequestBody body = RequestBody.create(mediaType, "------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"username\"\r\n\r\nlv298\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"key\"\r\n\r\nyFfTWUqt6dRyQAFpBsdwctgMGViK3ewtGY0FJCH3gyISdvkeIb8U8mvqiFm1WjJhwJF3M1x4NFAdoutTjpL7DkO54yoQFdmAnAVfqU3DgHtMKnuypPnLCcoFmrFK8RK5D1SyJFkfeJDsd3A0Q00a2hQAnNlGztQ36Q2PQO0yRCfQCVxPUfFkrUOeibFjYpgtvXW7HsAM5q6HrFmmZ3XKsdTIPTYlKlb38lciZAInSTd50vSYoeZQQPlDnqWHqdHc\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
//		Request request = new Request.Builder()
//		  .url("http://192.168.103.210/opencart/upload/index.php?route=api/login")
//		  .post(body)
//		  .addHeader("content-type", "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW")
//		  .addHeader("Content-Type", "application/json")
//		  .addHeader("Cache-Control", "no-cache")
//		  .addHeader("Postman-Token", "46772ac7-0ea9-4caf-a13e-fcd2014f3195")
//		  .build();
//
//		Response response = client.newCall(request).execute();
//		//
//		System.out.println(response.body().string());
//		System.out.println(response.headers("Set-Cookie"));
//		System.out.println(response.headers("Set-Cookie").get(0));
		//
		//
		OkHttpClient client = new OkHttpClient();
		//RequestBody formBody = new FormBody.Builder()
//			      //.add("username", "test")
//			      //.add("password", "test")
		//		  .add("token", "SG1B5Z6BQPJWLGI6WW861FV9OA719YVB")
		//		  .add("time", "1900000")
		//	      .build();
//		MediaType mediaType = MediaType.parse("multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW");
//		RequestBody formBody = RequestBody.create(mediaType,
//				"------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"token\"\r\n\r\n7UFHG5MA0OZJS5YD4E8B1Y0CLCHGH6QQ\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW\r\nContent-Disposition: form-data; name=\"time\"\r\n\r\n12000000\r\n------WebKitFormBoundary7MA4YWxkTrZu0gW--");
		Request request = new Request.Builder()
//		  .url("http://localhost:8080/login/users?token=NQP2JT7XG9J2ORZN05SADLFOJSTQ48SK")
//		  .get()
		  //.post(formBody)
		  //.addHeader("Cache-Control", "no-cache")
		  //.addHeader("Postman-Token", "202bdba9-3cc8-462c-952b-d916469de416")
		  //.url("http://localhost:8080/tokenlifetime?token=SG1B5Z6BQPJWLGI6WW861FV9OA719YVB&time=1800000")
		  //.url("http://localhost:8080/tokenlifetime")
		  .url("https://api.github.com/orgs/dotnet/repos")
		  .get()
		  //.put(formBody)
		  .build();
		Response response = client.newCall(request).execute();
		System.out.println(response.body().string());
	}
	
}
