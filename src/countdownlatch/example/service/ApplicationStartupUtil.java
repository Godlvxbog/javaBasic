package countdownlatch.example.service;

import countdownlatch.example.service.verifier.BaseHealthChecker;
import countdownlatch.example.service.verifier.CacheHealthChecker;
import countdownlatch.example.service.verifier.DatabaseHealthChecker;
import countdownlatch.example.service.verifier.NetworkHealthChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;



public class ApplicationStartupUtil 
{
	private static List<BaseHealthChecker> _services;
	private static CountDownLatch _latch;
	
	private ApplicationStartupUtil()
	{
	}
	
	private final static ApplicationStartupUtil INSTANCE = new ApplicationStartupUtil();
	
	public static ApplicationStartupUtil getInstance()
	{
		return INSTANCE;
	}
	
	public static boolean checkExternalServices() throws Exception
	{
		_latch = new CountDownLatch(3);
		_services = new ArrayList<BaseHealthChecker>();
		_services.add(new NetworkHealthChecker(_latch));
		_services.add(new CacheHealthChecker(_latch));
		_services.add(new DatabaseHealthChecker(_latch));
		
		Executor executor = Executors.newFixedThreadPool(_services.size());
		
		for(final BaseHealthChecker v : _services) 
		{
			executor.execute(v);
		}

		//启动后台线程之后，必须立马在主线程中的countdownlatch.await();实现主线程阻塞，等待后台线程执行完毕，主线程才往下执行
		_latch.await();
		
		for(final BaseHealthChecker v : _services) 
		{
			if( ! v.isServiceUp())
			{
				return false;
			}
		}
		return true;
	}
	
	
}
