package com.youxianji.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

public class ProdInfoLockManager {
    public static ProdInfoLockManager prodInfoManager = new ProdInfoLockManager();
    private ProdInfoLockManager(){}
    
    private Lock createLock = new ReentrantLock();
    private Map <String,Lock> lockMap = new ConcurrentHashMap<String,Lock>();
	private Logger logger = Logger.getLogger(ProdInfoLockManager.class);
	
	
    public static ProdInfoLockManager getInstance(){
    	return prodInfoManager;
    }
    
    public Lock getProdInfoLock(String prodId){
		if(!lockMap.containsKey(prodId)){
			this.logger.info("商品ID:" + prodId + ",商品锁不存在.");
			//当不存在对应的商户锁信息需要进行注册
			doCreateProdInfoLock(prodId);
		}
		this.logger.info("商品:" + prodId + ",获取已经注册的商品锁.");
		return lockMap.get(prodId);
	}
    
    /**
	 * 注册新的商品锁
	 * */
	private void doCreateProdInfoLock(String prodId){
		createLock.lock();
		try{
			if(!lockMap.containsKey(prodId)){
				lockMap.put(prodId, new ProdInfoTransactionLock(prodId));
				this.logger.info("商品:" + prodId + ",进行商品锁注册.");
			}
		}finally{
			createLock.unlock();
		}
	}
	
	
	class ProdInfoTransactionLock implements Lock, java.io.Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -3832845138354647453L;
		private String prodId;
		private ReentrantLock innerLock = new ReentrantLock();
		
		public ProdInfoTransactionLock(String prodId){
			this.prodId = prodId;
		}

		@Override
		public void lock() {
			/*在商户获取商户锁与使用锁之间此时删除该商户锁,可能会发生同步现象。此处可能抛出空指针异常.*/
			innerLock.lock();
		}

		@Override
		public void lockInterruptibly() throws InterruptedException {
			innerLock.lockInterruptibly();
			
		}

		@Override
		public boolean tryLock() {
			return innerLock.tryLock();
		}

		@Override
		public boolean tryLock(long time, TimeUnit unit)
				throws InterruptedException {
			return innerLock.tryLock(time, unit);
		}

		@Override
		public void unlock() {
			int queueLength = innerLock.getQueueLength();
			long beginTime = System.currentTimeMillis();
			logger.info("商品:" + prodId + ",等待获取商品锁的线程数:" + queueLength);
			
			innerLock.unlock();
			long endTime = System.currentTimeMillis();
			logger.info("商品:" + prodId + ",移除商户锁耗时：" + (endTime - beginTime));
		}

		@Override
		public Condition newCondition() {

			return  innerLock.newCondition();
		}

		
	}
}
