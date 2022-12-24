package org.example.exam;

import org.apache.commons.lang.StringUtils;
import org.example.Callable.MyCallable;
import java.io.*;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class test {
	/**
	 * 读取文件内容并对前十名进行排序
	 *
	 * @param args
	 */
	public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
		MyCallable myCallable = new MyCallable();
		FutureTask<String> stringFutureTask = new FutureTask<>(myCallable);
		Thread thread = new Thread(stringFutureTask);
		thread.start();
		String str = stringFutureTask.get();
	}
}
