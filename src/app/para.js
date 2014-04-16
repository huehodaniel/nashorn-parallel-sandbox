/**
 * Parallel JS - Nashorn proof-of-concept of JS parallel execution
 */

var System = Java.type('java.lang.System');
var BigDecimal = Java.type('java.math.BigDecimal');
var NANOSEC = BigDecimal.valueOf(100000000);

function time() {
	return BigDecimal.valueOf(System.nanoTime());
}

function println(arg) {
	System.out.println(arg);
}

var executor = function() {
	var BasicParallelExecutor = Java.type('org.hueho.paralleljs.impl.BasicParallelExecutor');
	return new BasicParallelExecutor(Java.type('app.Main').ENGINE);
}();

function squareSum(params) {
	var values = params.values,
		start = params.start || 0,
		end = params.end || values.length,
		total = 0;
	
	for(var i = start, len = values.length; i < end && i < len; i++) {
		total += Math.pow(values[i], 2);
	}
	
	return total/Math.pow(values.length, 2);
}

function randomParams(n) {
	var result = new Array(n);
	for(var i = 0; i < n; i++) {
		result[i] = Math.random();
	}
	return result;
}

function partition(arr, n) {
	var result = [], len = Math.ceil(arr.length / n);
	for(var i = 0; i < n; i++) {
		result.push({
			values: arr,
			start: i*len,
			end: (i+1)*len
		});
	}
	return result;
}

function sequential(array) {
	var start = time();
	
	var result = squareSum({ values: array });
	return { 
		timeEllapsed: time().subtract(start).divide(NANOSEC),
		value: result
	}
}

function parallel(array, tasks) {
	var start = time();
	
	var params = partition(array, tasks);
		
	var result = executor.invokeAll(squareSum, params)
		.stream()
		.map(function(e){ return e.get(); })
		.reduce(function(pe, e){ return pe + e; }).get();
	
	return { 
		timeEllapsed: time().subtract(start).divide(NANOSEC),
		value: result
	}
}


var n = 2000000;
var tasks = 4;
var runs = 10;

function printResults(results) {
	println("Value: " + results.value + ", time ellapsed: " + results.timeEllapsed + " seconds.");
}

function mean(arr) {
	return arr.reduce(function(pe, e) {
		return pe + e;
	}, 0) / arr.length;
}

var summaryParallel = new Array(runs), summarySequential = new Array(runs);
for(var i = 1; i <= runs; i++) {
	println("RUN #" + i);
	println("----------------");
	
	var array = randomParams(n);
	
	println("Running sequential test...");
	var seqResults = sequential(array);
	printResults(seqResults);
	
	println("Running parallel test...");
	var parResults = parallel(array, tasks);
	printResults(parResults);
	
	println("----------------");
	summaryParallel[i - 1] = parResults.timeEllapsed;
	summarySequential[i - 1] = seqResults.timeEllapsed;
}
println("Mean times: ");
println("- Sequential: " + mean(summarySequential) + " seconds.");
println("- Parallel: " + mean(summaryParallel) + " seconds.");

