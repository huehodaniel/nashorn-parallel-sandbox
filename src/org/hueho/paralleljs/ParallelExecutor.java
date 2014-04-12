package org.hueho.paralleljs;

import java.util.List;
import java.util.concurrent.Future;

import org.hueho.paralleljs.impl.BasicParallelExecutor;

/**
 * A interface representing a executor service for running tasks
 * in parallel in the Nashorn engine.
 * 
 * This interface makes several assumptions about the tasks passed:
 * - they should not depend on any external function or value
 * - they should not be closures
 * - they should accept one argument only, and output only one result
 * - they should have no side-effects
 * 
 * @author hueho
 *
 */
public interface ParallelExecutor {

	/**
	 * Submit a JS task to be executed in parallel.
	 * 
	 * @param task A JS Function object
	 * @return a Future for the submitted task
	 */
	Future<Object> submit(Object task, Object argument);
	
	/**
	 * Submit several JS tasks to be ran in parallel.
	 * 
	 * @param task A JS function object
	 * @param args An array of parameters to be used for each task
	 * @return a list of Futures
	 * @throws InterruptedException
	 */
	List<Future<Object>> invokeAll(Object task, Object[] args) throws InterruptedException;
	
	/**
	 * Creates a new ParallelExecutor using the default implementation for it
	 */
	public static ParallelExecutor create() {
		return new BasicParallelExecutor();
	}
	
}
