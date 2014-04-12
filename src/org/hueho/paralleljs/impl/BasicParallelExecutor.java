package org.hueho.paralleljs.impl;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.script.Bindings;

import nashorn.NashornEngine;

import org.hueho.paralleljs.ParallelExecutor;

public class BasicParallelExecutor implements ParallelExecutor {

	private static final String CALL_TASK = "task(argument);";
	
	private final ExecutorService executor;
	private final NashornEngine engine;
	
	public BasicParallelExecutor(ExecutorService executor, NashornEngine engine) {
		this.executor = executor;
		this.engine = engine;
	}
	
	public BasicParallelExecutor(ExecutorService executor) {
		this(executor, new NashornEngine());
	}
	
	public BasicParallelExecutor(NashornEngine engine) {
		this(Executors.newWorkStealingPool(), engine);
	}
	
	public BasicParallelExecutor() {
		this(Executors.newWorkStealingPool(), new NashornEngine());
	}
	
	@Override
	public Future<Object> submit(Object task, Object argument) {
		Bindings binding = engine.createBindings();
		binding.put("task", task);
		binding.put("argument", argument);
		return executor.submit(() -> engine.eval(CALL_TASK, binding));
	}

	@Override
	public List<Future<Object>> invokeAll(Object task, Object[] args) throws InterruptedException {
		return executor.invokeAll(Arrays.stream(args).map((Object argument) -> {
			Bindings binding = engine.createBindings();
			binding.put("task", task);
			binding.put("argument", argument);
			return (Callable<Object>) () -> engine.eval(CALL_TASK, binding);
		}).collect(Collectors.toList()));
	}

}
