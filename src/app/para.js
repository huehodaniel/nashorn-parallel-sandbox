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

function squareMean(values) {
	var total = 0;
	values.forEach(function(e) {
		total += Math.pow(e, 2);
	});
	return Math.sqrt(total/values.length);
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
		result.push(arr.splice(0, len))
	}
	return result;
}

function sequential(n) {
	var params = randomParams(n);
	
	var start = time();
	squareMean(params);
	return time().subtract(start).divide(NANOSEC);
}

function parallel(n, tasks) {
	var params = Java.to(partition(randomParams(n), tasks), "java.lang.Object[]");
	
	var start = time();
	executor.invokeAll(squareMean, params);
	return time().subtract(start).divide(NANOSEC);
}


var n = 2000000;
var tasks = 4;
var runs = 10;

for(var i = 0; i < runs; i++) {
	println("RUN #" + i);
	println("----------------")
	println("Running sequential test...");
	println(sequential(n) + " seconds");
	println("Running parallel test...");
	println(parallel(n, tasks) + " seconds");
	println("----------------")
}
println("End...")